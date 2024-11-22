class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        n = len(nums)
        dp = [0] * n
        dp[0] = 1
        answer = 1
        for i in range(1, n):
            mx = 0
            for j in range(i):
                if nums[i] > nums[j]:
                    mx = max(mx, dp[j])
            dp[i] = mx + 1
            answer = max(answer, dp[i])
        return answer
