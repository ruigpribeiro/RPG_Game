package Hogwarts.Itens;

import java.util.List;

public class ConsumivelCombate extends Consumivel {
    private int ataqueInstantaneo;

    public ConsumivelCombate(String nome, int preco, List<String> heroisPermitidos, int ataqueInstantaneo) {
        super(nome, preco, heroisPermitidos);
        this.ataqueInstantaneo = ataqueInstantaneo;
    }

    @Override
    public void mostrarDetalhes() {
        super.mostrarDetalhes();
        System.out.print("Ataque Instantâneo: " + ataqueInstantaneo);
    }
}
