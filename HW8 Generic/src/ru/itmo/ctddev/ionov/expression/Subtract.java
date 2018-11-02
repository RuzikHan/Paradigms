package ru.itmo.ctddev.ionov.expression;

public class Subtract<T> extends AbstractCalc<T> implements TripleExpression<T> {
    public Subtract(TripleExpression<T> firstNum, TripleExpression<T> secondNum, Types<T> type) {
        super(firstNum, secondNum, type);
    }

    public T evaluateImpl(T x, T y) throws Exception {
        return type.subtract(x, y);
    }
}