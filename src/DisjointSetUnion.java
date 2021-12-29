import java.util.Arrays;

public class DisjointSetUnion {

    private final int[][] arr;
    private final int n;
    private final int m;

    public DisjointSetUnion(int n, int m) {
        this.arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = i * m + j;
            }
        }
        this.n = n;
        this.m = m;
    }

    public int find(int i, int j) {
        return arr[i][j];
    }

    public void union(int i1, int j1, int i2, int j2) {
        if (arr[i1][j1] == arr[i2][j2]) {
            return;
        }
        int t = arr[i1][j1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == t) {
                    arr[i][j] = arr[i2][j2];
                }
            }
        }
    }

    public void show() {
        for (int i = 1; i < n - 1; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
    }

    public void unionRow(int rowNumber) {
        int t = arr[rowNumber][0];
        for (int i = 1; i < m; i++) {
            arr[rowNumber][i] = t;
        }
    }
}
