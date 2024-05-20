package br.unipar;

public class SobreCarga {

    // Este método calc recebe dois parâmetros inteiros e retorna um inteiro
    // Aqui é a primeira forma do método calc, que realiza a divisão de dois inteiros
    public int calc(int num1, int num2){
        return num1 / num2;
    }

    // Este método calc recebe dois parâmetros do tipo double e retorna um double
    // Aqui é a segunda forma do método calc, que realiza a soma de dois doubles
    public double calc(double num1, double num2){
        return num1 + num2;
    }

    // A sobrecarga de métodos está sendo utilizada aqui, onde dois métodos com o mesmo
    // nome (calc) possuem diferentes assinaturas (tipos e/ou número de parâmetros).
    // Dependendo dos tipos dos argumentos passados para o método, a JVM decidirá qual
    // método deve ser invocado.
}