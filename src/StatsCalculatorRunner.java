import java.util.ArrayList;
import java.util.Scanner;

public class StatsCalculatorRunner {
    public static void main(String... args) {
        System.out.println("Type your data space separated and then end with any non number character");
        Scanner sc = new Scanner(System.in);
        ArrayList<Double> list = new ArrayList<>();
        while(sc.hasNextDouble()) {
            list.add(sc.nextDouble());
        }
        StatsCalculator calc = new StatsCalculator(list.stream().mapToDouble(Double::doubleValue).toArray());
        calc.printFiveNumberSummary();
        sc.close();
    }
}
