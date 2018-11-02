package ru.itmo.ctddev.ionov.expression;

public class ParserException extends Exception {
    public ParserException(String line) {
        super(line);
    }
}
