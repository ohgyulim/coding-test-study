# 처음으로 생각해낸 틀린 풀이
# 주말에 다시 풀 예정

import sys
import heapq

input = sys.stdin.readline


def solv():
    N = int(input())
    heap = []
    days = [0] * (1001)
    for _ in range(N):
        d, w = map(int, input().split())
        heapq.heappush(heap, (-w, d))
    idx = 1
    while heap:
        w, d = heapq.heappop(heap)
        w = -w
        if days[d] == 0:
            days[d] = w
        elif d > idx:
            days[idx] = w
        for i in range(idx, 1001):
            if days[i] == 0:
                idx = i
                break
    print(sum(days))


solv()
