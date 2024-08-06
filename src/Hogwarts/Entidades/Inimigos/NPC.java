package Hogwarts.Entidades.Inimigos;

import Hogwarts.Entidades.Entidade;

public class NPC extends Entidade {
    private int ouro;

    public NPC(String nome, int vidaMax, int forca, int ouro) {
        super(nome, vidaMax, forca);
        this.ouro = ouro;
    }

    // Getter
    public int getOuro() {
        return ouro;
    }


}
