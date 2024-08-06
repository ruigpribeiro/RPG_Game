package Hogwarts.Jogo;

import Hogwarts.Entidades.Herois.HarryPotter;
import Hogwarts.Entidades.Herois.HermioneGranger;
import Hogwarts.Entidades.Herois.Heroi;
import Hogwarts.Entidades.Herois.RonWeasley;
import Hogwarts.Entidades.Vendedor;
import Hogwarts.Itens.ArmaPrincipal;
import Hogwarts.Itens.ConsumivelCombate;
import Hogwarts.Itens.Pocao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jogo {

    /**
     * Cria uma personagem com as escolhas do utilizador
     * @return
     */
    public Heroi criarPersonagem() {
        // Escolha de herói
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolhe o teu herói: ");
        System.out.println("1. Harry Potter");
        System.out.println("2. Hermione Granger");
        System.out.println("3. Ron Weasley");
        System.out.print("Opção: ");
        int opcaoHeroi = scanner.nextInt();

        // Escolha de dificuldade
        System.out.println("Escolhe a dificuldade:");
        System.out.println("1. Fácil");
        System.out.println("2. Difícil)");
        System.out.print("Opção: ");
        int dificuldade = scanner.nextInt();

        int pontosCriacao = 0;
        int ouroInicial = 0;

        if (dificuldade == 1) {
            pontosCriacao = 300;
            ouroInicial = 20;
        } else {
            pontosCriacao = 220;
            ouroInicial = 15;
        }

        // Distribui os pontos de criação
        System.out.println("Distribua os pontos de criação: ");
        System.out.print("Pontos de vida: ");
        int vida = scanner.nextInt();
        System.out.print("Força: ");
        int forca = scanner.nextInt();

        // Verifica se os pontos foram totalmente distribuidos
        while (vida + forca != pontosCriacao) {
            System.out.println("Pontos de distribuição incorretos!");
            System.out.println("Pontos de vida: ");
            vida = scanner.nextInt();
            System.out.println("Força: ");
            forca = scanner.nextInt();
        }

        // Cria uma instância do herói escolhido com o feedback do utilizador
        Heroi heroi = null;
        switch (opcaoHeroi) {
            case 1:
                heroi = new HarryPotter("Harry Potter", vida,forca, 1,ouroInicial);
                break;
            case 2:
                heroi = new HermioneGranger("Hermione Granger", vida, forca, 1, ouroInicial);
                break;
            case 3:
                heroi = new RonWeasley("Ron Weasley", vida, forca, 1, ouroInicial);
                break;
        }

        return heroi;
    }

    /**
     * Lógica do jogo
     * @param heroi O herói que vai jogar
     */
    public void labirintoMagico(Heroi heroi) {
        Vendedor vendedor = new Vendedor();

        List<String> todosPermitidos = new ArrayList<>();
        todosPermitidos.add("HarryPotter");
        todosPermitidos.add("HermioneGranger");
        todosPermitidos.add("RonWeasley");



        // Adiciona armas à loja do vendedor
        vendedor.adicionarItem(new ArmaPrincipal("Varinha de Olivanders", 25, todosPermitidos, 15, 30));
        vendedor.adicionarItem(new ArmaPrincipal("Espada de Gryffindor", 30, todosPermitidos, 20, 40));
        vendedor.adicionarItem(new ArmaPrincipal("Varinha do Dragão", 20, todosPermitidos, 18, 35));
        vendedor.adicionarItem(new ArmaPrincipal("Cajado de Dumbledore", 25, todosPermitidos, 22, 45));
        vendedor.adicionarItem(new ArmaPrincipal("Varinha de Fênix", 30, todosPermitidos, 25, 50));
        vendedor.adicionarItem(new ArmaPrincipal("Varinha do Harry", 20, todosPermitidos, 15, 35));
        vendedor.adicionarItem(new ArmaPrincipal("Varinha da Hermione", 20, todosPermitidos, 15, 35));
        vendedor.adicionarItem(new ArmaPrincipal("Varinha do Ron", 20, todosPermitidos, 15, 35));

        // Adiciona poções de cura à loja do vendedor
        vendedor.adicionarItem(new Pocao("Poção de Cura Menor", 5, todosPermitidos, 20, 0));
        vendedor.adicionarItem(new Pocao("Poção de Cura Média", 10, todosPermitidos, 50, 0));
        vendedor.adicionarItem(new Pocao("Poção de Cura Maior", 15, todosPermitidos, 80, 0));
        vendedor.adicionarItem(new Pocao("Poção da Sabedoria", 20, todosPermitidos, 40, 10));
        vendedor.adicionarItem(new Pocao("Poção de Vitalidade", 25, todosPermitidos, 60, 5));

        // Adiciona feitiços à loja do vendedor
        vendedor.adicionarItem(new ConsumivelCombate("Expelliarmus", 10, todosPermitidos, 15));
        vendedor.adicionarItem(new ConsumivelCombate("Stupefy", 15, todosPermitidos, 25));
        vendedor.adicionarItem(new ConsumivelCombate("Reducto", 20, todosPermitidos, 35));
        vendedor.adicionarItem(new ConsumivelCombate("Incendio", 12, todosPermitidos, 20));
        vendedor.adicionarItem(new ConsumivelCombate("Confringo", 18, todosPermitidos, 30));

        // Todos começam com uma varinha básica grátis
        heroi.setArmaPrincipal(new ArmaPrincipal("Varinha de Aprendiz", 0, todosPermitidos, 10, 20));

        Scanner scanner = new Scanner(System.in);
        boolean jogar = true;

        while (!jogar) {

            System.out.println("Escolha uma ação: ");
            System.out.println("1. Explorar");
            System.out.println("2. Visitar Vendedor");
            System.out.println("3. Status do Herói");
            System.out.println("4. Sair do Jogo");
            int escolha = scanner.nextInt();



        }
    }

    public void salaEntrada(){

    }

    public void salaEncontro(){

    }

    public void salaArmadilhas(){

    }

    public void salaItensPerdidos(){

    }

    public void salaVendedor(){

    }

    public void salaFinal(){

    }
}
