package mycalculator.app.hansol.org.mycalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivty extends AppCompatActivity implements View.OnClickListener{
    EditText mOperand1EditText;
    EditText mOperand2EditText;
    TextView mOperationResult;
    Button mPlusButton;
    Button mMinusButton;
    Button mMultiplyButton;
    Button mDivideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activty);

        mOperand1EditText = (EditText)findViewById(R.id.et_operand1);
        mOperand2EditText = (EditText)findViewById(R.id.et_operand2);
        mOperationResult = (TextView)findViewById(R.id.tv_operation_result);

        (mPlusButton = (Button)findViewById(R.id.button_plus)).setOnClickListener(this);
        (mMinusButton = (Button)findViewById(R.id.button_minus)).setOnClickListener(this);
        (mMultiplyButton = (Button)findViewById(R.id.button_multiply)).setOnClickListener(this);
        (mDivideButton = (Button)findViewById(R.id.button_divide)).setOnClickListener(this);
    }

    private void addExcute() {
        String sOperand1 = mOperand1EditText.getText().toString();
        String sOperand2 = mOperand2EditText.getText().toString();
        int iOperand1 = 0;
        int iOperand2 = 0;

        try {
            iOperand1 = Integer.parseInt(sOperand1);
            iOperand2 = Integer.parseInt(sOperand2);
            Calculator calculator = new PlainCalculator();
            int operationResult = calculator.add(iOperand1, iOperand2);

            mOperationResult.setText(String.valueOf(operationResult));

            calculator = new ToastCalculator(getApplicationContext());

            calculator.add(iOperand1, iOperand2);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    private void subtractExcute() {
        String sOperand1 = mOperand1EditText.getText().toString();
        String sOperand2 = mOperand2EditText.getText().toString();
        int iOperand1 = 0;
        int iOperand2 = 0;

        try {
            iOperand1 = Integer.parseInt(sOperand1);
            iOperand2 = Integer.parseInt(sOperand2);
            Calculator calculator = new PlainCalculator();
            int operationResult = calculator.subtract(iOperand1, iOperand2);

            mOperationResult.setText(String.valueOf(operationResult));

            calculator = new ToastCalculator(getApplicationContext());

            calculator.subtract(iOperand1, iOperand2);
        } catch (UnimplementedException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch(viewId) {
            case R.id.button_plus:
                addExcute();
                break;
            case R.id.button_minus:
                subtractExcute();
                break;
            case R.id.button_multiply:
                //TODO 3 multiply
                break;
            case R.id.button_divide:
                //TODO 4 divide
                break;
        }
    }
}
