package tree.level3;

import java.util.*;

public class PGS_길찾기_게임 {
	class Node {
		int number;
		int y;
		int x;
		Node left;
		Node right;

		Node(int number, int y, int x) {
			this.number = number;
			this.y = y;
			this.x = x;
		}

		void insert(Node node) {
			if (this.x > node.x) {
				if (this.left == null) this.left = node;
				else this.left.insert(node);
			}
			else if (this.x < node.x) {
				if (this.right == null) this.right = node;
				else this.right.insert(node);
			}
		}

		void preorder(List<Integer> preorder) {
			preorder.add(this.number);
			if (this.left != null) this.left.preorder(preorder);
			if (this.right != null) this.right.preorder(preorder);
		}

		void postorder(List<Integer> postorder) {
			if (this.left != null) this.left.postorder(postorder);
			if (this.right != null) this.right.postorder(postorder);
			postorder.add(this.number);
		}
	}

	public int[][] solution(int[][] nodeinfos) {
		int n = nodeinfos.length;
		List<Node> nodes = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int[] nodeinfo = nodeinfos[i];
			nodes.add(new Node(i + 1, nodeinfo[1], nodeinfo[0]));
		}

		nodes.sort((o1, o2) -> o2.y == o1.y ? o1.x - o2.x : o2.y - o1.y);

		Node root = nodes.get(0);
		for (int i = 1; i < n; i++) {
			Node node = nodes.get(i);
			root.insert(node);
		}

		List<Integer> preorder = new ArrayList<>();
		List<Integer> postorder = new ArrayList<>();
		root.preorder(preorder);
		root.postorder(postorder);

		return new int[][]{
			preorder.stream().mapToInt(Integer::intValue).toArray(),
			postorder.stream().mapToInt(Integer::intValue).toArray()
		};
	}
}
