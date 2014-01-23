package pt.coimbra.jug.lambda.defmethods;

/**
 * @author Roberto Cortez
 */
public interface ActionA {
    default void doSomething() {
        System.out.println("pt.coimbra.jug.lambda.defmethods.ActionA.doSomething");
    }

    default void doAnother() {
        System.out.println("pt.coimbra.jug.lambda.defmethods.ActionA.doAnother");
    }
}
