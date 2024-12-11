import heapq
import sys

input = sys.stdin.readline


def solv():
    N, L = map(int, input().split())
    A = list(map(int, input().split()))

    heap = []
    for a in A:
        heapq.heappush(heap, [a]*L)
        print(heap[0].pop(), end=" ")
        for i in range(1, len(heap)):
            heap[i].pop()
        heapq.heapify(heap)
        while not heap[0]:
            heapq.heappop(heap)
solv()
