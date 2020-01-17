package com.zxftech.forest;

public class Simple {
    static class Node {
        private Node left;
        private Node right;
        private String data;

        public Node(String data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

    /**
     * pre      A B D E G H C F
     * middle   D B G H E A C F
     * behind   D H G E B F C A
     *
     * @param args
     */
    public static void main(String[] args) {
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        Node f = new Node("F");
        Node g = new Node("G");
        Node h = new Node("H");
        a.setLeft(b);
        a.setRight(c);
        b.setLeft(d);
        b.setRight(e);
        e.setLeft(g);
        g.setRight(h);
        c.setRight(f);

        preTraversal(a);
        System.out.println();
        middleTraversal(a);
        System.out.println();
        behindTraversal(a);
    }


    public static void preTraversal(Node node) {
        System.out.print(node.getData() + " ");
        if (node.getLeft() != null) {
            preTraversal(node.getLeft());
        }
        if (node.getRight() != null) {
            preTraversal(node.getRight());
        }
    }

    public static void middleTraversal(Node node) {
        Node left = node.getLeft();
        Node right = node.getRight();
        if (left != null) {
            middleTraversal(left);
        }
        // 下面不要加到else里，否则出不了结果，为啥呢
        // 因为对于最左边节点的上个节点，左节点可定不是null，
        // 如果加到else里，意思是仅当当前节点左节点null时才打印，当然就错了
        System.out.print(node.getData() + " ");
        if (right != null) {
            middleTraversal(right);
        }
    }

    public static void behindTraversal(Node node) {
        Node left = node.getLeft();
        Node right = node.getRight();
        if (left != null) {
            behindTraversal(left);
        }
        if (right!=null){
            behindTraversal(right);
        }
        System.out.print(node.getData()+" ");
    }

}
