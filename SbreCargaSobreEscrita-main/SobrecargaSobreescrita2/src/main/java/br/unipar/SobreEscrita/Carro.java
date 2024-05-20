package br.unipar.SobreEscrita;

// Classe Carro que estende Veiculo

public class Carro extends Veiculo {

    // Sobrescrita do método descricao da classe Veiculo

    @Override
    public String descricao() {

        // Retorna uma descrição específica para Carro

        return "Marca:Mercedes Cor:Preta Ano: 2020";
    }
}