package ru.itmo.ctddev.ionov.expression;

public interface TripleExpression<T> {
    T evaluate(T x, T y, T z) throws Exception;
}
