package ru.itmo.ctddev.ionov.expression;

public class CheckedAdd extends AbstractCalc implements TripleExpression {
    public CheckedAdd(TripleExpression firstNum, TripleExpression secondNum) {
        super(firstNum, secondNum);
    }

    private void check(int x, int y) throws Exception {
        if ((x > 0 && y > Integer.MAX_VALUE - x) || (x < 0 && y < Integer.MIN_VALUE - x)) {
            throw new OverflowException("overflow");
        }
    }

    public int evaluateImpl(int x, int y) throws Exception {
        check(x, y);
        return x + y;
    }
}