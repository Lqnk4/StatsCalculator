public class Util{
    /**
     * Retrive the quartile value from a **sorted** array
     * .
     * @param values THe array of data
     * @param lowerPercent The percent cut off. For the lower quartile use 25,
     *      for the upper-quartile use 75
     * @return
     */
    public static double quartile(double[] values, double lowerPercent) {

        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("The data array either is null or does not contain any data.");
        }



        int n = (int) Math.round(values.length * lowerPercent / 100);

        return values[n];

    }

    public static double median(double[] values) {
        return values.length % 2 == 0 ? values[values.length / 2] : (values[(values.length - 1) / 2] + values[(values.length + 1)/2])/2;

    }
}