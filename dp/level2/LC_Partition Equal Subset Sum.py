class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        total_sum = sum(nums)
        if total_sum % 2 != 0:
            return False

        part_sum = total_sum // 2
        dp = [False] * (part_sum + 1)
        dp[0] = True

        for num in nums:
            for i in range(part_sum, num - 1, -1):
                dp[i] = dp[i] or dp[i - num]
        return dp[part_sum]
