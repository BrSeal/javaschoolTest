package com.tsystems.javaschool.tasks.calculator;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    private static final int FRACTION_DIGITS = 4;
    private static final String NUMBER_PATTERN = "\\d+\\.?\\d*";
    private static final String SPLIT_PATTERN = NUMBER_PATTERN + "|\\+|-|/|\\(|\\*|\\)";

    private final Map<String, Integer> priority;
    NumberFormat df;

    public Calculator() {
        df = DecimalFormat.getInstance(Locale.ENGLISH);
        df.setMaximumFractionDigits(FRACTION_DIGITS);

        priority = new HashMap<>();
        priority.put("+", 1);
        priority.put("-", 1);
        priority.put("*", 2);
        priority.put("/", 2);
    }

    public String evaluate(String statement) {
        try {
            List<String> split = splitStatement(statement);
            double result = calc(split);
            return df.format(result);

        } catch (Exception ex) {
            return null;
        }
    }

    private List<String> splitStatement(String statement) {
        Pattern pattern = Pattern.compile(SPLIT_PATTERN);
        Matcher matcher = pattern.matcher(statement);
        List<String> split = new ArrayList<>();

        while (matcher.find()) {
            split.add(matcher.group());
        }

        return split;
    }

    private boolean isNumber(String s) {
        return s.matches(NUMBER_PATTERN);
    }

    private double calculate(String operation, double a, double b) {
        switch (operation) {
            case "+":
                return b + a;
            case "-":
                return b - a;
            case "*":
                return b * a;
            case "/":
                if (a == 0) throw new IllegalArgumentException("Division by zero!");
                return b / a;
            default:
                throw new IllegalArgumentException("Unsupported operation [" + operation + "]");
        }
    }

    private double calc(List<String> strings) {
        Stack<Double> numbers = new Stack<>();
        Stack<String> signs = new Stack<>();
        String topSign;

        for(String expr : strings) {
            if (expr.equals(")")) {
                topSign = signs.pop();
                while (!topSign.equals("(")) {
                    numbers.push(calculate(topSign, numbers.pop(), numbers.pop()));
                    topSign = signs.pop();
                }
            } else if (isNumber(expr)) {
                numbers.push(Double.parseDouble(expr));
            } else if (expr.equals("(")) {
                signs.push(expr);
            } else {
                if (!signs.empty()) {
                    topSign = signs.peek();
                    while (!signs.empty() && !topSign.equals("(") && priority.get(expr) <= priority.get(topSign)) {
                        numbers.push(calculate(signs.pop(), numbers.pop(), numbers.pop()));
                        if (!signs.empty()) {
                            topSign = signs.peek();
                        }
                    }
                }
                signs.push(expr);
            }
        }

        while (!signs.empty()) {
            numbers.push(calculate(signs.pop(), numbers.pop(), numbers.pop()));
        }

        if (numbers.size() == 1) return numbers.pop();
        throw new IllegalArgumentException("Can't provide calculation");
    }
}
