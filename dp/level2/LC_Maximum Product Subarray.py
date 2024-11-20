class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        n = len(nums)
        answer = max(nums)
        curMax, curMin = 1, 1

        for n in nums:
            if n == 0:
                curMax = 1
                curMin = 1
                continue
            tmp = curMax * n
            curMax = max(tmp, n, curMin * n)
            curMin = min(tmp, n, curMin * n)
            answer = max(answer, curMax)
        return answer
