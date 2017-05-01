package mycalculator.app.hansol.org.mycalculator;

/**
 * Created by uaer-01 on 2017-05-01.
 */

public interface Calculator {

    int add(int op1, int op2) throws UnimplementedException;
    int subtract(int op1, int op2) throws UnimplementedException;
    int multiply(int op1, int op2) throws UnimplementedException;
    int divide(int op1, int op2) throws UnimplementedException;
}
