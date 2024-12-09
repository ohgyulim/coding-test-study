import sys
import heapq

input = sys.stdin.readline


def solv():
    N = int(input())
    graph = []
    for n in range(N):
        li = list(map(int, input().split()))
        for i in range(N):
            heapq.heappush(graph, li[i])
        if n == 0:
            continue
        for i in range(N):
            heapq.heappop(graph)
    answer = heapq.heappop(graph)
    print(answer)


solv()
