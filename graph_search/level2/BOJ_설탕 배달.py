import sys
input = sys.stdin.readline


def solv():
    N = int(input())

    for a in range(N//5, -1, -1):
        b = N - a*5
        if b % 3 == 0:
            print(a+b//3)
            return
    print(-1)

solv()