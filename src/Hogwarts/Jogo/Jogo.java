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
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Jogo {
    private Scanner scanner;

    /**
     * Cria uma personagem com as escolhas do utilizador
     *
     * @return
     */
    public Heroi criarPersonagem() {
        // Escolha de herói
        Scanner scanner = new Scanner(System.in);
        System.out.println("*** Escolhe o teu herói ***");
        System.out.println("1. Harry Potter");
        System.out.println("2. Hermione Granger");
        System.out.println("3. Ron Weasley");
        System.out.print("Opção: ");
        int opcaoHeroi = scanner.nextInt();

        // Escolha de dificuldade
        System.out.println("\n*** Escolhe a dificuldade ***");
        System.out.println("1. Fácil");
        System.out.println("2. Difícil");
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
        System.out.println("\nTens " + pontosCriacao + " pontos de criação. Distribui-os entre vida e força.");
        System.out.print("Vida: ");
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
                heroi = new HarryPotter("Harry Potter", vida, forca, 1, ouroInicial);
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
     *
     * @param heroi O herói que vai jogar
     */
    public void labirintoHogwarts(Heroi heroi) throws InterruptedException {
        Vendedor vendedor = new Vendedor();

        // Listas com os vários heróis
        List<String> todosPermitidos = Arrays.asList("HarryPotter", "HermioneGranger", "RonWeasley");
        List<String> apenasHarry = Arrays.asList("HarryPotter");
        List<String> apenasHermione = Arrays.asList("HermioneGranger");
        List<String> apenasRon = Arrays.asList("RonWeasley");

        // Adicionar armas à loja do vendedor
        vendedor.adicionarItem(new ArmaPrincipal("Varinha de Olivanders", 25, todosPermitidos, 15, 30));
        vendedor.adicionarItem(new ArmaPrincipal("Espada de Gryffindor", 30, todosPermitidos, 20, 40));
        vendedor.adicionarItem(new ArmaPrincipal("Varinha do Dragão", 20, todosPermitidos, 18, 35));
        vendedor.adicionarItem(new ArmaPrincipal("Cajado de Dumbledore", 25, todosPermitidos, 22, 45));
        vendedor.adicionarItem(new ArmaPrincipal("Varinha de Fênix", 30, todosPermitidos, 25, 50));
        vendedor.adicionarItem(new ArmaPrincipal("Varinha do Harry", 20, apenasHarry, 20, 30));
        vendedor.adicionarItem(new ArmaPrincipal("Varinha da Hermione", 20, apenasHermione, 15, 25));
        vendedor.adicionarItem(new ArmaPrincipal("Varinha do Ron", 20, apenasRon, 10, 20));

        // Adicionar poções de cura à loja do vendedor
        vendedor.adicionarItem(new Pocao("Poção de Cura Menor", 5, todosPermitidos, 10, 0));
        vendedor.adicionarItem(new Pocao("Poção de Cura Média", 10, todosPermitidos, 15, 0));
        vendedor.adicionarItem(new Pocao("Poção de Cura Maior", 15, todosPermitidos, 20, 0));
        vendedor.adicionarItem(new Pocao("Poção da Sabedoria", 20, todosPermitidos, 25, 10));
        vendedor.adicionarItem(new Pocao("Poção de Vitalidade", 25, todosPermitidos, 30, 15));

        // Adicionar feitiços à loja do vendedor
        vendedor.adicionarItem(new ConsumivelCombate("Expelliarmus", 10, apenasHarry, 50));
        vendedor.adicionarItem(new ConsumivelCombate("Petrificus Totalus", 10, apenasHermione, 40));
        vendedor.adicionarItem(new ConsumivelCombate("Wingardium Leviosa", 10, apenasRon, 30));
        vendedor.adicionarItem(new ConsumivelCombate("Stupefy", 15, todosPermitidos, 25));
        vendedor.adicionarItem(new ConsumivelCombate("Reducto", 20, todosPermitidos, 35));
        vendedor.adicionarItem(new ConsumivelCombate("Incendio", 12, todosPermitidos, 20));
        vendedor.adicionarItem(new ConsumivelCombate("Confringo", 18, todosPermitidos, 30));

        // Todos começam com uma varinha básica grátis
        heroi.setArmaPrincipal(new ArmaPrincipal("Varinha de Aprendiz", 0, todosPermitidos, 10, 20));
        /*
        // Lista de salas
        List<Sala> salas = new ArrayList<>();

        // Criar salas
        Sala sala1 = new Sala("Entrada de Hogwarts", 1, vendedor);
        Sala sala2 = new Sala("Floresta Proibida", 2, vendedor);
        Sala sala3 = new Sala("Sala das Poções", 3, vendedor);
        Sala sala4 = new Sala("Câmara dos Segredos", 4, vendedor);
        Sala sala5 = new Sala("Biblioteca", 5, vendedor);
        Sala sala6 = new Sala("Salão Principal", 6, vendedor);
        Sala sala7 = new Sala("Masmorras", 7, vendedor);
        Sala sala8 = new Sala("Sala Precisa", 8, vendedor);
        Sala sala9 = new Sala("Torre de Astronomia", 9, vendedor);
        Sala sala10 = new Sala("Estufa de Herbologia", 10, vendedor);
        Sala sala11 = new Sala("Campo de Quadribol", 11, vendedor);
        Sala sala12 = new Sala("Sala de Gryffindor", 12, vendedor);
        Sala sala13 = new Sala("Sala de Slytherin", 13, vendedor);
        Sala sala14 = new Sala("Sala de Ravenclaw", 14, vendedor);
        Sala sala15 = new Sala("Sala de Hufflepuff", 15, vendedor);
        Sala sala16 = new Sala("Encontro com Voldemort", 16, vendedor);

        // Conetar salas
        sala1.adicionarSalaConetada(2);
        sala1.adicionarSalaConetada(3);
        sala2.adicionarSalaConetada(4);
        sala2.adicionarSalaConetada(5);
        sala3.adicionarSalaConetada(6);
        sala3.adicionarSalaConetada(7);
        sala4.adicionarSalaConetada(8);
        sala5.adicionarSalaConetada(9);
        sala6.adicionarSalaConetada(10);
        sala7.adicionarSalaConetada(11);
        sala8.adicionarSalaConetada(12);
        sala9.adicionarSalaConetada(13);
        sala10.adicionarSalaConetada(14);
        sala11.adicionarSalaConetada(15);
        sala12.adicionarSalaConetada(16);

        */
        scanner = new Scanner(System.in);
        boolean jogar = true;

        while (true) {

            // Sala Inicial
            salaEntradaHogwarts(heroi, vendedor);

            System.out.println("Escolha a próxima sala:");
            System.out.println("1. Floresta Proíbida");
            System.out.println("2. Sala das Poções");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    salaFlorestaProibida();
                case 2:
                    salaPocoes();
            }

        }
    }

    /**
     * Sala Ínicial onde o vendedor mostra uma lista de ítens dísponivel para comprar
     * @param heroi O herói
     * @param vendedor O vendedor
     */
    public void salaEntradaHogwarts(Heroi heroi, Vendedor vendedor) throws InterruptedException {

        // Introdução do jogo
        System.out.println("\nBem-vindo à escola de Hogwarts, " + heroi.getNome() + "!");
        System.out.println("Tu foste escolhido para enfrentar os desafios do Labirinto de Hogwarts.");
        System.out.println("O teu objetivo é ultrapassar todos os obstáculos e derrotar o tenebroso Voldemort.\n");
        Thread.sleep(1000);

        while (true) {
            // Imprime o ouro do herói
            System.out.println(heroi.getNome() + ", tens " + heroi.getOuro() + " moedas de ouro.\n");

            List<Integer> indicesUsados = vendedor.mostrarItensLoja();
            System.out.print("Escolhe um ítem (ou 0 para continuar): ");
            int opcao = scanner.nextInt();

            if (opcao == 0) {
                break;
            }

            int indiceCorreto = indicesUsados.get(opcao-1);
            vendedor.venderItem(heroi, indiceCorreto);
        }
    }

    public void salaFlorestaProibida() {

    }

    public void salaPocoes() {

    }

    public void salaItensPerdidos() {

    }

    public void salaVendedor() {

    }

    public void salaFinal() {

    }
}
