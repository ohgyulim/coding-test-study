# 풀긴했느데 쓰레기 처럼 품

import sys
import heapq

input = sys.stdin.readline

# 풀이
# 힙에 w,d 를 저장해둔다. (w가 크면서, d가 작은 순으로)
# idx(날짜)를 1부터 증가 시켜가면서 힙에서 뺀 값을 해당 idx(날짜)에 임시로 해결한댜고 가정한다.
# 그러다 힙에서 뺀 값의 과제 마감일이 현재 날짜보다 작으면 idx-1부터 1까지 할당된 과제를 탐색하면서 tmp_idx보다 과제 마감일이 크면 과제를 tmp_idx에 새로 할당하고 원래 날짜엔 빈 값으로 대체한다.
# 그렇게 해서 마지막 tmp_idx보다 힙에서 뺀 과제의 마감일이 더 크면 tmp_idx에 현재 과제를 할당한다
# 만약 tmp_idx보다 현재 과제의 마감일이 더 작으면 이전까지의 과정을 전부 취소한다.

def solv():
    N = int(input())
    heap = []
    days = [[] for _ in range(1001)]
    for _ in range(N):
        d, w = map(int, input().split())
        heapq.heappush(heap, (-w, d))
    idx = 1
    answer = 0
    while heap:
        w, d = heapq.heappop(heap)
        w = -w
        if idx <= d:
            days[idx] = [w, d]
            idx += 1
            answer += w
        else:
            tmp_idx = idx
            tmp_days = days[:]
            for i in range(idx - 1, 0, -1):
                if days[i] and days[i][1] >= tmp_idx:
                    days[tmp_idx] = days[i][:]
                    tmp_idx = i
                    days[i] = []
            if tmp_idx <= d:
                days[tmp_idx] = [w, d]
                answer += w
            else:
                days = tmp_days
            if days[idx]:
                idx += 1
        # print(days)
    print(answer)


solv()

# 76ms