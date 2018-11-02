package ru.itmo.ctddev.ionov.expression;

public class Abs implements TripleExpression {
    private TripleExpression tmp;
    public Abs(TripleExpression Num) {
        tmp = Num;
    }

    public int evaluate(int x, int y, int z) {
        return Math.abs(tmp.evaluate(x, y, z));
    }
}