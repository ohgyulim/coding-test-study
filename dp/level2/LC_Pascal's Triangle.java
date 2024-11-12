class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        for(int i = 0; i < numRows; i++){
            List<Integer> row = new ArrayList<>();
            if(i == 0){
                row.add(1);
                triangle.add(row);
            }else if(i == 1){
                row.add(1);
                row.add(1);
                triangle.add(row);
            }else{
                for(int j = 0; j <= i ; j++){
                    if(j==0 || j==i){
                        row.add(1);
                    }else{
                        row.add(triangle.get(i-1).get(j-1)+triangle.get(i-1).get(j));
                    }
                }
                triangle.add(row);
            }

        }
        return triangle;
    }
}