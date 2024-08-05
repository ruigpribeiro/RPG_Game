package Hogwarts.Itens;

import java.util.List;

public class Pocao extends ItemHeroi {
    private int aumentoVida;
    private int aumentoPoderFeitico;

    public Pocao(String nome, int precoMoedasOuro, List<String> heroisPermitidos, int aumentoVida, int duracao) {
        super(nome, precoMoedasOuro, heroisPermitidos);
        this.aumentoVida = aumentoVida;
        this.aumentoPoderFeitico = duracao;
    }

    public int getAumentoVida() {
        return aumentoVida;
    }

    public int getAumentoPoderFeitico() {
        return aumentoPoderFeitico;
    }

    public void usarPocao() {
        System.out.println("Usaste a poção " + getNome() + " que cura " + aumentoVida + " pontos de vida.");
    }
}