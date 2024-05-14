package br.unipar;

public class ContaPoupanca extends HerancaContaBancaria {

    private static  final double TAXA_SAQUE = 5.0;

    @Override
    public boolean saque(Double valor) {

        if((saldo - 5) >= valor){
            saldo -= valor;
            saldo -= TAXA_SAQUE;
            return true;
        }

        return false;
    }

}
