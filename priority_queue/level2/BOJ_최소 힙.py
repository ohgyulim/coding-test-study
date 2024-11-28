import sys
import heapq

input = sys.stdin.readline

def solv():
    N = int(input())
    heap = []
    for _ in range(N):
        x = int(input())
        if x != 0:
            heapq.heappush(heap, x)
        else:
            if not heap:
                print(0)
            else:
                print(heapq.heappop(heap))

solv()