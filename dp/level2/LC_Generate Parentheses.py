from collections import deque


class Solution:
    def generateParenthesis(self, n: int):
        dp = [ set() for _ in range(n+1)]
        dp[1] = {"()"}
        for i in range(2,n+1):
            for j in range(1,i):
                for s in dp[j]:
                    for k in dp[i-j]:
                        dp[i].add(s+k)
            for x in dp[i-1]:
                dp[i].add("("+x+")")
        return list(dp[n])