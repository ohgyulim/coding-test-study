n = 0
dp = []
class Solution:
    def partition(self, s: str) -> List[List[str]]:
        global n, dp
        n = len(s)
        dp = [[True] *(n+1) for _ in range(n+1)]
        answer = []
        for i in range(n, 0, -1):
            for j in range(i, n+1):
                if i == j:
                    dp[i][j] = True
                    continue
                else:
                    dp[i][j] = dp[i+1][j-1] and s[i-1] == s[j-1]
        for i in range(1,n+1):
            if dp[1][i]:
                self.dfs(i+1, [s[0:i]], s, answer)
        return answer
    def dfs(self, y, li, s, answer):
        global n, dp
        if y >= n+1:
            answer.append(li)
            return
        for i in range(y, n+1):
            if dp[y][i]:
                self.dfs(i+1, li+[s[y-1:i]], s, answer)
