package ru.itmo.ctddev.ionov.expression.parser;

import ru.itmo.ctddev.ionov.expression.*;
import ru.itmo.ctddev.ionov.expression.parser.Parser;

public class ExpressionParser implements Parser {
    private int index = 0;
    private String expression;

    private void actionsCheck(String line) throws Exception {
        boolean f = false;
        for (int i = 0; i < line.length(); i++) {
            switch (line.charAt(i)) {
                case '+':
                case '-':
                case '*':
                case '/':
                    f = true;
            }
            if (f) {
                i = line.length() - 1;
            }
        }
        if (!f) {
            throw new ParserException("Expected action");
        }
    }

    private boolean actionsDigit(String line, int dex) {
        boolean f = false;
        switch (line.charAt(dex)) {
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
            case '9':
                f = true;
        }
        return f;
    }

    private void startCheck(String line) throws Exception {
        String saveLine = line;
        line = line + ".";
        line = line.replaceAll("log2", "log2+");
        line = line.replaceAll("pow2", "pow2+");
        index = 0;
        while (!actionsDigit(line, index) && index < line.length() - 1) {
            index++;
        }
        while (index < line.length() - 1) {
            while (actionsDigit(line, index)) {
                index++;
            }
            int index2 = index;
            while (!actionsDigit(line, index2) && line.charAt(index2) != '.') {
                index2++;
            }
            if (line.charAt(index2) != '.') {
                actionsCheck(line.substring(index, index2));
            }
            index = index2;
        }
        line = saveLine;
        for (int i = 0; i < line.length() - 4; i++) {
            if (line.substring(i, i + 4).equals("log2") || line.substring(i, i + 4).equals("pow2")) {
                int j = i + 4;
                boolean f = false;
                while (Character.isWhitespace(line.charAt(j))) {
                    j++;
                    f = true;
                }
                char check = line.charAt(j);
                if (!isCorrect(check)) {
                    throw new ParserException("Unexpected symbol " + check + " at position " + j);
                } else if (!f){
                    switch (check) {
                        case '+':
                        case '*':
                        case '/':
                        case ')':
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
                        case '9':
                            throw new ParserException("Unexpected symbol " + check + " at position " + j);
                    }
                }
            } else if (line.charAt(i) == 'l') {
                if (!(line.charAt(i + 1) == 'o')) {
                    throw new ParserException("Unexpected symbol " + line.charAt(i + 1) + " at position " + (i + 1));
                } else if (!(line.charAt(i + 2) == 'g')) {
                    throw new ParserException("Unexpected symbol " + line.charAt(i + 2) + " at position " + (i + 2));
                } else if (!(line.charAt(i + 3) == '2')) {
                    throw new ParserException("Unexpected symbol " + line.charAt(i + 3) + " at position " + (i + 3));
                }
            } else if (line.charAt(i) == 'p') {
                if (!(line.charAt(i + 1) == 'o')) {
                    throw new ParserException("Unexpected symbol " + line.charAt(i + 1) + " at position " + (i + 1));
                } else if (!(line.charAt(i + 2) == 'w')) {
                    throw new ParserException("Unexpected symbol " + line.charAt(i + 2) + " at position " + (i + 2));
                } else if (!(line.charAt(i + 3) == '2')) {
                    throw new ParserException("Unexpected symbol " + line.charAt(i + 3) + " at position " + (i + 3));
                }
            }
        }
    }

    private void check(char sign) throws Exception {
        if (!isCorrect(sign)) {
            if (index + 4 < expression.length()) {
                if (!(expression.substring(index, index + 4).equals("log2") || expression.substring(index, index + 4).equals("pow2"))) {
                    throw new ParserException("Unexpected symbol " + sign + " at position " + index);
                }
            } else throw new ParserException("Unexpected symbol " + sign + " at position " + index);
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
            case 'l':
            case 'p':
            case 'x':
            case 'y':
            case 'z':
            case '.':
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

    private TripleExpression num_parse(int sign) throws Exception {
        int start = index;
        while (Character.isDigit(expression.charAt(index))) {
            index++;
        }
        String line;
        if (sign == 1) {
            line = '-' + expression.substring(start, index);
        } else {
            line = expression.substring(start, index);
        }
        try {
            int num = Integer.parseInt(line);
            return new Const(num);
        } catch (Exception e) {
            throw new OverflowException("Constant overflow");
        }
    }

    private TripleExpression last_parse() throws Exception {
        skipSpace();
        char sign = expression.charAt(index);
        check(sign);
        switch (sign) {
            case '(': {
                index++;
                TripleExpression line = shifts_parse();
                index++;
                return line;
            }
            case '-': {
                index++;
                if (!Character.isDigit(expression.charAt(index))) {
                    return new CheckedNegate(last_parse());
                } else {
                    return num_parse(1);
                }
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
                return num_parse(0);
            }
            case 'l': {
                index += 4;
                return new CheckedLog2(last_parse());
            }
            case 'p': {
                index += 4;
                return new CheckedPow2(last_parse());
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

    private TripleExpression mid_parse() throws Exception {
        TripleExpression line = last_parse();
        skipSpace();
        char sign = expression.charAt(index);
        check(sign);
        while (sign == '*' || sign == '/') {
            switch (sign) {
                case '*': {
                    index++;
                    line = new CheckedMultiply(line, last_parse());
                    break;
                }
                case '/': {
                    index++;
                    skipSpace();
                    line = new CheckedDivide(line, last_parse());
                    break;
                }
            }
            skipSpace();
            sign = expression.charAt(index);
        }
        return line;
    }

    private TripleExpression first_parse() throws Exception {
        TripleExpression line = mid_parse();
        skipSpace();
        char sign = expression.charAt(index);
        check(sign);
        while (sign == '+' || sign == '-') {
            switch (sign) {
                case '+': {
                    index++;
                    line = new CheckedAdd(line, mid_parse());
                    break;
                }
                case '-': {
                    index++;
                    line = new CheckedSubtract(line, mid_parse());
                    break;
                }
            }
            sign = expression.charAt(index);
        }
        return line;
    }

    private TripleExpression shifts_parse() throws Exception {
        TripleExpression line = first_parse();
        char sign = expression.charAt(index);
        check(sign);
        return line;
    }

    public TripleExpression parse(String startline) throws Exception {
        startCheck(startline);
        expression = startline;
        expression = expression + '.';
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