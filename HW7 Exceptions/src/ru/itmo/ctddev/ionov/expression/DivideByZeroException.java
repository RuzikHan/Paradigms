package ru.itmo.ctddev.ionov.expression;

public class DivideByZeroException extends Exception {
    public DivideByZeroException() {
        super("divide by zero");
    }
}
