package Other.UnionFind;

public class UnionFind {
    private int[] roots;
    private int[] size;
    public int rootsNumber;

    public UnionFind(int N){
        rootsNumber = N;
        roots = new int[N];
        size = new int[N];
        for (int i = 0; i<N; i++) {
            roots[i] = i;
            size[i] = 1;
        }
    }

    public boolean connected(int i, int j){
        return root(i) == root(j);
    }

    public int root(int i){
        int node = i;
        while(roots[i]!=i) i = roots[i];
        while(node!= roots[node]) {
            int temp = roots[node];
            roots[node] = i;
            node = temp;
        }
        return i;
    }

    public void union(int i, int j) {
        int ri = roots[i];
        int rj = roots[j];
        if (size[ri]<size[rj]) {
            roots[ri] = rj;
            size[rj] += size[ri];
        }
        else {
            roots[rj] = ri;
            size[ri] = size[rj];
        }
        // Once a connection is formed, the number of the roots will decrease one.
        rootsNumber--;
    }
}
