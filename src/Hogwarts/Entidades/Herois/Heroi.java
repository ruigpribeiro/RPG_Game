package Hogwarts.Entidades.Herois;

import Hogwarts.Entidades.Entidade;
import Hogwarts.Entidades.Inimigos.NPC;
import Hogwarts.Itens.Consumivel;
import Hogwarts.Itens.ItemHeroi;
import Hogwarts.Itens.Pocao;
import Hogwarts.Itens.ArmaPrincipal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Heroi extends Entidade {
    protected int nivel;
    protected int ouro;
    protected ArmaPrincipal armaPrincipal;
    protected List<Consumivel> inventario;
    protected Scanner scanner;

    /**
     * Método construtor.
     * @param nome Nome do herói
     * @param vidaMax Vida Max do herói
     * @param forca Força do herói
     * @param nivel Nivel do herói
     * @param ouro Moedas de ouro do herói
     */
    public Heroi(String nome, int vidaMax, int forca, int nivel, int ouro) {
        super(nome, vidaMax, forca);
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

    /**
     * Imprime todas as poções do inventário do herói no terminal
     */
    public void usarPocao() {
        System.out.println("Aqui tens o teu inventário de Poções:");
        List<Pocao> pocoes = new ArrayList<>();

        // Guarda todas as poções no novo array pocoes
        for (Consumivel consumivel : inventario) {
            if (consumivel instanceof Pocao) {
                pocoes.add((Pocao) consumivel);
            }
        }

        // Verifica se não há poções disponíveis
        if (pocoes.isEmpty()) {
            System.out.println("Não tens poções no inventário.");
            return;
        }

        // Imprime todas as poções disponíveis
        for (int i = 0; i < pocoes.size(); i++) {
            Pocao pocao = pocoes.get(i);
            System.out.println((i + 1) + ": " + pocao.getNome() + ", Cura: " + pocao.getVidaCurar() + ", Força: "
                    + pocao.getAumentoForca());
        }

        // Input do utilizador
        System.out.println("Escolhe a poção que queres usar (ou 0 para cancelar):");
        scanner = new Scanner(System.in);
        int opcao = scanner.nextInt();

        if (opcao > 0 && opcao <= pocoes.size()) {
            Pocao pocaoEscolhida = pocoes.get(opcao - 1);

            // Aplicar os efeitos da poção
            vidaAtual += pocaoEscolhida.getVidaCurar();
            forca += pocaoEscolhida.getAumentoForca();

            System.out.println("Usaste a poção " + pocaoEscolhida.getNome() + ". Vida curada: "
                    + pocaoEscolhida.getVidaCurar() + ", Força aumentada: " + pocaoEscolhida.getAumentoForca());

            // Remover a poção usada do inventário
            inventario.remove(pocaoEscolhida);
        } else {
            System.out.println("Opção inválida.");
        }

    }


    // Getters e Setters
    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getOuro() {
        return ouro;
    }

    public void setOuro(int ouro) {
        this.ouro = ouro;
    }

    public ArmaPrincipal getArmaPrincipal() {
        return armaPrincipal;
    }

    public void setArmaPrincipal(ArmaPrincipal armaPrincipal) {
        this.armaPrincipal = armaPrincipal;
    }

    public List<Consumivel> getInventario() {
        return inventario;
    }

    public void setInventario(List<Consumivel> inventario) {
        this.inventario = inventario;
    }
}
