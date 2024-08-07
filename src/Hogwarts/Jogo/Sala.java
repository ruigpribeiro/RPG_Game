package Hogwarts.Jogo;

import Hogwarts.Entidades.Inimigos.NPC;
import Hogwarts.Entidades.Vendedor;
import Hogwarts.Itens.ItemHeroi;

import java.util.ArrayList;
import java.util.List;

public class Sala {
    private String nome;
    private int id;
    private List<Integer> idSalaConetada;
    private List<NPC> inimigosNaSala;
    private Vendedor vendedorPresente;
    private List<ItemHeroi> itemPerdido;
    private int danoArmadilha;
    private int ouroPerdido;

    public Sala(String nome, int id, Vendedor vendedor) {
        this.nome = nome;
        this.id = id;
        this.idSalaConetada = new ArrayList<>();
        this.inimigosNaSala = new ArrayList<>();
        this.itemPerdido = new ArrayList<>();
        this.danoArmadilha = 0;
        this.ouroPerdido = 0;
    }

    public void boasVindas() {
        System.out.println("Acabaste de entrar na sala " + nome + ".");
    }

    public void saida(Sala proximaSala) {
        System.out.println("Estás na sala " + nome + " e vais para a sala " + proximaSala + ".");
    }

    public void adicionarInimigo(NPC inimigo) {
        inimigosNaSala.add(inimigo);
    }

    public void adicionarItemPerdido(ItemHeroi item) {
        itemPerdido.add(item);
    }

    public void setDanoArmadilha(int danoArmadilha) {
        this.danoArmadilha = danoArmadilha;
    }

    public void setOuroPerdido(int ouroPerdido) {
        this.ouroPerdido = ouroPerdido;
    }

    public void adicionarSalaConetada(int idSala) {
        idSalaConetada.add(idSala);
    }
}
