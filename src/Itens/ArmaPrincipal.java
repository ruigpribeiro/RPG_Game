package Itens;

import java.util.List;

public class ArmaPrincipal extends ItemHeroi {
    private int ataque;
    private int ataqueEspecial;

    public ArmaPrincipal(String nome, int precoMoedasOuro, List<String> heroisPermitidos, int ataque, int ataqueEspecial) {
        super(nome, precoMoedasOuro, heroisPermitidos);
        this.ataque = ataque;
        this.ataqueEspecial = ataqueEspecial;
    }

    @Override
    public void mostrarDetalhes() {
        super.mostrarDetalhes();
        System.out.println("Ataque: " + ataque);
        System.out.println("Ataque Especial: " + ataqueEspecial);
    }
}
