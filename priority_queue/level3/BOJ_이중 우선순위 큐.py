import sys
import heapq

input = sys.stdin.readline


def solv():
    T = int(input())

    for _ in range(T):
        k = int(input())
        min_heap = []
        max_heap = []
        dict = {}
        cnt = 0
        for i in range(k):
            s, n = input().split()
            n = int(n)

            if s == "I":
                heapq.heappush(min_heap, n)
                heapq.heappush(max_heap, -n)
                cnt += 1
                if n in dict:
                    dict[n] += 1
                else:
                    dict[n] = 1
            else:
                if cnt == 0:
                    continue
                # pop = 0
                if n == 1:
                    pop = -heapq.heappop(max_heap)
                    while dict[pop] == 0:
                        pop = -heapq.heappop(max_heap)
                else:
                    pop = heapq.heappop(min_heap)
                    while dict[pop] == 0:
                        pop = heapq.heappop(min_heap)
                cnt -= 1
                dict[pop] -= 1
                if cnt == 0:
                    min_heap = []
                    max_heap = []

        if cnt == 0:
            print("EMPTY")
        else:
            while dict[-max_heap[0]] == 0:
                heapq.heappop(max_heap)
            while dict[min_heap[0]] == 0:
                heapq.heappop(min_heap)
            print(-heapq.heappop(max_heap), heapq.heappop(min_heap))


solv()
