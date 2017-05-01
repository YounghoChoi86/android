package mycalculator.app.hansol.org.mycalculator;

/**
 * Created by uaer-01 on 2017-05-01.
 */

public class PlainCalculator extends AbstractCalculator{
    @Override
    public int add(int a, int b) throws UnimplementedException {
        return a + b;
    }
}
