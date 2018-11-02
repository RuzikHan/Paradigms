package ru.itmo.ctddev.ionov.expression;

public class Add extends AbstractCalc implements TripleExpression {
    public Add(TripleExpression firstNum, TripleExpression secondNum) {
        super(firstNum, secondNum);
    }

    public int evaluateImpl(int x, int y) {
        return x + y;
    }
}