import heapq
import sys

input = sys.stdin.readline


def solv():
    n, m = map(int, input().split())
    A = list(map(int, input().split()))

    heapq.heapify(A)
    for _ in range(m):
        a = heapq.heappop(A) + heapq.heappop(A)
        heapq.heappush(A, a)
        heapq.heappush(A, a)
    print(sum(A))


solv()
