package Hogwarts.Itens;

import java.util.List;

public class Varinha extends ItemHeroi {
    private int aumentoPoderFeitico;

    public Varinha(String nome, int precoMoedasOuro, List<String> heroisPermitidos, int aumentoPoderFeitico) {
        super(nome, precoMoedasOuro, heroisPermitidos);
        this.aumentoPoderFeitico = aumentoPoderFeitico;
    }

    public int getAumentoPoderFeitico() {
        return aumentoPoderFeitico;
    }

    public void usarVarinha() {
        System.out.println("Usaste a varinha " + getNome() + " que aumentou o poder do feitiço em " + aumentoPoderFeitico + " pontos.");
    }
}