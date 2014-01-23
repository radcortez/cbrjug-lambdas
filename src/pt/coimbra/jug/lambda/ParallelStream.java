package pt.coimbra.jug.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roberto Cortez
 */
public class ParallelStream {
    public static void main(String[] args) {
        // 1000 objects to process.
        List<Stock> stocks = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            stocks.add(new Stock());
        }

        sequentialStream(stocks);
        parallelStream(stocks);
    }

    private static void sequentialStream(List<Stock> stocks) {
        long start = System.currentTimeMillis();

        // In a sequential way, all the 1000 invocations are processed, taking at least 10 ms. We need at least 10 s to
        // process all the objects.
        stocks.stream().forEach(Stock::getValue);

        long end = System.currentTimeMillis();
        System.out.println("sequential: " + (end - start));
    }

    private static void parallelStream(List<Stock> stocks) {
        long start = System.currentTimeMillis();

        // Parallel stream. The processing now depends on how many cores the machine has.
        stocks.parallelStream().forEach(Stock::getValue);

        long end = System.currentTimeMillis();
        System.out.println("parallel: " + (end - start));
    }

    // Just an object to simulate a waiting time.
    private static class Stock {
        public double getValue() {
            try {
                // Wait 10 ms.
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }
}
