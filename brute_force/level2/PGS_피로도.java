package brute_force.level2;

public class PGS_피로도 {
	boolean[] isVisited;
	int cnt = 0;
	public int solution(int k, int[][] dungeons) {
		isVisited = new boolean[dungeons.length];
		recur(k, 0, dungeons);
		return cnt;
	}

	public void recur(int pilodo, int count, int[][] dungeons) {
		cnt = Math.max(cnt, count);

		for (int i = 0; i < dungeons.length; i++) {
			if (isVisited[i]) continue;
			int need = dungeons[i][0];
			int consume = dungeons[i][1];
			if (pilodo >= need) {
				isVisited[i] = true;
				recur(pilodo - consume, count + 1, dungeons);
				isVisited[i] = false;
			}
		}
	}
}
