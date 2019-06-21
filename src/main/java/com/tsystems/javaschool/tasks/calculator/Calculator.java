package com.tsystems.javaschool.tasks.calculator;

import java.util.ArrayList;

public class Calculator {
    public static void main(String[] args) {
        Calculator c=new Calculator();
        System.out.println( c.evaluate("2+3*4"));
    }

    /**
     *
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    public String evaluate(String statement) {
        try {
            double i=scan(statement);
            if ((int)i==i)
            {
                return (int)i+"";
            }

            if((i+"").equals("Infinity")) throw new Exception();

            return i + "";
        } catch (Exception e) {
            return null;
        }
    }

    private static double scan(String s) throws Exception {

        int result = 0;
        ArrayList[] a = split(s);

        return count(a[0], a[1]);

    }

    private static ArrayList[] split(String s) throws Exception {
        ArrayList<String> numbers = new ArrayList();
        ArrayList<Character> operators = new ArrayList();
        StringBuilder buffer = new StringBuilder();
        int count = 0;
        for (char i : s.toCharArray()) {
            if (s.startsWith("-"))
            {
                buffer.append(i);
                continue;
            }
            if (count == 0) {
                //    +_*/
                if (i == ')') throw new Exception();
                if (i == '+' || i == '-' || i == '*' || i == '/') {
                    operators.add(i);
                    if (buffer.toString().isEmpty()) throw new Exception();
                    numbers.add(buffer.toString());
                    buffer.delete(0, buffer.length());
                } else {
                    if (i != '(') {
                        buffer.append(i);
                    } else {
                        if (!buffer.toString().isEmpty()) throw new Exception();
                        count++;
                    }
                }
            } else {
                if (i == '(') count++;
                if (i == ')') {
                    count--;
                    if (buffer.toString().isEmpty()) throw new Exception();
                }
                if (count != 0) buffer.append(i);
            }

        }
        if (buffer.toString().isEmpty()) throw new Exception();
        numbers.add(buffer.toString());

        return new ArrayList[]{numbers, operators};

    }

    private static double count(ArrayList<String> numbers, ArrayList<Character> operators) throws Exception{
        int i;
        while ((i = operators.indexOf('*')) > 0 || (i = operators.indexOf('/')) > 0) {
            if(operators.indexOf('*')==-1)
            {
                i=operators.indexOf('/');
            } else if(operators.indexOf('/')==-1)
            {
                i=operators.indexOf('*');
            }
                else {
                i = operators.indexOf('*') > operators.indexOf('/') ? operators.indexOf('/') : operators.indexOf('*');
            }
            numbers.set(i,lilcalculator(numbers.get(i)+"",numbers.get(i+1)+"",operators.get(i))+"");
            numbers.remove(i+1);
            operators.remove(i);
        }
        i=0;
        while (numbers.size()>1)
        {
            numbers.set(i,lilcalculator(numbers.get(i)+"",numbers.get(i+1)+"",operators.get(i))+"");
            numbers.remove(i+1);
            operators.remove(i);
        }


        return Double.parseDouble(numbers.get(0));
    }

    private static double lilcalculator(String aa, String bb, char operator) throws Exception {
        double result,a,b;
        if(aa.contains("+")||
                aa.contains("-")||
                aa.contains("*")||
                aa.contains("/")
        )
        {
            a=scan(aa);
        }else a=Double.parseDouble(aa);
        if(bb.contains("+")||
                bb.contains("-")||
                bb.contains("*")||
                bb.contains("/")
        )
        {
            b=scan(bb);
        }else b=Double.parseDouble(bb);

        switch (operator)
        {
            case '+':
                result=a+b;
                break;
            case '-':
                result=a-b;
                break;
            case '*':
                result=a*b;
                break;
            case '/':
                result=a/b;
                break;
            default:
                throw new Exception("LIL Calculator no such operator");
        }
        return result;
    }

}
