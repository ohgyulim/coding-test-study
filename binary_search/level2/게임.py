import sys

input = sys.stdin.readline


def solv():
    X, Y = map(int, input().split())

    Z = Y*100 // X

    if Z == 100 or Z == 99:
        print(-1)
        return

    left = 0
    right = X
    answer = -1
    while left <= right:
        mid = (left + right) // 2

        if Z == (Y + mid)*100 // (X + mid):
            left = mid + 1
        else:
            answer = mid
            right = mid - 1
    print(answer)


solv()
