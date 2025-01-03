package simulation.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class BOJ_2x2x2_큐브 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int answer = 0;
		Map<Integer, Integer> cubeInfo = new HashMap<>();
		List<Integer> posList;
		int[] pos;

		for (int i = 1; i <= 24; i++) {
			cubeInfo.put(i, Integer.parseInt(st.nextToken()));
		}

		// 좌우 고정, 상하 움직임
		if (isSameColor(cubeInfo.get(13), cubeInfo.get(14), cubeInfo.get(15), cubeInfo.get(16)) &&
			isSameColor(cubeInfo.get(17), cubeInfo.get(18), cubeInfo.get(19), cubeInfo.get(20))) {
			pos = new int[]{2, 4, 6, 8, 10, 12, 21, 23};
			posList = new ArrayList<>(List.of(1, 3, 5, 7, 9, 11, 22, 24));
			for (int shift = 0; shift < 3; shift++) {
				answer = 1;
				for (int i = 0; i < 8; i += 2) {
					posList.add(posList.remove(0));
					posList.add(posList.remove(0));
					if (!isSameColor(cubeInfo.get(posList.get(0)), cubeInfo.get(posList.get(1)), cubeInfo.get(pos[i]), cubeInfo.get(pos[i+1]))) {
						answer = 0;
						break;
					}
				}
				if (answer == 1) break;
			}
		} // 상하 고정, 좌우 움직임
		if (answer != 1 && isSameColor(cubeInfo.get(1), cubeInfo.get(2), cubeInfo.get(3), cubeInfo.get(4)) &&
			isSameColor(cubeInfo.get(9), cubeInfo.get(10), cubeInfo.get(11), cubeInfo.get(12))) {
			pos = new int[]{15, 16, 7, 8, 19, 20, 23, 24};
			posList = new ArrayList<>(List.of(13, 14, 5, 6, 17, 18, 21, 22));
			for (int shift = 0; shift < 3; shift++) {
				answer = 1;
				for (int i = 0; i < 8; i += 2) {
					posList.add(posList.remove(0));
					posList.add(posList.remove(0));
					if (!isSameColor(cubeInfo.get(posList.get(0)), cubeInfo.get(posList.get(1)), cubeInfo.get(pos[i]), cubeInfo.get(pos[i + 1]))) {
						answer = 0;
						break;
					}
				}
				if (answer == 1) break;
			}
		}

		if (answer != 1 && isSameColor(cubeInfo.get(22), cubeInfo.get(21), cubeInfo.get(24), cubeInfo.get(23)) &&
			isSameColor(cubeInfo.get(5), cubeInfo.get(6), cubeInfo.get(7), cubeInfo.get(8))) {
			pos = new int[]{1, 2, 18, 20, 12, 11, 15, 13};
			posList = new ArrayList<>(List.of(3, 4, 17, 19, 10, 9, 16, 14));
			for (int shift = 0; shift < 3; shift++) {
				answer = 1;
				for (int i = 0; i < 8; i += 2) {
					posList.add(posList.remove(0));
					posList.add(posList.remove(0));
					if (!isSameColor(cubeInfo.get(posList.get(0)), cubeInfo.get(posList.get(1)), cubeInfo.get(pos[i]), cubeInfo.get(pos[i + 1]))) {
						answer = 0;
						break;
					}
				}
				if (answer == 1) break;
			}
		}

		System.out.println(answer);
	}

	public static boolean isSameColor(int color1, int color2, int color3, int color4) {
		return color1 == color2 && color2 == color3 && color3 == color4;
	}
}
