package binary_search.level2;
import java.util.*;


class PGS_순위검색 {
    class Language {
        Job be = new Job();
        Job fe = new Job();

        public Job getJob(String job) {
            if (job.equals("backend")) {
                return be;
            }
            return fe;
        }

        public Job[] getAllJob() {
            return new Job[] {be, fe};
        }

        public void sortScore() {
            be.sortScore();
            fe.sortScore();
        }
    }

    class Job {
        Career junior = new Career();
        Career senior = new Career();

        public Career getCareer(String career) {
            if (career.equals("junior")) {
                return junior;
            }
            return senior;
        }

        public Career[] getAllCareer() {
            return new Career[] {junior, senior};
        }

        public void sortScore() {
            junior.sortScore();
            senior.sortScore();
        }
    }

    class Career {
        Food pizza = new Food();
        Food chicken = new Food();

        public Food getFood(String food) {
            if (food.equals("pizza")) {
                return pizza;
            }
            return chicken;
        }

        public Food[] getAllFood() {
            return new Food[] {pizza, chicken};
        }

        public void sortScore() {
            pizza.sortScore();
            chicken.sortScore();
        }
    }

    class Food {
        List<Integer> scores = new ArrayList<>();

        public List<Integer> getScores() {
            return scores;
        }
        public void sortScore() {
            Collections.sort(scores);
        }
    }

    public int[] solution(String[] infoArray, String[] queries) {
        int[] answer = new int[queries.length];

        Language cpp = new Language();
        Language java = new Language();
        Language python = new Language();
        Map<String, Language> map = new HashMap<>();
        map.put("cpp", cpp);
        map.put("java", java);
        map.put("python", python);
        //Arrays.sort(infoArray, ((o1, o2) -> Integer.parseInt(o1.split(" ")[4]) - Integer.parseInt(o2.split(" ")[4])));
        for (String info : infoArray) {
            String[] person = info.split(" ");
            Language language = map.get(person[0]);
            Job job = language.getJob(person[1]);
            Career career = job.getCareer(person[2]);
            Food food = career.getFood(person[3]);
            List<Integer> scores =food.getScores();

            scores.add(Integer.parseInt(person[4]));
        }
        cpp.sortScore();
        java.sortScore();
        python.sortScore();

        for (int i=0; i<queries.length; i++) {
            String query = queries[i];
            StringTokenizer st = new StringTokenizer(query);
            String[] queryArray = query.split(" ");
            String queryLanguage = queryArray[0];
            String queryJob = queryArray[2];
            String queryCareer = queryArray[4];
            String queryFood = queryArray[6];
            int queryScore = Integer.parseInt(queryArray[7]);

            int cnt = 0;
            Language[] languageArray;
            if (queryLanguage.equals("-")) {
                languageArray = new Language[] {cpp, java, python};
            } else {
                languageArray = new Language[] {map.get(queryLanguage)};
            }
            for (Language language : languageArray) {
                Job[] jobArray;
                if (queryJob.equals("-")) {
                    jobArray = language.getAllJob();
                } else {
                    jobArray = new Job[] {language.getJob(queryJob)};
                }

                for (Job job : jobArray) {
                    Career[] careerArray;
                    if (queryCareer.equals("-")) {
                        careerArray = job.getAllCareer();
                    } else {
                        careerArray = new Career[] {job.getCareer(queryCareer)};
                    }

                    for (Career career : careerArray) {
                        Food[] foodArray;
                        if (queryFood.equals("-")) {
                            foodArray = career.getAllFood();
                        } else {
                            foodArray = new Food[] {career.getFood(queryFood)};
                        }

                        for (Food food : foodArray) {
                            List<Integer> scores = food.getScores();

                            int left = 0;
                            int right = scores.size()-1;

                            while (left <= right) {
                                int mid = (left + right) / 2;
                                if (scores.get(mid) < queryScore) {
                                    left = mid + 1;
                                } else {
                                    right = mid - 1;
                                }
                            }

                            cnt += scores.size() - left;
                        }
                    }
                }
            }
            answer[i] = cnt;
        }

        return answer;
    }
}