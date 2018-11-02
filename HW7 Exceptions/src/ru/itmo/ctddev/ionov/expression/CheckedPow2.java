package ru.itmo.ctddev.ionov.expression;

public class CheckedPow2 implements TripleExpression {
    private TripleExpression tmp;

    public CheckedPow2(TripleExpression firstNum) {
        tmp = firstNum;
    }

    private void check(int x) throws Exception {
        if (x < 0 || x > 31) {
            throw new OverflowException("overflow");
        }
    }

    private int evaluateImpl(int x) {
        return (1 << x);
    }

    public int evaluate(int x, int y, int z) throws Exception {
        check(tmp.evaluate(x, y, z));
        return evaluateImpl(tmp.evaluate(x, y, z));
    }
}
