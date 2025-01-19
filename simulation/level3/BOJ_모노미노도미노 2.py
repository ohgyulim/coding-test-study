import sys

input = sys.stdin.readline

blueBoard = [[0] * 4 for _ in range(6)]
greenBoard = [[0] * 4 for _ in range(6)]
point = 0


def solv():
    global point
    N = int(input())
    simulation = [list(map(int, input().split())) for _ in range(N)]

    for t, x, y in simulation:
        moveToBoard(t,x,y,"green")
        moveToBoard(t,x,y,"blue")

    print(point)
    cnt = 0
    for r in range(2,6):
        for c in range(4):
            if blueBoard[r][c] == 1:
                cnt += 1
            if greenBoard[r][c] == 1:
                cnt += 1
    print(cnt)
def moveToBoard(t, x, y, color):
    global point
    x1, y1 = 0, 0
    x2, y2 = 0, 0
    if color == "green":
        board = greenBoard

        if t == 1:
            x1, y1 = 1, y
            x2, y2 = 1, y
        elif t == 2:
            x1, y1 = 1, y
            x2, y2 = 1, y + 1
        else:
            x1, y1 = 1, y
            x2, y2 = 0, y
    else:
        board = blueBoard

        if t == 1:
            x1, y1 = 1, x
            x2, y2 = 1, x
        elif t == 2:
            x1, y1 = 1, x
            x2, y2 = 0, x
        else:
            x1, y1 = 1, x
            x2, y2 = 1, x + 1

    # 블록 이동
    while True:
        if x1 == 5 or board[x1 + 1][y1] == 1 or board[x2 + 1][y2] == 1:
            break
        x1 += 1
        x2 += 1
    board[x1][y1] = 1
    board[x2][y2] = 1

    # 가득 찬 행 확인
    r = 5
    while r >=2:
        flag = True
        for i in board[r]:
            if i == 0:
                flag = False
                break
        if not flag:
            r -= 1
            continue

        point += 1
        # 위의 행 아래로 이동
        for i in range(r - 1, -1, -1):
            for j in range(4):
                board[i + 1][j] = board[i][j]
        for i in range(4):
            board[0][i] = 0
    # 1 행에 블록이 들어 있을 때
    k = 0
    for i in board[0]:
        if i == 1:
            k += 1
            break
    for i in board[1]:
        if i == 1:
            k += 1
            break
    if k == 0:
        return

    r = 5
    while r > 1:
        for c in range(4):
            board[r][c] = board[r-k][c]
        r -= 1
    for i in range(4):
        board[0][i] = 0
        board[1][i] = 0
solv()
