package mycalculator.app.hansol.org.mycalculatoradapter;

/**
 * Created by uaer-01 on 2017-05-01.
 */

public class PlainCalculator extends CalculatorAdaptor {
    @Override
    public int add(int a, int b) throws UnimplementedException {
        int result = a + b;
        addHistory(a, b, CalcData.TYPE_ADD, result);

        return result;
    }
}
