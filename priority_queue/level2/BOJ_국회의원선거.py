import heapq
import sys

input = sys.stdin.readline


def solv():
    answer = 0
    N = int(input())
    dasom = 0
    max_heap = []
    for i in range(N):
        if i == 0:
            dasom = int(input())
        else:
            heapq.heappush(max_heap, -int(input()))
    while max_heap and dasom <= -max_heap[0]:
        mx = -heapq.heappop(max_heap)
        dasom += 1
        mx -= 1
        heapq.heappush(max_heap, -mx)
        answer += 1
    print(answer)
solv()
