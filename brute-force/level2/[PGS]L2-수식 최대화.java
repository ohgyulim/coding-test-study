import java.util.*;

class Solution {
    private static final String[][] priorityOrders = {
            {"+", "-", "*"}, {"+", "*", "-"},
            {"-", "+", "*"}, {"-", "*", "+"},
            {"*", "+", "-"}, {"*", "-", "+"}
    };

    public long solution(String expression) {
        List<Long> numbers = new ArrayList<>();
        List<String> operators = new ArrayList<>();
        parseExpression(expression, numbers, operators);

        long maxResult = 0;

        for (String[] priority : priorityOrders) {
            maxResult = Math.max(maxResult, calculateMaxValue(numbers, operators, priority));
        }

        return maxResult;
    }

    private void parseExpression(String expression, List<Long> numbers, List<String> operators) {
        StringBuilder num = new StringBuilder();
        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c)) {
                num.append(c);
            } else {
                numbers.add(Long.parseLong(num.toString()));
                num.setLength(0);
                operators.add(String.valueOf(c));
            }
        }
        numbers.add(Long.parseLong(num.toString()));
    }

    private long calculateMaxValue(List<Long> numbers, List<String> operators, String[] priority) {
        List<Long> numList = new ArrayList<>(numbers);
        List<String> opList = new ArrayList<>(operators);

        for (String op : priority) {
            for (int i = 0; i < opList.size(); ) {
                if (opList.get(i).equals(op)) {
                    long result = compute(numList.remove(i), numList.remove(i), op);
                    numList.add(i, result);
                    opList.remove(i);
                } else {
                    i++;
                }
            }
        }
        return Math.abs(numList.get(0));
    }

    private long compute(long a, long b, String operator) {
        switch (operator) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
        }
        return 0;
    }
}