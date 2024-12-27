import sys
from collections import deque

input = sys.stdin.readline

dr = [1, -1, 0, 0]
dc = [0, 0, 1, -1]


def solv():
    N, M, T = map(int, input().split())
    board = [deque() for _ in range(N + 1)]
    for i in range(N):
        board[i + 1] = deque(list(map(int, input().split())))

    for _ in range(T):
        x, d, k = map(int, input().split())

        ### 회전
        for i in range(x, N + 1, x):
            for _ in range(k):
                if d == 0:
                    board[i].appendleft(board[i].pop())
                else:
                    board[i].append(board[i].popleft())

        for i in range(1, N + 1):
            board[i] = list(board[i])

        ### board에 모든 좌표에 접근해서 해당 좌표와 인접하면 하면서 값이 같은 좌표가 있는지 확인
        ### 인접한 좌표의 값이 현재 좌표의 값과 같다는 조건으로 bfs를 동작
        ### 이 때 한번이라도 인접한 좌표와 현재 좌표의 값이 같다면 flag를 False로 바꿈
        ### 모든 좌표에 대해서 인접한 좌표를 체크 했을 때 여전히 flag가 True라면 인접하면서 값이 같은 경우가 없다는 뜻으로
        ### 원판의 값들의 평균을 구해서 평균보다 큰 수는 -1, 작은 수는 +1함
        flag = True
        total = 0
        cnt = 0
        for i in range(1, N + 1):
            for j in range(0, M):
                if board[i][j] != 0:
                    total += board[i][j]
                    cnt += 1
                    queue = deque()
                    queue.append((i, j))
                    value = board[i][j]

                    while queue:
                        r, c = queue.popleft()
                        for dir in range(4):
                            nr = r + dr[dir]
                            nc = c + dc[dir]
                            if nr <= 0 or nr > N:
                                continue
                            if nc == -1:
                                nc = M - 1
                            elif nc == M:
                                nc = 0

                            if board[nr][nc] == value:
                                board[nr][nc] = 0
                                queue.append((nr, nc))
                                flag = False

        if flag:
            if cnt == 0:
                cnt = 1
            avg = total / cnt
            for i in range(1, N + 1):
                for j in range(0, M):
                    if board[i][j] > avg:
                        board[i][j] -= 1
                    elif board[i][j] < avg and board[i][j] != 0:
                        board[i][j] += 1

        for i in range(1, N + 1):
            board[i] = deque(board[i])

    answer = 0
    for i in range(1, N + 1):
        while board[i]:
            answer += board[i].pop()
    print(answer)


solv()
