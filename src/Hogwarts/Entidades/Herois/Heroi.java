package Hogwarts.Entidades.Herois;

import Hogwarts.Entidades.Entidade;
import Hogwarts.Entidades.Inimigos.NPC;
import Hogwarts.Itens.Consumivel;
import Hogwarts.Itens.ItemHeroi;
import Hogwarts.Itens.Varinha;

import java.util.List;

public abstract class Heroi extends Entidade {
    private int nivel;
    private int ouro;
    private Varinha varinha;
    private List<Consumivel> inventario;

    /**
     * Método construtor.
     * @param nome Nome do herói
     * @param vidaMax Vida Max do herói
     * @param vidaAtual Vida Atual do herói
     * @param forca Força do herói
     * @param nivel Nivel do herói
     * @param ouro Moedas de ouro do herói
     */
    public Heroi(String nome, int vidaMax, int vidaAtual, int forca, int nivel, int ouro) {
        super(nome, vidaMax, vidaAtual, forca);
        this.nivel = nivel;
        this.ouro = ouro;
    }

    /**
     * Adiciona um ítem ao inventário do herói
     * @param item O ítem a ser adicionado
     */
    public void adicionarItemAoInventario(ItemHeroi item) {
        inventario.add((Consumivel) item);
    }

    /**
     * Confronta o herói com um npc numa luta até que um deles fique sem vida.
     */
    public abstract boolean atacar(NPC inimigo);

    // Getters e Setters
    public int getNivel() {
        return nivel;
    }

    public int getOuro() {
        return ouro;
    }

    public Varinha getArma() {
        return varinha;
    }

    public List<Consumivel> getInventario() {
        return inventario;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setOuro(int ouro) {
        this.ouro = ouro;
    }

    public void setVarinha(Varinha varinha) {
        this.varinha = varinha;
    }

}
