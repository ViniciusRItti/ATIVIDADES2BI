package br.unipar.SobreEscrita;

// Classe Moto que estende Veiculo

public class Moto extends Veiculo {

    // Sobrescrita do método descricao da classe Veiculo

    @Override
    public String descricao() {

        // Retorna uma descrição específica para Moto

        return "Marca:BMW Cor:Branca e Azul Ano:2023";
    }
}