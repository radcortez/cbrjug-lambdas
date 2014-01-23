package pt.coimbra.jug.lambda.mylambda;

/**
 * @author Roberto Cortez
 */
public class LambdaImplementation {
    public static void main(String[] args) {
        // Lambdas can be used whenever you have an interface with only 1 abstract method.
        printSomething("test", System.out::println);

        LambdaInterface lambdaInterface = System.out::println;
        lambdaInterface.printDefault("another");
    }

    private static void printSomething(String x, LambdaInterface lambaInterface) {
        lambaInterface.printSomething(x);
    }
}
