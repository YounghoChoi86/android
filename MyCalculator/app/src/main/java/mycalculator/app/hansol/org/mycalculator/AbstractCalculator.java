package mycalculator.app.hansol.org.mycalculator;

/**
 * Created by uaer-01 on 2017-05-01.
 */

public abstract class AbstractCalculator implements Calculator {

    @Override
    public abstract int add(int op1, int op2) throws UnimplementedException;

    @Override
    public int subtract(int op1, int op2) throws UnimplementedException {
        throw new UnimplementedException("substract is not implemented");
    }

    @Override
    public int multiply(int op1, int op2) throws UnimplementedException {
        throw new UnimplementedException("multiply is not implemented");
    }

    @Override
    public int divide(int op1, int op2) throws UnimplementedException {
        throw new UnimplementedException("divide is not implemented");
    }
}
