package Hogwarts.Jogo;

import Hogwarts.Entidades.Herois.HarryPotter;
import Hogwarts.Entidades.Herois.HermioneGranger;
import Hogwarts.Entidades.Herois.Heroi;
import Hogwarts.Entidades.Herois.RonWeasley;
import Hogwarts.Entidades.Inimigos.NPC;
import Hogwarts.Entidades.Vendedor;
import Hogwarts.Itens.ArmaPrincipal;
import Hogwarts.Itens.ConsumivelCombate;
import Hogwarts.Itens.Pocao;

import java.util.*;

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
            System.out.print("Pontos de vida: ");
            vida = scanner.nextInt();
            System.out.print("Força: ");
            forca = scanner.nextInt();
        }

        // Cria uma instância do herói escolhido com o feedback do utilizador
        Heroi heroi = null;
        switch (opcaoHeroi) {
            case 1:
                heroi = new HarryPotter("Harry Potter", vida, (forca / 5), 1, ouroInicial);
                break;
            case 2:
                heroi = new HermioneGranger("Hermione Granger", vida, (forca / 5), 1, ouroInicial);
                break;
            case 3:
                heroi = new RonWeasley("Ron Weasley", vida, (forca / 5), 1, ouroInicial);
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

            System.out.println("\nEscolha a próxima sala:");
            System.out.println("1. Floresta Proíbida");
            System.out.println("2. Sala das Poções");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    salaFlorestaProibida(heroi, todosPermitidos);
                    break;
                case 2:
                    salaPocoes(heroi, todosPermitidos);
                    break;
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

    public void salaFlorestaProibida(Heroi heroi, List<String> heroisPermitidos) {
        // Mensagem de entrada
        System.out.println("\nEntraste na Floresta Proibida, um lugar sombrio e cheio de criaturas perigosas.");

        Random random = new Random();
        int rand = random.nextInt(2);

        if (rand == 0) {
            System.out.println("Um Centauro apareceu à tua frente. Ele avisou-te sobre o perigo e ofereceu-te uma poção!");
            // Adiciona uma poção ao inventário
            Pocao pocao = new Pocao("Poção de Vida", 0, heroisPermitidos, 15, 5);
            heroi.adicionarItemAoInventario(pocao);
        } else {
            System.out.println("Foste atacado por uma Acromântula! Prepara-te para lutar!");
            NPC acromantula = new NPC("Acromântula", 100, 20, 10);
            heroi.atacar(acromantula);
        }

        // Mensagem de saída
        System.out.println("\nDepois de enfrentar os perigos da Floresta Proibida, " + heroi.getNome() + " vai continuar a sua viagem.");

        // Escolha das próximas salas
        System.out.println("\nEscolha a próxima sala:");
        System.out.println("1. Câmara dos Segredos");
        System.out.println("2. Biblioteca");
        System.out.println("3. Sala de Requisitos");
        System.out.print("Opção: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                salaCamaraDosSegredos(heroi);
                break;
            case 2:
                salaBiblioteca(heroi, heroisPermitidos);
                break;
            case 3:
                salaRequisitos(heroi);
                break;
        }

    }

    private void salaRequisitos(Heroi heroi) {
        // Mensagem de entrada
        System.out.println("\nEntraste na Sala de Requisitos. Ela molda-se às tuas necessidades...");

        // Totem Mágico com efeitos aleatórios
        System.out.println("No centro da sala, um Totem Mágico brilha intensamente. Ele pode conceder-te ouro ou causar-te dano.");

        Random random = new Random();
        int efeito = random.nextInt(2);

        if (efeito == 0) {
            int ouroGanho = random.nextInt(50) + 1;
            heroi.setOuro(heroi.getOuro() + ouroGanho);
            System.out.println("O Totem concedeu-te " + ouroGanho + " moedas de ouro!");
        } else {
            int danoRecebido = random.nextInt(30) + 1;
            heroi.setVidaAtual(heroi.getVidaAtual() - danoRecebido);
            System.out.println("O Totem lançou-te uma maldição! Perdeste" + danoRecebido + " pontos de vida.");
        }

        // Verifica se o herói ainda está vivo
        if (heroi.getVidaAtual() <= 0) {
            System.out.println("Foste derrotado pela maldição do Totem. O jogo terminou.");
        }
    }

    private void salaBiblioteca(Heroi heroi, List<String> heroisPermitidos) {
        // Mensagem de entrada
        System.out.println("\nEntraste na Biblioteca de Hogwarts. Entre as prateleiras empoeiradas, avistaste algo a brilhar.");

        // Encontrar itens perdidos
        Pocao pocao = new Pocao("Poção de Vida", 0, heroisPermitidos, 15, 5);
        ArmaPrincipal varinhaFenix = new ArmaPrincipal("Varinha de Fênix", 0, heroisPermitidos, 30, 50);

        System.out.println("Encontraste uma Poção de Vida e a Varinha de Fênix perdida!");
        heroi.adicionarItemAoInventario(pocao);
        heroi.setArmaPrincipal(varinhaFenix);

        System.out.println("Os itens foram adicionados ao teu inventário.");
    }

    private void salaCamaraDosSegredos(Heroi heroi) {
        // Mensagem de entrada
        System.out.println("\nEntraste na Câmara dos Segredos. O ambiente é frio e o ar está denso com uma magia antiga.");

        // Enfrentar um inimigo
        NPC basilisco = new NPC("Basilisco", 150, 25, 25);
        System.out.println("O Basilisco emerge das sombras! Prepara-te para lutar!");
        heroi.atacar(basilisco);

        if (heroi.getVidaAtual() > 0) {
            System.out.println("Derrotaste o Basilisco! A Câmara dos Segredos está segura... por enquanto.");
        } else {
            System.out.println("Foste derrotado pelo Basilisco. O jogo terminou.");
            // Finalizar ou reiniciar o jogo
        }
    }

    public void salaPocoes(Heroi heroi, List<String> heroisPermitidos) {
        // Mensagem de entrada
        System.out.println("\nEntraste na Sala das Poções, onde o Professor Snape está à tua espera.");

        // Snape oferece uma poção em troca de ouro
        System.out.println("Snape oferece-te uma Poção de Vida por 10 moedas de ouro.");
        System.out.print("Queres comprar? (1 para Sim, 0 para Não): ");
        int opcao = scanner.nextInt();

        if (opcao == 1 && heroi.getOuro() >= 10) {
            heroi.setOuro(heroi.getOuro() - 10);
            Pocao pocao = new Pocao("Poção de Vida", 10, heroisPermitidos, 15, 5);
            heroi.adicionarItemAoInventario(pocao);
            System.out.println("Compraste a Poção de Vida.");
        } else if (opcao == 1) {
            System.out.println("Não tens ouro suficiente para comprar a poção.");
        } else {
            System.out.println("Recusaste a oferta do Professor Snape.");
        }

        // Mensagem de saída
        System.out.println("\nDepois de explorar a Sala das Poções, " + heroi.getNome() + " vai continuar a sua viagem.");

    }

    public void salaItensPerdidos() {

    }

    public void salaVendedor() {

    }

    public void salaFinal() {

    }
}
