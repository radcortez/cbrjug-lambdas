package pt.coimbra.jug.lambda.mylambda;

/**
 * @author Roberto Cortez
 */
@FunctionalInterface
public interface LambdaInterface {
    void printSomething(String text);

    default void printDefault(String text) {
        System.out.println(text);
    }
}
