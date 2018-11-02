package ru.itmo.ctddev.ionov.expression;

public class Subtract extends AbstractCalc implements TripleExpression {
    public Subtract(TripleExpression firstNum, TripleExpression secondNum) {
        super(firstNum, secondNum);
    }

    public int evaluateImpl(int x, int y) {
        return x - y;
    }
}