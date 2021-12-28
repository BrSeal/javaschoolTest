package com.tsystems.javaschool.tasks.calculator;

import org.junit.Assert;
import org.junit.Test;

public class MyCalculatorTest {

    private Calculator calc = new Calculator();

    @Test
    public void tst() {
        String input = "(4-6*98.5+17/2)/((0.8*7-5)+(12.8*8-99))";
        Assert.assertEquals("-144.625", calc.evaluate(input));
    }

    @Test
    public void tst1() {
        String input = "((((12))))";
        Assert.assertEquals("12", calc.evaluate(input));
    }

    @Test
    public void tst2() {
        String input = "23/(((2+3)*(0))-7)";
        Assert.assertEquals("-3.2857", calc.evaluate(input));
    }

    @Test
    public void tst3() {
        String input = "1+2+3+4+5+6+7+8+9+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0+0";
        Assert.assertEquals("45", calc.evaluate(input));
    }
}
