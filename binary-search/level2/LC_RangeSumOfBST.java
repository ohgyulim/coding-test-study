class LC_RangeSumOfBST {
    public int rangeSumBST(TreeNode root, int low, int high) {
        //null이면 합에 지장없게 0 리턴
        if (root == null) {
            return 0;
        }
        //최소보다 작으면 우측노드에서 탐색
        if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        }
        //최대보다 크면 좌측노드에서 탐색
        if (root.val > high) {
            return rangeSumBST(root.left, low, high);
        }
        //범위에 속하는경우 해당 노드의 값 + 좌측,우측 노드의 탐색값 반환(재귀로 호출)
        return root.val + rangeSumBST(root.right, low, high) + rangeSumBST(root.left, low, high);
    }
}