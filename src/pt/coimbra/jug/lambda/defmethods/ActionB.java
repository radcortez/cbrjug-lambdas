package pt.coimbra.jug.lambda.defmethods;

/**
 * @author Roberto Cortez
 */
public interface ActionB {
    default void doSomething() {
        System.out.println("pt.coimbra.jug.lambda.defmethods.ActionB.doSomething");
    }
}
