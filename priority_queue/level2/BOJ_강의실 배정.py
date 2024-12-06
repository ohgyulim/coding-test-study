import sys
import heapq

input = sys.stdin.readline


def solv():
    answer = 0
    N = int(input())
    li = [list(map(int, input().split())) for _ in range(N)]
    li.sort()
    heap = []
    for s, t in li:
        if not heap:
            heapq.heappush(heap, t)
            answer += 1
            continue
        if heap[0] > s:
            answer += 1
        else:
            heapq.heappop(heap)
        heapq.heappush(heap, t)
    print(answer)

solv()
