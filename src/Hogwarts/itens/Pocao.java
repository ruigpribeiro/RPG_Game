package Hogwarts.itens;

import java.util.List;

public class Pocao extends Consumivel {
    private int vidaCurar;
    private int aumentoForca;

    public Pocao(String nome, int preco, List<String> heroisPermitidos, int vidaCurar, int aumentoForca) {
        super(nome, preco, heroisPermitidos);
        this.vidaCurar = vidaCurar;
        this.aumentoForca = aumentoForca;
    }

    /**
     * Imprime os detalhes da poção no terminal.
     * Este método exibe os detalhes básicos do item (nome, preço e heróis permitidos)
     * e adiciona informações adicionais específicas da poção, como a vida que pode
     * curar e o aumento de força que ela proporciona.
     */
    @Override
    public void mostrarDetalhes() {
        super.mostrarDetalhes();
        System.out.print("- Vida: " + vidaCurar + " - Força: " + aumentoForca);
    }

    // Getters
    public int getVidaCurar() {
        return vidaCurar;
    }

    public int getAumentoForca() {
        return aumentoForca;
    }
}