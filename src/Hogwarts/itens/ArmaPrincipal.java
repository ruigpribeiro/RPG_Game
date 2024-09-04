package Hogwarts.itens;

import java.util.List;


public class ArmaPrincipal extends ItemHeroi {
    private int ataque;
    private int ataqueEspecial;


    public ArmaPrincipal(String nome, int preco, List<String> heroisPermitidos, int ataque, int ataqueEspecial) {
        super(nome, preco, heroisPermitidos);
        this.ataque = ataque;
        this.ataqueEspecial = ataqueEspecial;
    }

    @Override
    public void mostrarDetalhes() {
        super.mostrarDetalhes();
        System.out.print(", Ataque: " + ataque + ", Ataque Especial: " + ataqueEspecial);
    }

    public int getAtaque() {
        return ataque;
    }

    public int getAtaqueEspecial() {
        return ataqueEspecial;
    }
}