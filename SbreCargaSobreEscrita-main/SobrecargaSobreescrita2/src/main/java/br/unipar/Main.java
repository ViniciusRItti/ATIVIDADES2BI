package br.unipar;

public class Main {
    public static void main(String[] args) {

        SobreCarga calc = new SobreCarga();
        System.out.println(calc.calc(2, 2)); //Divisao
        System.out.println(calc.calc(2.0,2.0)); // Soma
    }
}