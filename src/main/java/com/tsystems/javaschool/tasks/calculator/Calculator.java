package com.tsystems.javaschool.tasks.calculator;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    private static final int FRACTION_DIGITS = 4;
    private static final String VALIDATION_PATTERN = "[\\d.\\+\\-\\/\\(\\*\\)]+";
    private static final String NUMBER_PATTERN = "\\d+\\.?\\d*";
    private static final String SPLIT_PATTERN = NUMBER_PATTERN + "|\\+|\\-|\\/|\\(|\\*|\\)";

    private final Map<String, Integer> priority;

    DecimalFormat df;

    public Calculator() {
        df = new DecimalFormat();
        df.setMaximumFractionDigits(FRACTION_DIGITS);

        priority = new HashMap<>();
        priority.put("+", 1);
        priority.put("-", 1);
        priority.put("*", 2);
        priority.put("/", 2);
    }

    public String evaluate(String statement) {
        try {
            if (statement == null || !statement.matches(VALIDATION_PATTERN)) throw new IllegalArgumentException();

            List<String> split = splitStatement(statement);

            if (split.size() == 0) return null;

            Stack<Double> numbers = new Stack<>();
            Stack<String> signs = new Stack<>();
            Double result = 0.0;


            for(String s : split) {
                result = calc(s, numbers, signs, result);
            }

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

    private Double calc(String maybeNumber, Stack<Double> numbers, Stack<String> signs, Double result) {
        throw new NotImplementedException();
    }

    private double calculate(String operation, double a, double b) {
        switch (operation) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            default:
                throw new IllegalArgumentException();
        }
    }

    private boolean isNumber(String maybeNumber) {
        return maybeNumber.matches(NUMBER_PATTERN);
    }

}
