package Itens;

import Entidades.Heroi;

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

    /**
     * Imprime os detalhes dos atributos desta classe.
     */
    public void mostrarDetalhes() {
        System.out.print("Nome: " + nome + " | Preço: " + precoMoedasOuro + " | Heróis Permitidos: " + heroisPermitidos);
    }

    public int getPrecoMoedasOuro() {
        return precoMoedasOuro;
    }

    public String getNome() {
        return nome;
    }

    public List<String> getHeroisPermitidos() {
        return heroisPermitidos;
    }
}
