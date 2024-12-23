import sys
from collections import defaultdict
input = sys.stdin.readline



def recur(i):
    global dp, P, Q
    if dp[i] != 0:
        return dp[i]
    dp[i] = recur(i//P) + recur(i//Q)
    return dp[i]


N, P, Q = map(int, input().split())
dp = defaultdict(int)
dp[0] = 1
print(recur(N))