import sys
from collections import deque


class Solution:
    # dp 풀이
    def canJump(self, nums: List[int]) -> bool:
        n = len(nums)
        dp = [False] * n
        dp[-1] = True
        for i in range(n - 2, -1, -1):
            for j in range(i + 1, i + nums[i] + 1):
                if j >= n:
                    break
                if dp[j]:
                    dp[i] = True
                    break
        return dp[0]

    # bfs 풀이
    # def canJump(self, nums: List[int]) -> bool:
    #     n = len(nums)
    #     if n == 1:
    #         return True
    #     queue = deque()
    #     queue.append(0)
    #     visited = [False] * n
    #     visited[0] = True
    #
    #     while queue:
    #         x = queue.popleft()
    #         for i in range(x + 1, x + nums[x] + 1):
    #             if i == n - 1:
    #                 return True
    #             if not visited[i]:
    #                 queue.append(i)
    #                 visited[i] = True
    #
    #     return False
