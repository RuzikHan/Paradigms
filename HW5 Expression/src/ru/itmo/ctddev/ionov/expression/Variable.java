package ru.itmo.ctddev.ionov.expression;

public class Variable implements AllExpression {
    private String line;

    public Variable(String Var) {
        line = Var;
    }

    public int evaluate(int value) {
        return (int) evaluate(value, 0, 0);
    }

    public double evaluate(double value) {
        return evaluate(value, 0, 0);
    }

    public double evaluate(double x, double y, double z) {
        if (line.equals("x")) {
            return x;
        } else if (line.equals("y")) {
            return y;
        } else {
            return z;
        }
    }
}
