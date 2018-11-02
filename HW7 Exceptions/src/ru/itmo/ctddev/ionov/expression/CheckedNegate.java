package ru.itmo.ctddev.ionov.expression;

public class CheckedNegate implements TripleExpression {
    private TripleExpression tmp;

    public CheckedNegate(TripleExpression Num) {
        tmp = Num;
    }

    private void check(int x) throws Exception {
        if (x <= Integer.MIN_VALUE) {
            throw new OverflowException("overflow");
        }
    }

    public int evaluate(int x, int y, int z) throws Exception {
        check(tmp.evaluate(x, y, z));
        return -tmp.evaluate(x, y, z);
    }
}
