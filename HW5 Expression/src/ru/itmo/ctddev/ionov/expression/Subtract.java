package ru.itmo.ctddev.ionov.expression;

public class Subtract extends AbstractCalc implements AllExpression {
    public Subtract(AllExpression firstNum, AllExpression secondNum) {
        super(firstNum, secondNum);
    }

    public int evaluateIntImpl(int x, int y) {
        return x - y;
    }

    public double evaluateImpl(double x, double y) {
        return x - y;
    }
}