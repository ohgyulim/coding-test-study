import sys
from collections import deque

input = sys.stdin.readline

# 풀이
# 리스트 대신 deque를 사용하니 간당간당하게 시간초과 안남
def solv():
    N, M, K = map(int, input().split())
    A = []
    for _ in range(N):
        A.append(list(map(int, input().split())))
    graph = [[5] * N for _ in range(N)]
    trees = deque()
    for _ in range(M):
        x, y, z = map(int, input().split())
        trees.append((z, x-1, y-1))

    dx = [-1, -1, -1, 0, 0, 1, 1, 1]
    dy = [-1, 0, 1, -1, 1, -1, 0, 1]
    new_trees = deque()
    for _ in range(K):
        death_trees = deque()
        n = len(trees)

        while new_trees:
            z, x, y = new_trees.pop()
            if z <= graph[x][y]:
                graph[x][y] -= z
                trees.appendleft((z + 1, x, y))
            else:
                death_trees.append((z, x, y))
        for _ in range(n):
            z, x, y = trees.pop()
            if z <= graph[x][y]:
                graph[x][y] -= z
                trees.appendleft((z + 1, x, y))
                if (z + 1) % 5 == 0:
                    for i in range(8):
                        nx = x + dx[i]
                        ny = y + dy[i]
                        if nx < 0 or nx >= N or ny < 0 or ny >= N:
                            continue
                        new_trees.append((1, nx, ny))
            else:
                death_trees.append((z, x, y))

        for z, x, y in death_trees:  # 여름
            graph[x][y] += z // 2

        for i in range(0, N):  # 겨울
            for j in range(0, N):
                graph[i][j] += A[i][j]
    print(len(trees)+len(new_trees))


solv()
