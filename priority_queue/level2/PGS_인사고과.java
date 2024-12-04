package priority_queue.level2;

import java.util.*;

class PGS_인사고과 {
	public int solution(int[][] scores) {
		int answer = 1;
		int[] wanho = scores[0];
		boolean isExistAnswer = false;
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[0] == o1[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
		PriorityQueue<int[]> scoreHeap = new PriorityQueue<>((o1, o2) -> (o2[0] + o2[1]) - (o1[0] + o1[1]));
		for (int[] arr : scores) {
			pq.offer(arr);
		}
		int[] scorePair = pq.poll();
		int workScore = scorePair[0];
		int peerScore = scorePair[1];

		scoreHeap.offer(scorePair);

		while (!pq.isEmpty()) {
			scorePair = pq.poll();
			int nextWorkScore = scorePair[0];
			int nextPeerScore = scorePair[1];
			if (workScore > nextWorkScore && peerScore > nextPeerScore) {
				continue;
			}
			scoreHeap.offer(scorePair);
			workScore = nextWorkScore;
			peerScore = Math.max(peerScore, nextPeerScore);
		}

		while (!scoreHeap.isEmpty()) {
			int[] score = scoreHeap.poll();

			if (wanho[0] == score[0] && wanho[1] == score[1]) {
				isExistAnswer = true;
				break;
			}
			if ((wanho[0] + wanho[1]) == (score[0] + score[1])) continue;
			answer += 1;
		}

		return isExistAnswer ? answer : -1;
	}
}