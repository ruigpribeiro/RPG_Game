package Itens;

import java.util.List;

public abstract class ItemHeroi {
    private String nome;
    private int precoMoedasOuro;
    private List<String> heroisPermitidos;

    public ItemHeroi(String nome, int precoMoedasOuro, List<String> heroisPermitidos) {
        this.nome = nome;
        this.precoMoedasOuro = precoMoedasOuro;
        this.heroisPermitidos = heroisPermitidos;
    }

    public void mostrarDetalhes() {
        System.out.println("Nome: " + nome);
        System.out.println("Preço em moedas de ouro: " + precoMoedasOuro);
        System.out.println("Heróis: " + heroisPermitidos);
    }

    public int getPrecoMoedasOuro() {
        return precoMoedasOuro;
    }

    public String getNome() {
        return nome;
    }
}
