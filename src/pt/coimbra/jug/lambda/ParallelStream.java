package pt.coimbra.jug.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roberto Cortez
 */
public class ParallelStream {
    public static void main(String[] args) {
        // 1000 objectos que vou ter que processar
        List<Stock> stocks = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            stocks.add(new Stock());
        }

        // De forma sequencial são 1000 invocações a 10 milisegundos cada uma. No mínimo 10 segundos.
        long start = System.currentTimeMillis();
        stocks.stream().forEach(Stock::getValue);
        long end = System.currentTimeMillis();
        System.out.println("sequencial: " + (end - start));

        // Paralelo. Depende do números de processadores da máquina que está a correr o código. No pior caso será igual
        // ao anterior.
        start = System.currentTimeMillis();
        stocks.parallelStream().forEach(Stock::getValue);
        end = System.currentTimeMillis();
        System.out.println("paralelo: " + (end - start));
    }

    // Apenas um objecto para simular o tempo de espera de processamento de uma invocação
    private static class Stock {
        public double getValue() {
            try {
                // Espera 10 milisegundos
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }
}
