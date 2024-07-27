package Itens;

import java.util.List;

public class ConsumivelCombate extends Consumivel {
    private int ataqueInstantaneo;

    public ConsumivelCombate(String nome, int precoMoedasOuro, List<String> heroisPermitidos, int ataqueInstantaneo) {
        super(nome, precoMoedasOuro, heroisPermitidos);
        this.ataqueInstantaneo = ataqueInstantaneo;
    }

    @Override
    public void mostrarDetalhes() {
        super.mostrarDetalhes();
        System.out.println("Ataque Instantaneo: " + this.ataqueInstantaneo);
    }
}
