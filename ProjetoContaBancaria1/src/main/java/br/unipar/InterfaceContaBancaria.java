package br.unipar;

public interface InterfaceContaBancaria {
    Double obterSaldo();

    void depositar(Double valor);

    String retornarnumeroConta();

    boolean saque(Double valor);
}
