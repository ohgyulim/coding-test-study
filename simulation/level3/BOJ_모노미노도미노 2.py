import sys

input = sys.stdin.readline


def solv():
    N = int(input())
    list = [list(map(int, input().split())) for _ in range(N)]

solv()