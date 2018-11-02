package ru.itmo.ctddev.ionov.expression.parser;

import ru.itmo.ctddev.ionov.expression.*;

public interface Parser {
    TripleExpression parse(String expression) throws Exception;
}
