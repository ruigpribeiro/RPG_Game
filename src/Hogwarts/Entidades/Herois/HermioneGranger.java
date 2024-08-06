package Hogwarts.Entidades.Herois;

import Hogwarts.Entidades.Inimigos.NPC;

public class HermioneGranger extends Heroi {

    /**
     * Método construtor.
     *
     * @param nome      Nome do herói
     * @param vidaMax   Vida Max do herói
     * @param forca     Força do herói
     * @param nivel     Nivel do herói
     * @param ouro      Moedas de ouro do herói
     */
    public HermioneGranger(String nome, int vidaMax, int forca, int nivel, int ouro) {
        super(nome, vidaMax, forca, nivel, ouro);
    }

    @Override
    public boolean atacar(NPC inimigo) {
        return false;
    }
}
