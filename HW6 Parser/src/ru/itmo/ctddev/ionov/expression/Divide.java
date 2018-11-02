package ru.itmo.ctddev.ionov.expression;

public class Divide extends AbstractCalc implements TripleExpression {
    public Divide(TripleExpression firstNum, TripleExpression secondNum) {
        super(firstNum, secondNum);
    }

    public int evaluateImpl(int x, int y) {
        return x / y;
    }
}