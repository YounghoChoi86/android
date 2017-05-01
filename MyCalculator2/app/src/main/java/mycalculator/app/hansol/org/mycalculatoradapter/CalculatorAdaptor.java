package mycalculator.app.hansol.org.mycalculatoradapter;

import java.util.ArrayList;

/**
 * Created by uaer-01 on 2017-05-01.
 */

public abstract class CalculatorAdaptor implements Calculator {
    private ArrayList<CalcData> calcHistory = new ArrayList<CalcData>();

    public void addHistory(int op1, int op2, int type, int operationResult) {
        CalcData calcData = new CalcData(op1, op2, type, operationResult);

        calcHistory.add(calcData);
    }
    @Override
    public void cleaHistory() {
        this.calcHistory.clear();
    }
    @Override
    public ArrayList<CalcData> getHistory() {

        return this.calcHistory;
    }

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
