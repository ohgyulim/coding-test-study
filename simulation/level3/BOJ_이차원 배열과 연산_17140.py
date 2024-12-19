import sys
from collections import defaultdict
import heapq

input = sys.stdin.readline


def solv():
    # 입력
    r, c, k = map(int, input().split())
    A = [list(map(int, input().split())) for _ in range(3)]
    time = 0

    while time < 100 and (r > len(A) or c > len(A[0]) or A[r - 1][c - 1] != k):
        time += 1
        li = []
        length = 0

        if len(A) >= len(A[0]):
            for row in A:
                count = defaultdict(int)
                for n in row:
                    if n == 0: continue
                    count[n] += 1
                length = max(length, len(count) * 2)
                li.append(count)
            A = [[] for _ in range(len(A))]
            idx = 0
            length = min(100, length)
            for dict in li:
                heap = []
                for key in dict:
                    heapq.heappush(heap, (dict[key], key))
                while heap:
                    value, key = heapq.heappop(heap)
                    A[idx].append(key)
                    A[idx].append(value)
                while len(A[idx]) < length:
                    A[idx].append(0)
                idx += 1
        else:
            for col in range(len(A[0])):
                count = defaultdict(int)
                for row in range(len(A)):
                    if A[row][col] == 0: continue
                    count[A[row][col]] += 1
                length = max(length, len(count) * 2)
                li.append(count)

            length = min(100, length)

            A = [[0] * (len(A[0])) for _ in range(length)]
            idx = 0
            for dict in li:
                heap = []
                for key in dict:
                    heapq.heappush(heap, (dict[key], key))
                row = 0
                while heap and row < length:
                    value, key = heapq.heappop(heap)
                    A[row][idx] = key
                    A[row + 1][idx] = value
                    row += 2
                idx += 1
    print(time if time < 100 else -1)


solv()
