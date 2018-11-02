package ru.itmo.ctddev.ionov.expression.parser;

import ru.itmo.ctddev.ionov.expression.*;

public interface Parser<T> {
    TripleExpression<T> parse(String expression) throws Exception;
}
