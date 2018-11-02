package ru.itmo.ctddev.ionov.expression;

public class NegativeLogException extends Exception {
    public NegativeLogException() {
        super("Negative number on log");
    }
}