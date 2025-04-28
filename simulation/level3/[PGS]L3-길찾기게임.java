import java.util.*;

class Solution {
    static class Node {
        int x, y, num;
        Node left, right;

        Node(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    static List<Integer> preOrderList;
    static List<Integer> postOrderList;

    public int[][] solution(int[][] nodeinfo) {
        List<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < nodeinfo.length; i++) {
            nodeList.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1));
        }

        //y 내림차순.,같으면 x 오름차순
        nodeList.sort((a, b) -> {
            if (a.y == b.y) {
                return Integer.compare(a.x, b.x);
            }
            return Integer.compare(b.y, a.y);
        });

        Node root = nodeList.get(0);
        for (int i = 1; i < nodeList.size(); i++) {
            insert(root, nodeList.get(i));
        }

        preOrderList = new ArrayList<>();
        postOrderList = new ArrayList<>();
        preorder(root);
        postorder(root);

        int[][] answer = new int[2][nodeList.size()];
        for (int i = 0; i < nodeList.size(); i++) {
            answer[0][i] = preOrderList.get(i);
            answer[1][i] = postOrderList.get(i);
        }
        return answer;
    }

    private void insert(Node parent, Node child) {
        if (child.x < parent.x) {
            if (parent.left == null) {
                parent.left = child;
            } else {
                insert(parent.left, child);
            }
        } else {
            if (parent.right == null) {
                parent.right = child;
            } else {
                insert(parent.right, child);
            }
        }
    }

    private void preorder(Node node) {
        if (node == null) return;
        preOrderList.add(node.num);
        preorder(node.left);
        preorder(node.right);
    }

    private void postorder(Node node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        postOrderList.add(node.num);
    }
}
