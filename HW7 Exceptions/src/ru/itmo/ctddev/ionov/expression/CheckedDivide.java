package ru.itmo.ctddev.ionov.expression;

public class CheckedDivide extends AbstractCalc implements TripleExpression {
    public CheckedDivide(TripleExpression firstNum, TripleExpression secondNum) {
        super(firstNum, secondNum);
    }

    private void check(int x, int y) throws Exception {
        if (y == 0) {
            throw new DivideByZeroException();
        } else if (y == -1 && x == Integer.MIN_VALUE) {
            throw new OverflowException("overflow");
        }
    }

    public int evaluateImpl(int x, int y) throws Exception {
        check(x, y);
        return x / y;
    }
}