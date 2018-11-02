package ru.itmo.ctddev.ionov.expression;

public abstract class AbstractCalc implements AllExpression {
    protected AllExpression tmpone;
    protected AllExpression tmptwo;

    AbstractCalc(AllExpression firstNum, AllExpression secondNum) {
        tmpone = firstNum;
        tmptwo = secondNum;
    }

    public int evaluate(int x) {
        return evaluateIntImpl(tmpone.evaluate(x), tmptwo.evaluate(x));
    }

    public double evaluate(double x) {
        return evaluateImpl(tmpone.evaluate(x, 0, 0), tmptwo.evaluate(x, 0, 0));
    }

    public double evaluate(double x, double y, double z) {
        return evaluateImpl(tmpone.evaluate(x, y, z), tmptwo.evaluate(x, y, z));
    }

    protected abstract double evaluateImpl(double x, double y);

    protected abstract int evaluateIntImpl(int x, int y);
}
