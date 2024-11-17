package greedy.level2;

public class LC_Jump_Game {
	public boolean canJump(int[] nums) {
		int index = nums.length - 1;
		for (int i = nums.length - 2; i >= 0; i--) {
			// (이전 위치 + 이전 위치의 최대 step) >= 현재 위치
			if (i + nums[i] >= index) {
				index = i;
			}
		}
		return index == 0;
	}
}
