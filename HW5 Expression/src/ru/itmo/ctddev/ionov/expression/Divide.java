package ru.itmo.ctddev.ionov.expression;

public class Divide extends AbstractCalc implements AllExpression {
    public Divide(AllExpression firstNum, AllExpression secondNum) {
        super(firstNum, secondNum);
    }

    public int evaluateIntImpl(int x, int y) {
        return x / y;
    }

    public double evaluateImpl(double x, double y) {
        return x / y;
    }
}