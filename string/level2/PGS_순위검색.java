package string.level2;

import java.util.*;

public class PGS_순위검색 {
	List<String> languages = List.of("cpp", "java", "python");
	List<String> devs = List.of("backend", "frontend");
	List<String> types = List.of("junior", "senior");
	List<String> foods = List.of("chicken", "pizza");

	public int[] solution(String[] info, String[] queries) {
		int[] answer = new int[queries.length];
		Map<String, Map<String, Map<String, Map<String, List<Integer>>>>> infos = initMap();

		for (String i : info) {
			String[] arr = i.split(" ");
			String lang = arr[0];
			String dev = arr[1];
			String type = arr[2];
			String food = arr[3];
			int score = Integer.parseInt(arr[4]);
			infos.get(lang).get(dev).get(type).get(food).add(score);
		}

		for (String lang : languages) {
			for (String d : devs) {
				for (String t : types) {
					for (String f : foods) {
						List<Integer> scores = infos.get(lang).get(d).get(t).get(f);
						Collections.sort(scores);
					}
				}
			}
		}

		int index = 0;
		for (String query : queries) {
			String[] arr = query.split(" ");
			String lang = arr[0];
			String dev = arr[2];
			String type = arr[4];
			String food = arr[6];
			int score = Integer.parseInt(arr[7]);
			int count = 0;

			for (String l : languages) {
				if (!lang.equals("-") && !lang.equals(l)) continue;
				for (String d : devs) {
					if (!dev.equals("-") && !dev.equals(d)) continue;
					for (String t : types) {
						if (!type.equals("-") && !type.equals(t)) continue;
						for (String f : foods) {
							if (!food.equals("-") && !food.equals(f)) continue;

							List<Integer> scores = infos.get(l).get(d).get(t).get(f);
							if (scores.isEmpty()) continue;

							int idx = Collections.binarySearch(scores, score);
							if (idx >= 0) {
								while (idx > 0 && score == scores.get(idx - 1)) {
									idx -= 1;
								}
								count += scores.size() - idx;
							} else {
								count += scores.size() + (idx + 1);
							}
						}
					}
				}
			}
			answer[index++] = count;
		}
		return answer;
	}

	public Map<String, Map<String, Map<String, Map<String, List<Integer>>>>> initMap() {
		Map<String, Map<String, Map<String, Map<String, List<Integer>>>>> infos = new HashMap<>();

		for (String lang : languages) {
			infos.put(lang, new HashMap<>());
			for (String dev : devs) {
				infos.get(lang).put(dev, new HashMap<>());
				for (String type : types) {
					infos.get(lang).get(dev).put(type, new HashMap<>());
					for (String food : foods) {
						infos.get(lang).get(dev).get(type).put(food, new ArrayList<>());
					}
				}
			}
		}
		return infos;
	}
}
