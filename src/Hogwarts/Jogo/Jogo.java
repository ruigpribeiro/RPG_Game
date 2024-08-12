package Hogwarts.Jogo;

import Hogwarts.Entidades.Herois.HarryPotter;
import Hogwarts.Entidades.Herois.HermioneGranger;
import Hogwarts.Entidades.Herois.Heroi;
import Hogwarts.Entidades.Herois.RonWeasley;
import Hogwarts.Entidades.NPC;
import Hogwarts.Entidades.Vendedor;
import Hogwarts.Itens.ArmaPrincipal;
import Hogwarts.Itens.ConsumivelCombate;
import Hogwarts.Itens.Pocao;

import java.util.*;

public class Jogo {
    private Scanner scanner;
    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[36m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String WHITE = "\u001B[37m";

    /**
     * Método construtor.
     */
    public Jogo() throws InterruptedException {
        scanner = new Scanner(System.in);
    }

    /**
     * Cria uma personagem com as escolhas do utilizador
     *
     * @return
     */
    public Heroi criarPersonagem() {
        // Escolha de herói
        Scanner scanner = new Scanner(System.in);
        System.out.println(YELLOW + "*** Escolhe o teu herói ***" + RESET);
        System.out.println(BLUE + "1. Harry Potter" + RESET);
        System.out.println(BLUE + "2. Hermione Granger" + RESET);
        System.out.println(BLUE + "3. Ron Weasley" + RESET);
        System.out.print(YELLOW + "Opção: " + RESET);
        int opcaoHeroi = scanner.nextInt();

        // Escolha de dificuldade
        System.out.println(YELLOW + "\n*** Escolhe a dificuldade ***" + RESET);
        System.out.println(GREEN + "1. Fácil" + RESET);
        System.out.println(RED + "2. Difícil" + RESET);
        System.out.print(YELLOW + "Opção: " + RESET);
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
        System.out.println(YELLOW + "\nTens " + pontosCriacao + " pontos de criação. Distribui-os entre vida e força." + RESET);
        System.out.print(GREEN + "Vida: " + RESET);
        int vida = scanner.nextInt();
        System.out.print(GREEN + "Força: " + RESET);
        int forca = scanner.nextInt();

        // Verifica se os pontos foram totalmente distribuidos
        while (vida + forca != pontosCriacao) {
            System.out.println(RED + "Pontos de distribuição incorretos!" + RESET);
            System.out.print(GREEN + "Pontos de vida: " + RESET);
            vida = scanner.nextInt();
            System.out.print(GREEN + "Força: " + RESET);
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


        boolean jogar = true;
        while (jogar) {
            // Sala de Entrada de Hogwarts
            salaEntradaHogwarts(heroi, vendedor);

            System.out.println(YELLOW + "\nEscolhe um labirinto para começar a tua aventura:" + RESET);
            System.out.println(BLUE + "1. Pedra Filosofal" + RESET);
            System.out.println(BLUE + "2. Câmara dos Segredos" + RESET);
            System.out.println(BLUE + "3. Prisioneiro de Azkaban" + RESET);
            System.out.println(BLUE + "4. Cálice de Fogo" + RESET);
            System.out.println(BLUE + "5. Ordem da Fénix" + RESET);
            System.out.println(BLUE + "6. Príncipe Misterioso" + RESET);
            System.out.println(BLUE + "7. Talismãs da Morte" + RESET);
            System.out.print(YELLOW + "Opção: " + RESET);
            int opcaoFilme = scanner.nextInt();

            // Encaminha cada escolha para a sua sala
            switch (opcaoFilme) {
                case 1:
                    pedraFilosofal(heroi);
                    break;
                case 2:
                    //camaraDosSegredos(heroi);
                    break;
                case 3:
                    //prisioneiroAzkaban(heroi);
                    break;
                case 4:
                    //caliceFogo(heroi);
                    break;
                case 5:
                    //ordemFenix(heroi);
                    break;
                case 6:
                    //principeMisterioso(heroi);
                    break;
                case 7:
                    //talismasMorte(heroi);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }


            System.out.println(GREEN + "\nDeseja continuar jogando?" + RESET);
            System.out.println(YELLOW + "1. Sim" + RESET);
            System.out.println(RED + "2. Não" + RESET);
            System.out.print(YELLOW + "Opção: " + RESET);
            int continuar = scanner.nextInt();
            if (continuar == 2) {
                jogar = false;
                System.out.println(GREEN + "Obrigado por jogar!" + RESET);
            }
        }
    }

    private void pedraFilosofal(Heroi heroi) throws InterruptedException {
        System.out.println("\nEscolheste: Pedra Filosofal");
        salaFlorestaProibida(heroi);
    }

    private void salaFlorestaProibida(Heroi heroi) {
        System.out.println("\n** Sala 1: Floresta Proibida **");
        System.out.println("Escolhe um caminho:");
        System.out.println("1. Explorar a floresta.");
        System.out.println("2. Seguir uma trilha de pegadas.");

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            System.out.println("Encontraste um centauro que avisa-te sobre o perigo que corres.");
            // Adicionar algum efeito ou interação.
        } if (escolha == 2) {
            System.out.println("Encontraste o Voldemort a beber sangue de unicórnio!");
            NPC voldemort = new NPC("Voldemort", 100, 20, 15);
            heroi.atacar(voldemort);
        } else {
            System.out.println("Escolha inválida.");
            salaFlorestaProibida(heroi);
        }

        salaXadrezGigante(heroi);
    }

    private void salaXadrezGigante(Heroi heroi){
        System.out.println("\n** Sala 2: Sala do Xadrez Gigante **");
        System.out.println("Escolhe um caminho:");
        System.out.println("1. Participar no jogo de xadrez gigante.");
        System.out.println("2. Procurar um atalho.");

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            System.out.println("Estás em um duelo de xadrez! Derrota o adversário.");
            // Implementar lógica do duelo.
        } else if (escolha == 2) {
            System.out.println("Encontraste peças guardiãs menores, prepare-te para lutar!");
            // Implementar batalha contra peças menores.
        } else {
            System.out.println("Escolha inválida.");
            salaXadrezGigante(heroi);
        }

        salaEnigmaPocoes(heroi);
    }

    private void salaEnigmaPocoes(Heroi heroi){
        System.out.println("\n** Sala 3: Enigma das Poções **");
        System.out.println("Escolhe um caminho:");
        System.out.println("1. Resolver o enigma do Professor Snape.");
        System.out.println("2. Tentar adivinhar a poção correta.");

        int escolha = scanner.nextInt();
        Random random = new Random();
        if (escolha == 1) {
            System.out.println("Resolveste o enigma e podes avançar com segurança.");
        } else if (escolha == 2) {
            int sorte = random.nextInt(2);
            if (sorte == 0) {
                System.out.println("Escolheste a poção correta e podes avançar!");
            } else {
                System.out.println("Escolha errada! Sofreste algum dano.");
                heroi.setVidaAtual(heroi.getVidaAtual() - 10);
            }
        } else {
            System.out.println("Escolha inválida.");
            salaEnigmaPocoes(heroi);
        }

        salaFinalPedraFilosofal(heroi);
    }

    private void salaFinalPedraFilosofal(Heroi heroi) {
        System.out.println("\n** Sala Final: Confronto com Voldemort **");
        NPC voldemort = new NPC("Voldemort", 150, 30, 20);
        heroi.atacar(voldemort);

        if (heroi.getVidaAtual() > 0) {
            System.out.println("Derrotaste o Voldemort e salvaste a Pedra Filosofal!");
        } else {
            System.out.println("Voldemort venceu. Fim de jogo.");
        }
    }


    public void salaEntradaHogwarts(Heroi heroi, Vendedor vendedor) throws InterruptedException {
        // Introdução do jogo
        System.out.println(CYAN + "\nBem-vindo à escola de Hogwarts, " + heroi.getNome() + "!");
        System.out.println("Tu foste escolhido para enfrentar os desafios do Labirinto de Hogwarts.");
        System.out.println("O teu objetivo é ultrapassar todos os obstáculos e derrotar o tenebroso Voldemort." + RESET + "\n");
        Thread.sleep(1000);

        while (true) {
            // Imprime o ouro do herói
            System.out.println(CYAN + heroi.getNome() + ", tens " + heroi.getOuro() + " moedas de ouro." + RESET + "\n");

            List<Integer> indicesUsados = vendedor.mostrarItensLoja();
            System.out.print(CYAN + "Escolhe um ítem (ou 0 para continuar): " + RESET);
            int opcao = scanner.nextInt();

            if (opcao == 0) {
                break;
            }

            if (opcao > 0 && opcao <= indicesUsados.size()) {
                int indiceCorreto = indicesUsados.get(opcao - 1);
                vendedor.venderItem(heroi, indiceCorreto);
            } else {
                System.out.println(RED + "Opção inválida, tente novamente." + RESET);
            }
        }
    }

    /*
    public void salaFlorestaProibida(Heroi heroi, List<String> heroisPermitidos) {
        // Mensagem de entrada
        System.out.println(CYAN + "\nEntraste na Floresta Proibida, um lugar sombrio e cheio de criaturas perigosas." + RESET);

        Random random = new Random();
        int rand = random.nextInt(2);

        if (rand == 0) {
            System.out.println(GREEN + "Um Centauro apareceu à tua frente. Ele avisou-te sobre o perigo e ofereceu-te uma poção!" + RESET);
            // Adiciona uma poção ao inventário
            Pocao pocao = new Pocao("Poção de Vida", 0, heroisPermitidos, 15, 5);
            heroi.adicionarItemAoInventario(pocao);
        } else {
            System.out.println(RED + "Foste atacado por uma Acromântula! Prepara-te para lutar!" + RESET);
            NPC acromantula = new NPC("Acromântula", 100, 20, 10);
            heroi.atacar(acromantula);
        }

        // Mensagem de saída
        System.out.println(CYAN + "\nDepois de enfrentar os perigos da Floresta Proibida, " + heroi.getNome() + " vai continuar a sua viagem." + RESET);
    }


    private void salaRequisitos(Heroi heroi) {
        // Mensagem de entrada
        System.out.println(CYAN + "\nEntraste na Sala de Requisitos. Ela molda-se às tuas necessidades..." + RESET);

        // Totem Mágico com efeitos aleatórios
        System.out.println(CYAN + "No centro da sala, um Totem Mágico brilha intensamente. Ele pode conceder-te ouro ou causar-te dano." + RESET);

        Random random = new Random();
        int efeito = random.nextInt(2);

        if (efeito == 0) {
            int ouroGanho = random.nextInt(50) + 1;
            heroi.setOuro(heroi.getOuro() + ouroGanho);
            System.out.println(GREEN + "O Totem concedeu-te " + ouroGanho + " moedas de ouro!" + RESET);
        } else {
            int danoRecebido = random.nextInt(30) + 1;
            heroi.setVidaAtual(heroi.getVidaAtual() - danoRecebido);
            System.out.println(RED + "O Totem lançou-te uma maldição! Perdeste " + danoRecebido + " pontos de vida." + RESET);
        }

        // Verifica se o herói ainda está vivo
        if (heroi.getVidaAtual() <= 0) {
            System.out.println(RED + "Foste derrotado pela maldição do Totem. O jogo terminou." + RESET);
        }
    }

    private void salaBiblioteca(Heroi heroi, List<String> heroisPermitidos) {
        // Mensagem de entrada
        System.out.println(CYAN + "\nEntraste na Biblioteca de Hogwarts. Entre as prateleiras empoeiradas, avistaste algo a brilhar." + RESET);

        // Encontrar itens perdidos
        Pocao pocao = new Pocao("Poção de Vida", 0, heroisPermitidos, 15, 5);
        ArmaPrincipal varinhaFenix = new ArmaPrincipal("Varinha de Fênix", 0, heroisPermitidos, 30, 50);

        System.out.println(GREEN + "Encontraste uma Poção de Vida e a Varinha de Fênix perdida!" + RESET);
        heroi.adicionarItemAoInventario(pocao);
        heroi.setArmaPrincipal(varinhaFenix);

        System.out.println(GREEN + "Os itens foram adicionados ao teu inventário." + RESET);
    }

    private void salaCamaraDosSegredos(Heroi heroi) {
        // Mensagem de entrada
        System.out.println(CYAN + "\nEntraste na Câmara dos Segredos. O ambiente é frio e o ar está denso com uma magia antiga." + RESET);

        // Enfrentar um inimigo
        NPC basilisco = new NPC("Basilisco", 100, 25, 25);
        System.out.println(RED + "O Basilisco emerge das sombras! Prepara-te para lutar!" + RESET);
        heroi.atacar(basilisco);

        if (heroi.getVidaAtual() > 0) {
            System.out.println(GREEN + "Derrotaste o Basilisco! A Câmara dos Segredos está segura... por enquanto." + RESET);
            heroi.mostrarDetalhes();
        } else {
            System.out.println(RED + "Foste derrotado pelo Basilisco. O jogo terminou." + RESET);
        }
    }

    public void salaPocoes(Heroi heroi, List<String> heroisPermitidos) {
        // Mensagem de entrada
        System.out.println(CYAN + "\nEntraste na Sala das Poções, onde o Professor Snape está à tua espera." + RESET);

        // Snape oferece uma poção em troca de ouro
        System.out.println(CYAN + "Snape oferece-te uma Poção de Vida por 10 moedas de ouro." + RESET);
        System.out.print(CYAN + "Queres comprar? (1 para Sim, 0 para Não): " + RESET);
        int opcao = scanner.nextInt();

        if (opcao == 1) {
            if (heroi.getOuro() >= 10) {
                heroi.setOuro(heroi.getOuro() - 10);
                Pocao pocao = new Pocao("Poção de Vida", 10, heroisPermitidos, 15, 5);
                heroi.adicionarItemAoInventario(pocao);
                System.out.println(GREEN + "Compraste a Poção de Vida." + RESET);
            } else {
                System.out.println(RED + "Não tens ouro suficiente para comprar a poção." + RESET);
            }
        } else {
            System.out.println(CYAN + "Recusaste a oferta do Professor Snape." + RESET);
        }

        // Mensagem de saída
        System.out.println(CYAN + "\nDepois de explorar a Sala das Poções, " + heroi.getNome() + " vai continuar a sua viagem." + RESET);
    }

    private void salaSalaoPrincipal(Heroi heroi, Vendedor vendedor) throws InterruptedException {
        System.out.println(CYAN + "Chegaste ao Salão Principal." + RESET);
        System.out.println(CYAN + "O vendedor está aqui com novos itens para ti." + RESET);

        salaEntradaHogwarts(heroi, vendedor);
    }

    private void salaMasmorras(Heroi heroi) {
        System.out.println(CYAN + "Desceste para as Masmorras, onde inimigos aguardam..." + RESET);

        // Gera aleatoriamente entre 1 a 3 inimigos
        Random random = new Random();
        int numeroInimigos = random.nextInt(3) + 1;

        for (int i = 0; i < numeroInimigos; i++) {
            if (i == 0) {
                NPC basilisco = new NPC("Dementador", 75, 15, 20);
                System.out.println(RED + "Encontraste um inimigo: " + basilisco.getNome() + RESET);
                heroi.atacar(basilisco);
                if (heroi.getVidaAtual() <= 0) {
                    System.out.println(RED + "Foste derrotado..." + RESET);
                    return;
                }
            }
            if (i == 1) {
                NPC troll = new NPC("Troll", 50, 10, 15);
                System.out.println(RED + "Encontraste um inimigo: " + troll.getNome() + RESET);
                heroi.atacar(troll);
                if (heroi.getVidaAtual() <= 0) {
                    System.out.println(RED + "Foste derrotado..." + RESET);
                    return;
                }
            }
            if (i == 2) {
                NPC aranhaNegra = new NPC("Aranha Negra", 25, 5, 10);
                System.out.println(RED + "Encontraste um inimigo: " + aranhaNegra.getNome() + RESET);
                heroi.atacar(aranhaNegra);
                if (heroi.getVidaAtual() <= 0) {
                    System.out.println(RED + "Foste derrotado..." + RESET);
                    return;
                }
            }
        }

        System.out.println(GREEN + "Derrotaste todos os inimigos e podes seguir para a próxima sala." + RESET);
    }

    public void salaEstufaHerbologia(Heroi heroi) {
        System.out.println(CYAN + "\nChegaste à Estufa de Herbologia e encontraste algumas moedas de ouro entre as plantas." + RESET);
        int ouroEncontrado = 50;
        heroi.setOuro(heroi.getOuro() + ouroEncontrado);
        System.out.println(GREEN + "Encontraste " + ouroEncontrado + " moedas de ouro." + RESET);
    }

    public void salaCorvinal(Heroi heroi) {
        System.out.println(CYAN + "\nA Sala da Corvinal está cheia de armadilhas mágicas." + RESET);
        Random random = new Random();
        int chanceArmadilha = random.nextInt(100);

        if (chanceArmadilha < 50) { // 50% de chance de evitar a armadilha
            System.out.println(GREEN + "Conseguiste evitar a armadilha!" + RESET);
        } else {
            int danoArmadilha = random.nextInt(20) + 10; // Dano entre 10 e 30
            heroi.setVidaAtual(heroi.getVidaAtual() - danoArmadilha);
            System.out.println(RED + "Caiste numa armadilha e perdeste " + danoArmadilha + " pontos de vida." + RESET);
        }
    }

    public void salaFinal() {

    }*/
}
