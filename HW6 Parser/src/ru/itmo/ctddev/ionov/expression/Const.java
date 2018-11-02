package ru.itmo.ctddev.ionov.expression;

public class Const implements TripleExpression {
    private int tmp;

    public Const(int x) {
        tmp = x;
    }

    public int evaluate(int x, int y, int z) {
        return tmp;
    }
}
