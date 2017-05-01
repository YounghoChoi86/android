package mycalculator.app.hansol.org.mycalculatoradapter;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by uaer-01 on 2017-05-01.
 */

public class ToastCalculator extends CalculatorAdaptor {
    Context mContext;

    public ToastCalculator(Context context) {
        this.mContext = context;
    }
    @Override
    public int add(int op1, int op2) throws UnimplementedException {
        Toast.makeText(this.mContext, String.valueOf(op1 + op2), Toast.LENGTH_LONG).show();
        return op1 + op2;
    }
}
