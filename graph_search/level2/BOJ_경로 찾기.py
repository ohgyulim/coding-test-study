import sys

input = sys.stdin.readline


def solv():
    N = int(input())
    board = [list(map(int, input().split())) for _ in range(N)]

    for mid in range(N):
        for start in range(N):
            for end in range(N):
                if board[start][mid] and board[mid][end]:
                    board[start][end] = 1

    for arr in board:
        print(" ".join(map(str,arr)))
solv()