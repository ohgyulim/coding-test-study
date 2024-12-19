package simulation.level3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_이차원_배열과_연산 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			ArrayList<Integer> row = new ArrayList<>();
			for (int j = 0; j < 3; j++) {
				row.add(Integer.parseInt(st.nextToken()));
			}
			matrix.add(row);
		}

		int answer = -1;
		for (int time = 0; time <= 100; time++) {
			if (r - 1 < matrix.size() && c - 1 < matrix.get(0).size() && matrix.get(r - 1).get(c - 1) == k) {
				answer = time;
				break;
			}

			if (matrix.size() >= matrix.get(0).size()) {
				matrix = executeR(matrix);
			} else {
				matrix = executeC(matrix);
			}
		}

		System.out.println(answer);
	}

	public static ArrayList<ArrayList<Integer>> executeR(ArrayList<ArrayList<Integer>> matrix) {
		ArrayList<ArrayList<Integer>> newMatrix = new ArrayList<>();
		int maxLength = 0;

		for (ArrayList<Integer> row : matrix) {
			Map<Integer, Integer> countMap = new HashMap<>();
			for (int num : row) {
				if (num == 0) continue;
				countMap.put(num, countMap.getOrDefault(num, 0) + 1);
			}

			List<Map.Entry<Integer, Integer>> sortedEntries = sortByValue(countMap);
			ArrayList<Integer> newRow = new ArrayList<>();
			for (Map.Entry<Integer, Integer> entry : sortedEntries) {
				newRow.add(entry.getKey());
				newRow.add(entry.getValue());
			}

			maxLength = Math.max(maxLength, newRow.size());
			newMatrix.add(newRow);
		}

		for (ArrayList<Integer> row : newMatrix) {
			while (row.size() < maxLength) {
				row.add(0);
			}
		}

		return newMatrix;
	}

	public static ArrayList<ArrayList<Integer>> executeC(ArrayList<ArrayList<Integer>> matrix) {
		int numCols = matrix.get(0).size();
		ArrayList<ArrayList<Integer>> newMatrix = new ArrayList<>();
		int maxLength = 0;

		for (int col = 0; col < numCols; col++) {
			Map<Integer, Integer> countMap = new HashMap<>();
			for (ArrayList<Integer> row : matrix) {
				if (col < row.size() && row.get(col) != 0) {
					int num = row.get(col);
					countMap.put(num, countMap.getOrDefault(num, 0) + 1);
				}
			}

			List<Map.Entry<Integer, Integer>> sortedEntries = sortByValue(countMap);
			ArrayList<Integer> newCol = new ArrayList<>();
			for (Map.Entry<Integer, Integer> entry : sortedEntries) {
				newCol.add(entry.getKey());
				newCol.add(entry.getValue());
			}

			maxLength = Math.max(maxLength, newCol.size());
			newMatrix.add(newCol);
		}

		for (ArrayList<Integer> col : newMatrix) {
			while (col.size() < maxLength) {
				col.add(0);
			}
		}

		ArrayList<ArrayList<Integer>> resultMatrix = new ArrayList<>();
		for (int i = 0; i < maxLength; i++) {
			ArrayList<Integer> newRow = new ArrayList<>();
			for (ArrayList<Integer> col : newMatrix) {
				newRow.add(col.get(i));
			}
			resultMatrix.add(newRow);
		}

		return resultMatrix;
	}

	public static List<Map.Entry<Integer, Integer>> sortByValue(Map<Integer, Integer> countMap) {
		List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(countMap.entrySet());
		entryList.sort((o1, o2) -> {
			if (o1.getValue().equals(o2.getValue())) {
				return Integer.compare(o1.getKey(), o2.getKey());
			}
			return Integer.compare(o1.getValue(), o2.getValue());
		});
		return entryList;
	}
}
