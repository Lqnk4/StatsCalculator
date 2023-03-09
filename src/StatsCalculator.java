import java.util.Arrays;
import java.util.List;

public record StatsCalculator(double[] values, double[] sortedValues, List<Double> outliers) {

    /**
     * Records require a default all arguements constructor, just don't use this one
     * @param values data
     * @param sortedValues NA
     * @param outliers NA
     */
    public StatsCalculator(double[] values, double[] sortedValues, List<Double> outliers) {
        this.values = values;
        this.sortedValues = Arrays.stream(values).sorted().toArray();
        this.outliers = Util.findOutliers(sortedValues);
    }

    /**
     * Creates a new StatsCalculator
     * @param values data
     */
    public StatsCalculator(double[] values) {
        this(values, Arrays.stream(values).sorted().toArray(), Util.findOutliers(Arrays.stream(values).sorted().toArray()));
    }

    /**
     * Creates an empty StatsCalculator with an empty double array
     */
    public StatsCalculator() {
        this(new double[20], new double[20], Util.findOutliers(new double[20]));
    }

    /**
     * Data is always sorted, method exists for no reason
     * @param data data to be sorted
     * @return sorted Array
     */
    public double[] sortData(double[] data) {
        double[] newArr = data;
        Arrays.sort(newArr);
        return newArr;
    }

    /**
     * @return maximum of the data
     */
    public double calculateMax() {
        return Arrays.stream(sortedValues).max().getAsDouble();
    }

    /**
     * Gets the minimum of the data
     * @return minimum
     */
    public double calculateMin() {
        return Arrays.stream(sortedValues).min().getAsDouble();
    }

    /**
     * Calculates the first quartile of the data
     * @return first quartile
     */
    public double calculateFirstQuartile() {
        return Util.quartile(sortedValues, 25);
    }

    /**
     * Calculates the third quartile of the data
     * @return third quartile
     */
    public double calculateThirdQuartile() {
        return Util.quartile(sortedValues, 75);
    }

    /**
     * Calculates the median of the data
     * @return median
     */
    public double calculateMedian() {
        return Util.median(sortedValues);
    }

    /**
     * Calculates the sum of the data
     * @return sum
     */
    public double calculateSum() {
        return Arrays.stream(values).sum();
    }

    public double calculateAverage() {
        return Arrays.stream(values).average().getAsDouble();
    }


    /**
     * Prints Array Data
     */
    public void print() {
        Util.printArr(values);
    }

    /**
     * Prints Sorted Array Data
     */
    public void printSorted() {
        Util.printArr(sortedValues);
    }

    /**
     * Prints a convenient Summary of the data
     */
    public void printFiveNumberSummary() {
        System.out.print("Your data is: \n" );
        print();
        System.out.print("\nYour sorted data is: ");
        printSorted();
        System.out.println("\nYour five number summary is:");
        System.out.println("\tMinimum: " + calculateMin());
        System.out.println("\tFirst Quartile: " + calculateFirstQuartile());
        System.out.println("\tMedian: " + calculateMedian());
        System.out.println("\tThird Quartile: " + calculateThirdQuartile());
        System.out.println("\tMaximum: " + calculateMax());
        System.out.print("\tOutliers: ");
        Util.printList(outliers);
    }

    /**
     * Contains internal class methods
     */
    private static class Util{
        /**
         * Retrive the quartile value from a **sorted** array
         * .
         * @param values THe array of data
         * @param lowerPercent The percent cut off. For the lower quartile use 25,
         *      for the upper-quartile use 75
         * @return Quartile value
         */
        private static double quartile(double[] values, double lowerPercent) {
            return values[(int) Math.round(values.length * lowerPercent / 100)];
        }

        /**
         * Retrieve the median value from a **sorted** array
         * @param values The array of data
         * @return median
         */
        private static double median(double[] values) {
            return values.length % 2 == 0 ? values[values.length / 2] : (values[(values.length - 1) / 2] + values[(values.length + 1)/2])/2;

        }

        /**
         * Finds Outliers for the array using 1.5IQR
         * @param sortedValues **sorted** array to be used
         * @return List of Outliers
         */
        private static List<Double> findOutliers(double[] sortedValues) {
            double onePointFiveIQR = quartile(sortedValues, 75)-quartile(sortedValues, 25) * 1.5;
            return Arrays.stream(sortedValues).filter((d) -> quartile(sortedValues, 25) - d > onePointFiveIQR || d - quartile(sortedValues, 75) > onePointFiveIQR).boxed().toList();
        }

        /**
         * prints each element of a list of doubles
         * @param list of doubles
         */
        private static void printList(List<Double> list) {
            list.forEach((d) -> System.out.print(d + " "));
        }

        /**
         * prints each element of an array of doubles
         * @param values list of doubles
         */
        private static void printArr(double[] values) {
            Arrays.stream(values).forEach((d) -> System.out.print(d + " "));
        }
    }
}