import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        int n = 2;
        int T = 100000;
        Percolation hashtag;
        List<Double> percolationThresholds = new ArrayList<>();

        for (int i = 0; i < T; i++) {
            hashtag = new Percolation(n);
            List<Integer> indexes = getShuffledArrayOfIndexes(n * n);
            int ind = 0;
            while (!hashtag.percolates()) {
                int cell = indexes.get(ind);
                hashtag.open(cell / n, cell % n);
                ind++;
            }
            double tmp = (double) (hashtag.getNumberOfOpenedCells()) / (n * n);
            percolationThresholds.add(tmp);
        }
        double mean = mean(percolationThresholds);
        double standardDerivation = standardDeviation(percolationThresholds);
        System.out.println(String.join(": ", "Mean", String.valueOf(mean)));
        System.out.println(String.join(": ", "Standard Derivation", String.valueOf(standardDerivation)));

    }

    public static List<Integer> getShuffledArrayOfIndexes(int n) {
        List<Integer> indexes = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            indexes.add(j);
        }
        Collections.shuffle(indexes);
        return indexes;
    }

    public static double mean(List<Double> arr) {
        return arr.stream().mapToDouble(Double::doubleValue).sum() / arr.size();
    }

    public static double standardDeviation(List<Double> arr) {
        double mean = mean(arr);
        return arr.stream().map(p -> (p - mean) * (p - mean)).mapToDouble(Double::doubleValue).sum() / (arr.size() - 1);
    }

}
