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

    public int getAtaque() {
        return ataque;
    }

    public int getAtaqueEspecial() {
        return ataqueEspecial;
    }

    @Override
    public void mostrarDetalhes() {
        super.mostrarDetalhes();
        System.out.print(" | Ataque: " + ataque + " | Ataque Especial: " + ataqueEspecial);
    }
}
