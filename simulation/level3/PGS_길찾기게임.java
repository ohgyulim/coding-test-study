package simulation.level3;

import java.util.*;

class PGS_길찾기게임 {
    class Node {
        int x,y,n;
        Node parent = null;
        Node left = null;
        Node right = null;
        public Node(int x, int y, int n) {
            this.x = x;
            this.y = y;
            this.n = n;
        }
    }

    Map<Integer, List<int[]>> xMap = new HashMap<>(); // y = {x, i};
    Node root;
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][nodeinfo.length];

        int maxY = 0;
        for (int[] node : nodeinfo) {
            if (maxY < node[1]) maxY = node[1];
        }

        for (int y=0; y<=maxY; y++) {
            xMap.put(y, new ArrayList<>());
        }
        // xMap.get(i)는 y가 i인 노드들을 x좌표 순으로 정렬해 놓은 것
        for (int i=0; i<nodeinfo.length; i++) {
            int[] node = nodeinfo[i];
            int y = node[1];
            xMap.get(y).add(new int[] {node[0], i+1});
        }

        for (int y=0; y<=maxY; y++) {
            Collections.sort(xMap.get(y), (o1, o2) -> o1[0] - o2[0]);
        }

        root = new Node(xMap.get(maxY).get(0)[0], maxY, xMap.get(maxY).get(0)[1]);

        List<Node> ancestors = new ArrayList<>();
        List<Node> parents = new ArrayList<>(); // 현재 부모가 될 노드들
        List<Node> currents = new ArrayList<>();

        ancestors.add(root);
        int Y = 0;
        // 루트와 루트의 직계자식 구조 생성
        for (int y=maxY-1; y>=0; y--) {
            List<int[]> list = xMap.get(y);
            if (list.size() == 0) continue;
            for (int[] node : list) {
                parents.add(new Node(node[0], y, node[1]));
            }

            if (parents.size() > 1) {
                Node left = parents.get(0);
                Node right = parents.get(1);

                root.left = left;
                root.right = right;

                left.parent = root;
                right.parent = root;
            } else if (parents.get(0).x < root.x) {
                Node left =parents.get(0);
                root.left = left;
                left.parent = root;
            } else {
                Node right =parents.get(0);
                root.right = right;
                right.parent = root;
            }
            Y = y;
            break;
        }

        //
        for (int y=Y-1; y>=0; y--) {
            List<int[]> list = xMap.get(y);
            if (list.size() == 0) continue; // 현재 y좌표에 노드가 있을 때까지 y를 감소시킨다.
            currents = new ArrayList<>(); //y좌표에 노드들
            for (int[] node : list) {
                currents.add(new Node(node[0], y, node[1]));
            }

            int parentIdx = 0;
            int currentIdx = 0;
            // currents의 앞에서부터 노드를 뽑아서 해당 노드의 부모를 찾는다.
            // parents의 앞에서부터 현재 노드의 부모인지 확인한다.
            // 후보 노드의 x값이 현재 노드의 x값보다 크면 부모 노드이다.
            // 후보 노드의 x값이 현재 노드의 x값보다 작으면
            //      후보의 조상 중 후보보다 x값이 큰 조상의 x 값보다 현재 노드의 x값이 더 작으면 부모 노드이다.
            //      후보의 조상 중 후보보다 x값이 큰 조상의 x 값보다 현재 노드의 x값이 더 크면 부모가 아니다.
            //      후보의 조상 중 후보보다 x값이 큰 조상이 없으면 무조건 부모 노드이다. (오른쪽으로 일자이므로)
            //          자식 노드가 left이면 부모는 무조건 자식 노드보다 큼.
            //          자식 노드가 right인데 root기준 왼쪽에 있으면 최악의 경우라도 root가 자식보다 큰 조상이됨
            //          자식 노드가 right인데 root기준 오른쪽에 있으면 최악의 경우 큰 조상이 없음 -> 이 경우가 오른쪽으로 일자
            // 부모 노드이면 현재 노드와 부모 노드를 연결시키고 currentIdx ++하여 다음 노드에 대해서 부모 노드 찾기
            //      이 때 만약 현재 노드가 부모 노드의 left이면 다음 노드 또한 right도 부모가 같을 수 있으므로 parentIdx는 증가시키지 않음
            //      현재 노드가 right이면 parentIdx 증가
            // 부모 노드가 아니면 parentIdx를 증가시키며 부모 노드 찾기
            while (currentIdx < currents.size()) {
                Node node = currents.get(currentIdx);

                Node parent = parents.get(parentIdx);
                if  (node.x < parent.x) {
                    parent.left = node;
                    node.parent = parent;
                    currentIdx ++;
                    //System.out.println(parent.n + "의 자식: " + node.n);
                } else {
                    Node ancestor = parent.parent;
                    while (ancestor != null && ancestor.x < parent.x) {
                        ancestor = ancestor.parent;
                    }
                    if (ancestor == null || ancestor.x > node.x ) {
                        parent.right = node;
                        node.parent = parent;
                        currentIdx ++;
                        parentIdx ++;
                        //System.out.println(parent.n + "의 자식: " + node.n);
                    } else {
                        parentIdx ++;
                    }
                }
            }
            ancestors = parents;
            parents = currents;
        }

        preOrder(root, answer[0], 0);
        postOrder(root, answer[1], 0);
        return answer;
    }


    public int preOrder(Node node, int[] order, int idx) {
        order[idx] = node.n;
        if (node.left != null) idx = preOrder(node.left, order, ++idx);
        if (node.right != null) idx = preOrder(node.right, order, ++idx);
        return idx;
    }

    public int postOrder(Node node, int[] order, int idx) {
        if (node.left != null) idx = postOrder(node.left, order, idx);
        if (node.right != null) idx = postOrder(node.right, order, idx);
        order[idx] = node.n;
        return idx+1;
    }
}
