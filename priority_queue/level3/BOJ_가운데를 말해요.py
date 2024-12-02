import heapq
import sys

input = sys.stdin.readline


def solv():
    N = int(input())
    min_heap = [] # middle보다 크거가 같은 값이 들어있음
    max_heap = [] # middle보다 작은 값이 들어있음
    middle = int(input())
    print(middle)
    for i in range(2, N + 1):
        x = int(input())
        if middle <= x:
            heapq.heappush(min_heap, x)
        else:
            heapq.heappush(max_heap, -x)

        if i % 2 != 0 and middle <= x:
            heapq.heappush(max_heap, -middle)
            middle = heapq.heappop(min_heap)
        elif i % 2 == 0 and middle > x:
            heapq.heappush(min_heap, middle)
            middle = -heapq.heappop(max_heap)
        print(middle)
solv()


"""
중간값 보다 크거나 같은 값이 입력으로 들어오면  min_heap에 넣는다
중간값 보다 작은 값이 입력으로 들어오면 max_heap에 넣는다
중간값이 바뀌는 경우는 홀수 번째 입력이 들어왔는데 그 그값이 중간값보다 크거나 같을 때, 짝수 번째 입력이 들어왔는데 그 값이 중간값보다 작을 때

홀수 번째 입력이 들어왔는데 그 그값이 중간값보다 크거나 같을 때
   중간값을 max_heap에 넣음
   min_heap에서 하나 빼서 중간값으로 교체

짝수 번째 입력이 들어왔는데 그 값이 중간값보다 작을 때
   중간값을 min_heap에 넣음
   max_heap에서 하나 빼서 중간값으로 교체
"""
