package ru.itmo.ctddev.ionov.expression;

public class CheckedMultiply extends AbstractCalc implements TripleExpression {
    public CheckedMultiply(TripleExpression firstNum, TripleExpression secondNum) {
        super(firstNum, secondNum);
    }

    private void check(int x, int y) throws Exception {
        if (x > 0 && y > 0) {
            if (x > Integer.MAX_VALUE / y || y > Integer.MAX_VALUE / x) {
                throw new OverflowException("overflow");
            }
        } else if (x < 0 && y < 0) {
            if (x < Integer.MAX_VALUE / y || y < Integer.MAX_VALUE / x) {
                throw new OverflowException("overflow");
            }
        } else if (x > 0 && y < 0) {
            if (y < Integer.MIN_VALUE / x) {
                throw new OverflowException("overflow");
            }
        } else if (x < 0 && y > 0) {
            if (x < Integer.MIN_VALUE / y) {
                throw new OverflowException("overflow");
            }
        }
    }

    public int evaluateImpl(int x, int y) throws Exception {
        check(x, y);
        return x * y;
    }
}