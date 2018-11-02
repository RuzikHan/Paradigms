package ru.itmo.ctddev.ionov.expression.parser;

import ru.itmo.ctddev.ionov.expression.*;
import ru.itmo.ctddev.ionov.expression.parser.Parser;

public class ExpressionParser implements Parser {
    private int index = 0;
    private String expression;
    private int num = 0;

    private TripleExpression last_parse() {
        char sign = expression.charAt(index);
        switch (sign) {
            case '(': {
                index++;
                TripleExpression line = shifts_parse();
                index++;
                return line;
            }
            case '-': {
                index++;
                return new Subtract(new Const(0), last_parse());
            }
            case 'x': {
                index++;
                return new Variable("x");
            }
            case 'y': {
                index++;
                return new Variable("y");
            }
            case 'z': {
                index++;
                return new Variable("z");
            }
            case 'a': {
                index = index + 3;
                return new Abs(last_parse());
            }
            case 's': {
                index = index + 6;
                return new Square(last_parse());
            }
            default: {
                int start = index;
                while (Character.isDigit(expression.charAt(index))) {
                    index++;
                }
                String line;
                line = expression.substring(start, index);
                int num = Integer.parseInt(line);
                return new Const(num);
            }
        }
    }

    private TripleExpression mid_parse() {
        TripleExpression line = last_parse();
        char sign = expression.charAt(index);
        while (sign == '*' || sign == '/') {
            switch (sign) {
                case '*': {
                    index++;
                    line = new Multiply(line, last_parse());
                    break;
                }
                case '/': {
                    index++;
                    line = new Divide(line, last_parse());
                    break;
                }
            }
            sign = expression.charAt(index);
        }
        return line;
    }

    private TripleExpression first_parse() {
        TripleExpression line = mid_parse();
        char sign = expression.charAt(index);
        while (sign == '+' || sign == '-') {
            switch (sign) {
                case '+': {
                    index++;
                    line = new Add(line, mid_parse());
                    break;
                }
                case '-': {
                    index++;
                    line = new Subtract(line, mid_parse());
                    break;
                }
            }
            sign = expression.charAt(index);
        }
        return line;
    }

    private TripleExpression shifts_parse() {
        TripleExpression line = first_parse();
        char sign = expression.charAt(index);
        while (sign == '<' || sign == '>') {
            switch (sign) {
                case '<': {
                    index += 2;
                    line = new ShiftLeft(line, first_parse());
                    break;
                }
                case '>': {
                    index += 2;
                    line = new ShiftRight(line, first_parse());
                    break;
                }
            }
            sign = expression.charAt(index);
        }
        return line;
    }

    public TripleExpression parse(String startline) {
        expression = startline.replaceAll("\\p{javaWhitespace}+", "") + " ";
        index = 0;
        return shifts_parse();
    }
}