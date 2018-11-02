package ru.itmo.ctddev.ionov.expression;

public class OverflowException extends Exception {
    public OverflowException(String line) {
        super(line);
    }
}
