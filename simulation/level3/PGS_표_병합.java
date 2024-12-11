package simulation.level3;

import java.util.*;

public class PGS_표_병합 {

    class Node {
        int y;
        int x;
        int parentY = 0;
        int parentX = 0;
        boolean merged = false;
        String value;

        public Node(int y, int x, String value) {
            this.y = y;
            this.x = x;
            this.parentX = x;
            this.parentY = y;
            this.value = value;
        }

        public boolean isMerged() {
            return merged;
        }

        public boolean mergedCell(Node node) {
            return parentX == node.parentX && parentY == node.parentY;
        }

        public void setParent(Node node) {
            parentX = node.parentX;
            parentY = node.parentY;
        }

        public void init() {
            parentX = x;
            parentY = y;
            value = "EMPTY";
            merged = false;
        }
    }

    public String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();

        Node[][] graph = new Node[51][51];
        for (int i = 1; i < 51; i++) {
            for (int j = 1; j < 51; j++) {
                graph[i][j] = new Node(i, j, "EMPTY");
            }
        }

        for (String command : commands) {
            String[] splitedCommand = command.split(" ");
            if (splitedCommand[0].equals("UPDATE") && splitedCommand.length == 3) {
                updateFromValue(graph, splitedCommand[1], splitedCommand[2]);
            } else if (splitedCommand[0].equals("UPDATE")) {
                updateFromPosition(graph, Integer.parseInt(splitedCommand[1]), Integer.parseInt(splitedCommand[2]), splitedCommand[3]);
            } else if (splitedCommand[0].equals("MERGE")) {
                mergeGraph(graph, Integer.parseInt(splitedCommand[1]), Integer.parseInt(splitedCommand[2]), Integer.parseInt(splitedCommand[3]), Integer.parseInt(splitedCommand[4]));
            } else if (splitedCommand[0].equals("UNMERGE")) {
                unmergeGraph(graph, Integer.parseInt(splitedCommand[1]), Integer.parseInt(splitedCommand[2]));
            } else if (splitedCommand[0].equals("PRINT")) {
                answer.add(graph[Integer.parseInt(splitedCommand[1])][Integer.parseInt(splitedCommand[2])].value);
            }
        }


        return answer.toArray(new String[0]);
    }

    private void updateFromValue(Node[][] graph, String from, String to) {
        for (int i = 1; i < 51; i++) {
            for (int j = 1; j < 51; j++) {
                if (graph[i][j].value.equals(from)) {
                    graph[i][j].value = to;
                }
            }
        }
    }

    private void updateFromPosition(Node[][] graph, int r, int c, String to) {
        if (graph[r][c].isMerged()) {
            for (int i = 1; i < 51; i++) {
                for (int j = 1; j < 51; j++) {
                    if (graph[r][c].mergedCell(graph[i][j])) {
                        graph[i][j].value = to;
                    }
                }
            }
        } else {
            graph[r][c].value = to;
        }
    }

    private void mergeGraph(Node[][] graph, int r1, int c1, int r2, int c2) {
        if (graph[r1][c1].mergedCell(graph[r2][c2])) {
            return;
        }
        String mergedValue;
        if (!graph[r1][c1].value.equals("EMPTY") && !graph[r2][c2].value.equals("EMPTY")) {
            mergedValue = graph[r1][c1].value;
        } else if (!graph[r1][c1].value.equals("EMPTY")) {
            mergedValue = graph[r1][c1].value;
        } else {
            mergedValue = graph[r2][c2].value;
        }

        if (!graph[r1][c1].isMerged() && !graph[r2][c2].isMerged()) { // 둘 다 머지 안돼 있을 때
            graph[r1][c1].value = mergedValue;
            graph[r1][c1].merged = true;
            graph[r2][c2].value = mergedValue;
            graph[r2][c2].merged = true;
            graph[r2][c2].setParent(graph[r1][c1]);
        } else if (!graph[r2][c2].isMerged()) {  // r1,c1만 머지되어 있을 때
            graph[r1][c1].value = mergedValue;
            graph[r2][c2].setParent(graph[r1][c1]);
            graph[r2][c2].merged = true;
            for (int i = 1; i < 51; i++) {
                for (int j = 1; j < 51; j++) {
                    if (graph[r1][c1].mergedCell(graph[i][j])) {
                        graph[i][j].value = mergedValue;
                    }
                }
            }
        } else if (!graph[r1][c1].isMerged()) { // r2,c2만 머지되어 있을 때
            graph[r2][c2].value = mergedValue;
            graph[r1][c1].setParent(graph[r2][c2]);
            graph[r1][c1].merged = true;
            for (int i = 1; i < 51; i++) {
                for (int j = 1; j < 51; j++) {
                    if (graph[r2][c2].mergedCell(graph[i][j])) {
                        graph[i][j].value = mergedValue;
                    }
                }
            }
        } else { // 둘다 머지 되어 있을 때
            graph[r1][c1].value = mergedValue;
            for (int i = 1; i < 51; i++) {
                for (int j = 1; j < 51; j++) {
                    if ((r2 != i || c2 != j) && graph[r2][c2].mergedCell(graph[i][j])) {
                        graph[i][j].setParent(graph[r1][c1]);
                    }
                }
            }
            graph[r2][c2].setParent(graph[r1][c1]);
            for (int i = 1; i < 51; i++) {
                for (int j = 1; j < 51; j++) {
                    if (graph[r1][c1].mergedCell(graph[i][j])) {
                        graph[i][j].value = mergedValue;
                    }
                }
            }
        }

    }

    private void unmergeGraph(Node[][] graph, int r, int c) {
        String tmp = graph[r][c].value;
        for (int i = 1; i < 51; i++) {
            for (int j = 1; j < 51; j++) {
                if ((r != i || c != j) && graph[r][c].mergedCell(graph[i][j])) {
                    graph[i][j].init();
                }
            }
        }
        graph[r][c].init();
        graph[r][c].value = tmp;
    }
}
