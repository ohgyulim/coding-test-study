class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        N = len(nums)
        dp = [0] * N  # i인덱스를 마지막으로 포함하는 subarray의 최대 합
        dp[0] = nums[0]
        for i in range(1, N):
            if dp[i-1] + nums[i] < nums[i]:
                dp[i] = nums[i]
            else:
                dp[i] = dp[i-1] + nums[i]

        return max(dp)
