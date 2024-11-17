package greedy.level2;

import java.util.*;

public class PGS_과제_진행하기 {
	public String[] solution(String[][] plans) {
		int index = 0;
		String[] answer = new String[plans.length];
		Deque<String[]> remainPlans = new ArrayDeque<>();
		Stack<String[]> proceedPlans = new Stack<>();
		Arrays.sort(plans, (o1, o2) -> (timeToInteger(o1[1], "0") - timeToInteger(o2[1], "0")));

		for (String[] plan : plans) {
			remainPlans.addLast(plan);
		}

		while (!remainPlans.isEmpty()) {
			String[] curPlan = remainPlans.pollFirst();
			String[] newPlan = remainPlans.peekFirst();

			if (newPlan != null) {
				int curIntTime = timeToInteger(curPlan[1], "0");
				int newIntTime = timeToInteger(newPlan[1], "0");

				int proceedTime = Integer.parseInt(curPlan[2]);
				int subTime = subtractTime(curIntTime, newIntTime);

				// 작업 즉시 완료 가능하면
				if (subTime >= proceedTime) {
					answer[index++] = curPlan[0];
					if (!proceedPlans.isEmpty()) {
						String proceededTime = integerToTime(addTime(curIntTime, proceedTime));
						String[] remainPlan = proceedPlans.pop();
						remainPlan[1] = proceededTime;
						remainPlans.offerFirst(remainPlan);
					}
				} else {
					curPlan[2] = String.valueOf(proceedTime - subTime);
					proceedPlans.push(curPlan);
				}
			} else {
				answer[index++] = curPlan[0];
			}
		}

		while (!proceedPlans.isEmpty()) {
			String[] plan = proceedPlans.pop();
			answer[index++] = plan[0];
		}

		return answer;
	}

	public int timeToInteger(String start, String plusTime) {
		String[] startTime = start.split(":");
		int hour = Integer.parseInt(startTime[0]) * 100;
		int minute = Integer.parseInt(startTime[1]) + Integer.parseInt(plusTime);

		if (minute >= 60) {
			hour += ((minute / 60) * 100);
			minute %= 60;
		}

		return hour + minute;
	}

	public int subtractTime(int time, int newTime) {
		int hour = time / 100;
		int newTimeHour = newTime / 100;

		int minute = (hour - newTimeHour) * 60 + (time % 100);
		int newTimeMinute = newTime % 100;

		return Math.abs(minute - newTimeMinute);
	}

	public int addTime(int time, int minute) {
		minute += (time % 100);
		time -= (time % 100);

		if (minute >= 60) {
			time += ((minute / 60) * 100);
			minute %= 60;
		}

		return time + minute;
	}

	public String integerToTime(int time) {
		StringBuilder sb = new StringBuilder();
		int hour = time / 100;
		int minute = time % 100;

		if (hour < 10) sb.append("0");
		sb.append(hour).append(":");
		if (minute < 10) sb.append("0");
		sb.append(minute);

		return sb.toString();
	}
}
