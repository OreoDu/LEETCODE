import java.io.*;
import java.util.*;

class prampSalesPath {

    static class Node {

        int cost;
        Node[] children;
        Node parent;

        Node(int cost) {
            this.cost = cost;
            children = null;
            parent = null;
        }
    }

    static class SalesPath {

        int getCheapestCost(Node rootNode) {
            if (rootNode.children == null) return rootNode.cost;

            int finalRes = Integer.MAX_VALUE;
            for (Node children: rootNode.children) {
                int res = getCheapestCost(children);
                finalRes = Math.min(res, finalRes);
            }
            return finalRes + rootNode.cost;
        }
    }

    public static void main(String[] args) {
        Node n1 = new Node(0);
        Node n2 = new Node(5); //4
        Node n3 = new Node(3); //1
        Node n4 = new Node(6); //0
        n1.children = new Node[]{n2, n3, n4};
        Node n5 = new Node(4);
        n2.children = new Node[]{n5};
        Node n6 = new Node(1);
        n3.children = new Node[]{n6};
        Node n7 = new Node(0);
        n4.children = new Node[]{n7};

        SalesPath s = new SalesPath();
        int res = s.getCheapestCost(n1);
        System.out.println(res);
    }
}