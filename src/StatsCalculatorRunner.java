public class StatsCalculatorRunner {
    public static void main(String... args) {
        StatsCalculator calc = new StatsCalculator(new double[] {0, 12, 4f, 3d, 6.3, 19.2, 100});
        calc.printFiveNumberSummary();
    }
}
