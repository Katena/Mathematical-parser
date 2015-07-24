package com.company;

public class Parser {
    private String exp;
    private int pos = 0;

    public double parse(String exp) {
        this.exp = exp;
        pos = 0;
        double result = expression(term(pow(factor())));
        return result;
    }

    private double pow(double left) {

        double res = left;
        char ch = getChar();
        if (ch != '^') {
            pos--;
            return left;
        }
        double s = factor();
        if (ch == '^') {
            for (int i = 0; i < s - 1; i++) {
                res = res * left;
            }
        }

        return res;
    }

    private double expression(double left) {
        char ch = getChar();
        if (ch != '+' && ch != '-') {
            pos--;
            return left;
        }
        double right = term(pow(factor()));
        if (ch == '+') {
            return expression(left + right);
        }

        return expression(left - right);
    }

    private double term(double left) {
        char ch = getChar();
        if (ch != '*' && ch != '/') {
            pos--;
            return left;
        }
        double right = factor();
        if (ch == '*') {
            return term(pow((left * right)));
        }
        if (right == 0) {
            System.out.println("division by ziro");
            return 0;
        }
        return term(pow((left / right)));
    }

    private double factor() {
        char ch = getChar();
        if (ch == 'E') {
            return Math.E;
        }
        if (ch == 'P') {
            ch = getChar();
            if (ch == 'I') {
                return Math.PI;
            } else pos--;
        }
        if (ch == '-') {
            return getValue(ch);
        }
        if (Character.isDigit(ch)) {
            return getValue(ch);
        }
        if (ch == '(') {
            Parser p = new Parser();
            return p.parse(getExpression());
        }
        return 0;
    }

    private String getExpression() {
        String res = "";
        int s = 0;

        while (true) {
            char ch = getChar();
            if (ch == '(') s++;

            else if (ch == ')') {
                if (s != 0) {
                    s--;
                } else break;
            }
            res += Character.toString(ch);
        }

        return res;
    }

    private double getValue(char ch) {
        int s = 0;

        String res = Character.toString(ch);

        while (true) {
            ch = getChar();

            if (Character.isDigit(ch) || ch == '.') {
                if (ch == '.') {
                    s++;
                }
                if (s <= 1) {
                    res += Character.toString(ch);
                } else {
                    System.out.println("incorrect!!");
                    return 0;
                }
            } else break;
        }
        pos--;

        return Double.parseDouble(res);
    }

    private char getChar() {
        if (pos < exp.length()) {
            char ch;
            ch = exp.charAt(pos);
            pos++;
            return ch;
        }
        return '\0';
    }

}
