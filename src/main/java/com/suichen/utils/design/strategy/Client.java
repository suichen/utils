package com.suichen.utils.design.strategy;

public class Client {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.setStrategy(new Addition());
        System.out.println(calculator.getResult(2,1));


        calculator.setStrategy(new Subtraction());
        System.out.println(calculator.getResult(2,1));

        calculator.setStrategy(new Multiply());
        System.out.println(calculator.getResult(2,1));
    }
}
