package Entidades;

import Itens.ArmaPrincipal;
import Itens.Consumivel;

import java.util.List;

public class Feiticeiro extends Heroi {
    public Feiticeiro(String nome, int vidaMax, int vidaAtual, int forca, int nivel, int ouro, ArmaPrincipal armaPrincipal, List<Consumivel> inventario) {
        super(nome, vidaMax, vidaAtual, forca, nivel, ouro, armaPrincipal, inventario);
    }

    @Override
    public void atacar(NPC inimigo) {

    }
}
