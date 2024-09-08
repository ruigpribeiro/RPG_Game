package Hogwarts.itens;

import java.util.List;

public abstract class ItemHeroi {
    private String nome;
    private int preco;
    private List<String> heroisPermitidos;


    /**
     * Método construtor.
     * @param nome Nome do ítem
     * @param preco Preço do ítem em ouro
     * @param heroisPermitidos Lista de heróis permitidos usar este item
     */
    public ItemHeroi(String nome, int preco, List<String> heroisPermitidos) {
        this.nome = nome;
        this.preco = preco;
        this.heroisPermitidos = heroisPermitidos;
    }

    /**
     * Imprime os detalhes do item no terminal.
     * O método exibe o nome do item, o seu preço e a lista de heróis permitidos a usá-lo.
     */
    public void mostrarDetalhes() {
        System.out.print("Ítem: " + nome + " - Preço: " + preco + " - Heróis Permitidos: " + heroisPermitidos);
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public int getPreco() {
        return preco;
    }

    public List<String> getHeroisPermitidos() {
        return heroisPermitidos;
    }
}
