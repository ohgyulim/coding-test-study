import sys

input = sys.stdin.readline


def solv():
    N, M = map(int, input().split())
    lectures = list(map(int, input().split()))

    right = sum(lectures)
    left = max(max(lectures), right // M)
    answer = 0
    while left <= right:
        mid = (left + right) // 2
        cnt = 1
        time = 0
        for lecture in lectures:
            if time + lecture > mid:
                cnt += 1
                time = 0
            time += lecture

        if cnt <= M:
            answer = mid
            right = mid - 1
        else:
            left = mid + 1
    print(answer)

solv()