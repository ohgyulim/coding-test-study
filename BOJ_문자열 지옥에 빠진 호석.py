import sys
from collections import deque, defaultdict

input = sys.stdin.readline

dy = [-1, -1, -1, 0, 0, 1, 1, 1]
dx = [-1, 0, 1, -1, 1, -1, 0, 1]
dp = [defaultdict(list) for _ in range(11)]


def solv():
    N, M, K = map(int, input().split())
    board = [[""] * (M + 1)]
    for _ in range(N):
        board.append([""] + list(input().strip()))

    for r in range(1, N + 1):
        for c in range(1, M + 1):
            dp[1][board[r][c]].append([r, c])
    lastIdx = 1
    for _ in range(K):
        word = input().strip()

        while lastIdx < len(word):
            for key in dp[lastIdx]:
                for y, x in dp[lastIdx][key]:
                    for i in range(8):
                        ny = y + dy[i]
                        nx = x + dx[i]
                        if nx == 0:
                            nx = M
                        elif nx == M + 1:
                            nx = 1

                        if ny == 0:
                            ny = N
                        elif ny == N + 1:
                            ny = 1

                        new_sub_word = key + board[ny][nx]
                        dp[lastIdx + 1][new_sub_word].append([ny, nx])
            lastIdx += 1
        print(len(dp[len(word)][word]))


solv()
