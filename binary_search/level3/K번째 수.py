import sys

input = sys.stdin.readline

def solv():
    N = int(input())
    k = int(input())

    left = 0
    right = k  # k번째 수는 k보다 작거나 같다.

    answer = 0
    while left <= right:
        mid = (left + right) // 2
        # 정답이 mid값인지 확인
        cnt = 0
        for i in range(1, N + 1):
            cnt += min(mid // i, N)

        if cnt < k:
            left = mid + 1
        else:
            answer = mid
            right = mid - 1
    print(answer)


solv()
