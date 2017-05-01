package mycalculator.app.hansol.org.mycalculatoradapter;

/**
 * Created by uaer-01 on 2017-05-01.
 */

public class CalcData {

    public static final int TYPE_ADD = 0x01;
    public static final int TYPE_SUBTRACT = 0x02;
    public static final int TYPE_MULTIPLY = 0x03;
    public static final int TYPE_DIVDE = 0x04;

    private int op1;
    private int op2;
    private int type;
    private int operationResult;

    public CalcData(int op1, int op2, int type, int operationResult) {
        this.op1 = op1;
        this.op2 = op2;
        this.type = type;
        this.operationResult = operationResult;
    }

    public int getOp1() {
        return op1;
    }

    public void setOp1(int op1) {
        this.op1 = op1;
    }

    public int getOp2() {
        return op2;
    }

    public void setOp2(int op2) {
        this.op2 = op2;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(int operationResult) {
        this.operationResult = operationResult;
    }

    public String getType2StrType() {
        switch (this.type) {
            case TYPE_ADD:
                return "+";
            case TYPE_SUBTRACT:
                return "-";
            case TYPE_MULTIPLY:
                return "X";
            case TYPE_DIVDE:
                return "/";
        }
        return "??";
    }

    @Override
    public String toString() {
        return this.op1 + " " + getType2StrType() + " " + this.op2 + " = " + this.operationResult;
    }
}
