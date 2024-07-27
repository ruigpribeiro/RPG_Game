package Entidades;

import Itens.ArmaPrincipal;
import Itens.Consumivel;
import Itens.Pocao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Heroi extends Entidade {
    private int nivel;
    private int ouro;
    private ArmaPrincipal armaPrincipal;
    private List<Consumivel> inventario;
    private Scanner scanner;

    public Heroi(String nome, int vidaMax, int vidaAtual, int forca, int nivel, int ouro, ArmaPrincipal armaPrincipal, List<Consumivel> inventario) {
        super(nome, vidaMax, vidaAtual, forca);
        this.nivel = nivel;
        this.ouro = ouro;
        this.armaPrincipal = armaPrincipal;
        this.inventario = inventario;
        scanner = new Scanner(System.in);
    }

    public abstract void atacar(NPC inimigo);

    /**
     * Imprime o inventário de poções do herói e pergunta qual quer usar
     */
    public void usarPocao() {
        List<Pocao> pocoes = new ArrayList<>();

        // Adiciona ao novo arraylist todas as poções que encontrar no inventário
        for (int i = 0; i < inventario.size(); i++) {
            if (inventario.get(i) instanceof Pocao) {
                pocoes.add((Pocao) inventario.get(i));
            }
        }

        // Verifica se a arraylist está vazia
        if (pocoes.isEmpty()) {
            System.out.println("Não tens poções no teu inventário");
            return;
        }

        // Imprime todas as poções
        int count = 0;
        for (Pocao pocaoAtual : pocoes) {
            System.out.println(count + ": " +  pocaoAtual);
        }

        System.out.print("Escolha uma Poção: ");
        int opcao = scanner.nextInt();

        // Verifica se a opção está fora do range 0 ao tamanho de pocoes
        if (opcao < 0 || opcao >= pocoes.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        // Atualiza a vida atual e força do herói
        Pocao pocao = pocoes.get(opcao);
        super.vidaAtual += pocao.getVidaACurar();
        super.forca += pocao.getAumentoForca();

        System.out.println("Acabaste de receber a poção " + pocao + ". A tua vida e força foram aumentadas.");
        inventario.remove(pocao);
    }

    @Override
    public void mostrarDetalhes() {
        super.mostrarDetalhes();
        System.out.println("Nível: " + nivel);
        System.out.println("Ouro: " + ouro);
        System.out.println("Arma Principal: " + armaPrincipal);
        System.out.println("Inventário: " + inventario);
    }

    public int getOuro() {
        return ouro;
    }

    public void setOuro(int ouro) {
        this.ouro = ouro;
    }

    public void setArmaPrincipal(ArmaPrincipal armaPrincipal) {
        this.armaPrincipal = armaPrincipal;
    }
}
