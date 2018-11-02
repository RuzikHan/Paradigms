package ru.itmo.ctddev.ionov.expression;

public abstract class AbstractCalc implements TripleExpression {
    protected TripleExpression tmpone;
    protected TripleExpression tmptwo;

    AbstractCalc(TripleExpression firstNum, TripleExpression secondNum) {
        tmpone = firstNum;
        tmptwo = secondNum;
    }

    public int evaluate(int x, int y, int z) throws Exception {
        return evaluateImpl(tmpone.evaluate(x, y, z), tmptwo.evaluate(x, y, z));
    }

    protected abstract int evaluateImpl(int x, int y) throws Exception;
}
