package ru.itmo.ctddev.ionov.expression;

public class CheckedSubtract extends AbstractCalc implements TripleExpression {
    public CheckedSubtract(TripleExpression firstNum, TripleExpression secondNum) {
        super(firstNum, secondNum);
    }

    private void check(int x, int y) throws Exception {
        if ((y > 0 && x < Integer.MIN_VALUE + y) || (y < 0 && x > Integer.MAX_VALUE + y)) {
            throw new OverflowException("overflow");
        }
    }

    public int evaluateImpl(int x, int y) throws Exception {
        check(x, y);
        return x - y;
    }
}