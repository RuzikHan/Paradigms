package ru.itmo.ctddev.ionov.expression.parser;

import ru.itmo.ctddev.ionov.expression.*;
import ru.itmo.ctddev.ionov.expression.parser.Parser;

public class ExpressionParser<T> implements Parser<T> {
    private int index = 0;
    private String expression;
    private Types<T> type;

    public ExpressionParser(Types<T> myType) {
        type = myType;
    }

    private void check(char sign) throws Exception {
        if (!isCorrect(sign)) {
            if (index + 6 < expression.length()) {
                if (!expression.substring(index, index + 6).equals("square") && !(expression.substring(index, index + 3).equals("mod") || expression.substring(index, index + 3).equals("abs"))) {
                    throw new ParserException("Unexpected symbol " + sign + " at position " + index);
                }
            } else if (index + 3 < expression.length()) {
                if (!(expression.substring(index, index + 3).equals("mod") || expression.substring(index, index + 3).equals("abs"))) {
                    throw new ParserException("Unexpected symbol " + sign + " at position " + index);
                }
            } else {
                throw new ParserException("Unexpected symbol " + sign + " at position " + index);
            }
        }
    }

    private void skipSpace() {
        while (Character.isWhitespace(expression.charAt(index))) {
            index++;
        }
    }

    private boolean isCorrect(char sign) {
        switch (sign) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '(':
            case ')':
            case '.':
            case 'x':
            case 'y':
            case 'z':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9': {
                return true;
            }
            default:
                return false;
        }
    }

    private TripleExpression<T> last_parse() throws Exception {
        skipSpace();
        char sign = expression.charAt(index);
        check(sign);
        switch (sign) {
            case '(': {
                index++;
                TripleExpression<T> line = shifts_parse();
                index++;
                return line;
            }
            case '-': {
                index++;
                T num = type.parse("0");
                return new Subtract<>(new Const<>(num), last_parse(), type);
            }
            case 'x': {
                index++;
                return new Variable<>("x");
            }
            case 'y': {
                index++;
                return new Variable<>("y");
            }
            case 'z': {
                index++;
                return new Variable<>("z");
            }
            case 'a': {
                index += 3;
                return new Abs<>(last_parse(), type);
            }
            case 's': {
                index += 6;
                return new Square<>(last_parse(), type);
            }
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9': {
                int start = index;
                while (Character.isDigit(expression.charAt(index))) {
                    index++;
                }
                try {
                    T num = type.parse(expression.substring(start, index));
                    return new Const<>(num);
                } catch (Exception e) {
                    throw new OverflowException("Constant overflow");
                }
            }
            default:
                if (index == 0 || expression.charAt(index - 1) == '(') {
                    throw new ParserException("No first argument at position " + index);
                } else if (index == expression.length() - 1 || expression.charAt(index) == ')') {
                    throw new ParserException("No last argument at position " + index);
                } else {
                    throw new ParserException("No middle argument at position " + index);
                }
        }
    }

    private TripleExpression<T> mid_parse() throws Exception {
        skipSpace();
        TripleExpression<T> line = last_parse();
        skipSpace();
        char sign = expression.charAt(index);
        check(sign);
        while (sign == '*' || sign == '/' || sign == 'm') {
            switch (sign) {
                case '*': {
                    index++;
                    line = new Multiply<>(line, last_parse(), type);
                    break;
                }
                case '/': {
                    index++;
                    skipSpace();
                    line = new Divide<>(line, last_parse(), type);
                    break;
                }
                case 'm': {
                    index += 3;
                    line = new Mod<>(line, last_parse(), type);
                }
            }
            skipSpace();
            sign = expression.charAt(index);
        }
        return line;
    }

    private TripleExpression<T> first_parse() throws Exception {
        TripleExpression<T> line = mid_parse();
        skipSpace();
        char sign = expression.charAt(index);
        check(sign);
        while (sign == '+' || sign == '-') {
            switch (sign) {
                case '+': {
                    index++;
                    line = new Add<>(line, mid_parse(), type);
                    break;
                }
                case '-': {
                    index++;
                    line = new Subtract<>(line, mid_parse(), type);
                    break;
                }
            }
            sign = expression.charAt(index);
        }
        return line;
    }

    private TripleExpression<T> shifts_parse() throws Exception {
        TripleExpression<T> line = first_parse();
        char sign = expression.charAt(index);
        check(sign);
        return line;
    }

    public TripleExpression<T> parse(String startline) throws Exception {
        expression = startline + ".";
        int balance = 0;
        for (int i = 0; i < expression.length(); i++) {
            char sign = expression.charAt(i);
            if (sign == '(') {
                balance++;
            } else if (sign == ')') {
                balance--;
            }
            if (balance < 0) {
                throw new ParserException("No opening parenthesis");
            }
        }
        if (balance > 0) {
            throw new ParserException("No closing parenthesis");
        } else if (balance < 0) {
            throw new ParserException("No opening parenthesis");
        }
        index = 0;
        return shifts_parse();
    }
}