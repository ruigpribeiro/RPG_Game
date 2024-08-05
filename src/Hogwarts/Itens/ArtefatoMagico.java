package Hogwarts.Itens;

import java.util.List;

public class ArtefatoMagico extends ItemHeroi {
    private String efeitoEspecial;

    public ArtefatoMagico(String nome, int precoMoedasOuro, List<String> heroisPermitidos, String efeitoEspecial) {
        super(nome, precoMoedasOuro, heroisPermitidos);
        this.efeitoEspecial = efeitoEspecial;
    }

    public String getEfeitoEspecial() {
        return efeitoEspecial;
    }

    public void usarArtefato() {
        System.out.println("Usaste o artefato mágico " + getNome() + " que possui o efeito especial: " + efeitoEspecial);
    }
}