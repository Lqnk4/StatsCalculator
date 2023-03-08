import java.util.Arrays;

public record StatsCalculator(double[] values, double[] sortedValues) {
    public StatsCalculator(double[] values) {
        this(values, Arrays.stream(values).sorted().toArray());
    }

    public StatsCalculator() {
        this(new double[20], new double[20]);
    }

    /**
     * Data is always sorted, method exists for no reason
     */
    public void sortData() {}

    public double calculateMax() {
        return Arrays.stream(sortedValues).max().getAsDouble();
    }

    public double calculateMin() {
        return Arrays.stream(sortedValues).min().getAsDouble();
    }

    public double calculateFirstQuartile() {
        return Util.quartile(sortedValues, 25);
    }

    public double calculateThirdQuartile() {
        return Util.quartile(sortedValues, 75);
    }

    public double calculateMedian() {
        return Util.median(sortedValues);
    }

    public double calculateSum() {
        return Arrays.stream(values).sum();
    }







}