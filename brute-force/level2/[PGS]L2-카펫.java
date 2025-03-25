class Solution {
    public int[] solution(int brown, int yellow) {
        int total = brown + yellow;

        for (int width = 3; width <= Math.sqrt(total); width++) {
            if (total % width == 0) {
                int height = total / width;

                if ((width - 2) * (height - 2) == yellow) {
                    if (width < height) {
                        int temp = width;
                        width = height;
                        height = temp;
                    }
                    return new int[]{width, height};
                }
            }
        }
        return new int[]{};
    }

}