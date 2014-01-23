package pt.coimbra.jug.lambda.defmethods;

/**
 * @author Roberto Cortez
 */
public class Action implements ActionA, ActionB {
    // If you implement several interfaces with different default implementations you need to override and tell which
    // one you want to use
    @Override
    public void doSomething() {
        ActionB.super.doSomething();
    }

    public static void main(String[] args) {
        Action action = new Action();
        action.doSomething();
        action.doAnother();
    }
}
