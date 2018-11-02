package ru.itmo.ctddev.ionov.expression;

public class Const implements AllExpression {
    private double tmp;

    public Const(double x) {
        tmp = x;
    }

    public int evaluate(int x) {
        return (int) evaluate(0, 0, 0);
    }

    public double evaluate(double x) {
        return evaluate(0, 0, 0);
    }

    public double evaluate(double x, double y, double z) {
        return tmp;
    }
}
