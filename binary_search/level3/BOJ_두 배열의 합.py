import sys
from collections import defaultdict
input = sys.stdin.readline


def solv():
    T = int(input())
    n = int(input())
    A = [0] + list(map(int, input().split()))
    m = int(input())
    B = [0] + list(map(int, input().split()))
    answer = 0
    for i in range(1,n+1):
        A[i] += A[i-1]
    for i in range(1,m+1):
        B[i] += B[i-1]

    sumSubA = defaultdict(int)
    sumSubB = defaultdict(int)
    for i in range(n+1):
        for j in range(i+1,n+1):
            sumSubA[A[j] - A[i]] += 1

    for i in range(m+1):
        for j in range(i+1,m+1):
            sumSubB[B[j] - B[i]] += 1
    for key in sumSubA:
        target = T - key
        answer += sumSubA[key] * sumSubB[target]
    print(answer)
    # for key in sumSubA:
    #     target = T - key
    #     left = 0
    #     right = 1
    #     while True:
    #         if left == m:
    #             break
    #         if left == right:
    #             right += 1
    #         if B[right] - B[left] == target:
    #             answer += 1
    #             if right == m:
    #                 left += 1
    #             else:
    #                 right += 1
    #         elif B[right] - B[left] > target:
    #             left += 1
    #         else:
    #             if right == n:
    #                 left += 1
    #             else:
    #                 right += 1
    # print(answer)
solv()
