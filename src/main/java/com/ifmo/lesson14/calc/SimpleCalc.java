package com.ifmo.lesson14.calc;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Добавьте поддержку переменных.
 * Синтаксис следующий.
 * Объявление переменной через символ '=':
 * x = 8
 *
 * Имя переменной не может быть числом.
 *
 * Использование в выражении вместо одного или двух аргументов:
 * x + 2
 * Результат: 10.
 *
 * Калькулятор должен выбрасывать соответствующие исключения с
 * подробными описаниями ошибок и как их исправить. Например,
 * если имя переменной не найдено или использовался неверный синтаксис.
 */
public class SimpleCalc {

    private static Map<String, Integer> peremen = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter expression: ");

            String line = scanner.nextLine();

            if ("exit".equals(line))
                break;

            try {
                System.out.println("Answer is: " + calculate(line));
            }
            catch (CalcException e) {
                System.err.println("Error occurred: ");

                e.printStackTrace();
            }
        }
    }

    static int calculate(String line) throws CalcException {
        int op1 = 0;
        int op2 = 0;

        if (!line.contains("+") && !line.contains("-") && !line.contains("="))
            throw new CalcException("Expression must contain '+', '-' or '=' : " + line);

        String[] operands = line.split(" ");

        if (operands.length != 3)
            throw new CalcException("Expression must have only 3 operands separated with space (e.g. 2 + 4): " + line);


        OPERATOR operator = OPERATOR.parse(operands[1]);

        op2 = parseOperand(operands[2]);

        if(operator == OPERATOR.EQ && !valid(operands[0])){
            peremen.put(operands[0], op2);
            return op2;
        }

        op1 = parseOperand(operands[0]);

        return operator.apply(op1, op2);
    }

    private static boolean valid(String string) throws CalcException{
        try {
            Integer.parseInt(string);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    private static int parseOperand(String string) throws CalcException {
        try {
            return Integer.parseInt(string);
        }
        catch (NumberFormatException e) {
            if(peremen.containsKey(string)){
                return peremen.get(string);
            }
                throw new CalcException("Wrong operand, must be only integer number or valid peremen: " + string, e);
        }
    }

    private enum OPERATOR {
        PLUS, MINUS, EQ;

        int apply(int arg1, int arg2) throws CalcException {
            switch (this) {

                case PLUS:
                    return arg1 + arg2;

                case MINUS:
                    return arg1 - arg2;
            }

            throw new CalcException("Unsupported operator: " + this);
        }

        static OPERATOR parse(String str) throws CalcException {
            switch (str) {
                case "+":
                    return PLUS;

                case "-":
                    return MINUS;

                    case "=":
                    return EQ;
            }

            throw new CalcException("Incorrect operator: " + str);
        }
    }
}
