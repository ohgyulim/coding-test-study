import sys
class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        dp = [sys.maxsize] * (amount+1)
        dp[0] = 0
        coins.sort()
        for i in range(1, amount+1):
            tmp = [sys.maxsize]
            for coin in coins:
                if i - coin < 0:
                    break
                tmp.append(dp[i-coin]+1)
            dp[i] = min(tmp)
        return -1 if dp[amount] == sys.maxsize else dp[amount]
