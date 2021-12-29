
public class Percolation {

    private final DisjointSetUnion dsu;
    private final int n;
    private int numberOfOpenedCells;
    private final boolean[][] open;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Grid size should be positive");
        }
        this.n = n;
        dsu = new DisjointSetUnion(n + 2, n);
        open = new boolean[n][n];
        dsu.unionRow(0);
        dsu.unionRow(n + 1);
        numberOfOpenedCells = 0;
    }

    public void show() {
        dsu.show();
    }

    public void open(int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= n) {
            throw new IllegalArgumentException("Indexes are out of bounds");
        }
        open[i][j] = true;
        numberOfOpenedCells++;
        if (i == 0) {
            dsu.union(0, 0, 1, j);
        }
        if (i == n - 1) {
            dsu.union(n + 1, 0, n, j);
        }
        if (i > 0 && open[i - 1][j]) {
            dsu.union(i + 1, j, i, j);
        }
        if (i < n - 1 && open[i + 1][j]) {
            dsu.union(i + 1, j, i + 2, j);
        }

        if (j > 0 && open[i][j - 1]) {
            dsu.union(i + 1, j, i + 1, j - 1);
        }
        if (j < n - 1 && open[i][j + 1]) {
            dsu.union(i + 1, j, i + 1, j + 1);
        }
    }

    public boolean percolates() {
        return dsu.find(0, 0) == dsu.find(n + 1, 0);
    }

    public int getNumberOfOpenedCells() {
        return numberOfOpenedCells;
    }
}

