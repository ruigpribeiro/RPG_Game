package Hogwarts.itens;

import java.util.List;

public class ConsumivelCombate extends Consumivel {
    private int ataqueInstantaneo;

    public ConsumivelCombate(String nome, int preco, List<String> heroisPermitidos, int ataqueInstantaneo) {
        super(nome, preco, heroisPermitidos);
        this.ataqueInstantaneo = ataqueInstantaneo;
    }

    /**
     * Imprime todos os detalhes do ConsumivelCombate no terminal, incluindo detalhes específicos de Consumivel
     * e o valor de ataque instantâneo associado.
     *
     * Sobrescreve o método mostrarDetalhes da classe pai Consumivel.
     */
    @Override
    public void mostrarDetalhes() {
        super.mostrarDetalhes();
        System.out.print(" - Ataque Instantâneo: " + ataqueInstantaneo);
    }

    public int getAtaqueInstantaneo() {
        return ataqueInstantaneo;
    }
}
