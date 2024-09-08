package Hogwarts.jogo;

import Hogwarts.entidades.Herois.HarryPotter;
import Hogwarts.entidades.Herois.HermioneGranger;
import Hogwarts.entidades.Herois.Heroi;
import Hogwarts.entidades.Herois.RonWeasley;
import Hogwarts.entidades.NPC;
import Hogwarts.entidades.Vendedor;
import Hogwarts.itens.*;
import Hogwarts.utils.Audio;
import Hogwarts.utils.ConsoleUtils;

import java.util.*;

import java.util.Scanner;

public class Jogo {
    private Scanner scanner;

    /**
     * Método construtor.
     */
    public Jogo() {
        scanner = new Scanner(System.in);
    }

    /**
     * Cria uma personagem selecionando um herói e a dificuldade do jogo,
     * distribuindo pontos de criação entre vida e força e retorna o herói criado.
     *
     * @return o objeto Heroi criado com nome, vida, força e ouro inicial conforme a seleção do jogador.
     * @throws InterruptedException se o método Thread.sleep for interrompido.
     */
    public Heroi criarPersonagem() throws InterruptedException {

        ConsoleUtils.printTitle();
        Thread.sleep(1000);

        // Escolha de herói
        ConsoleUtils.typeWriter(ConsoleUtils.YELLOW + "*** Escolhe o teu herói ***" + ConsoleUtils.RESET, 0);
        ConsoleUtils.typeWriter(ConsoleUtils.BLUE + "1. Harry Potter" + ConsoleUtils.RESET, 0);
        ConsoleUtils.typeWriter(ConsoleUtils.BLUE + "2. Hermione Granger" + ConsoleUtils.RESET, 0);
        ConsoleUtils.typeWriter(ConsoleUtils.BLUE + "3. Ron Weasley" + ConsoleUtils.RESET, 0);
        System.out.print(ConsoleUtils.YELLOW + "Opção: " + ConsoleUtils.RESET);
        int opcaoHeroi = scanner.nextInt();

        // Escolha de dificuldade
        ConsoleUtils.typeWriter(ConsoleUtils.YELLOW + "\n*** Escolhe a dificuldade ***" + ConsoleUtils.RESET, 0);
        ConsoleUtils.typeWriter(ConsoleUtils.GREEN + "1. Fácil" + ConsoleUtils.RESET, 0);
        ConsoleUtils.typeWriter(ConsoleUtils.RED + "2. Difícil" + ConsoleUtils.RESET, 0);
        System.out.print(ConsoleUtils.YELLOW + "Opção: " + ConsoleUtils.RESET);
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

        ConsoleUtils.typeWriter(ConsoleUtils.PURPLE + "\nAntes de começares a tua aventura, precisas de te preparar. Vais precisar de equilíbrio entre força e resistência." + ConsoleUtils.RESET, 25);
        ConsoleUtils.typeWriter(ConsoleUtils.PURPLE + "Distribui os teus pontos de criação sabiamente. A tua escolha pode fazer a diferença entre a vida e a morte nos corredores de Hogwarts!" + ConsoleUtils.RESET, 25);

        // Distribui os pontos de criação
        System.out.println(ConsoleUtils.YELLOW_BOLD + "\nTens " + pontosCriacao + " pontos de criação. Distribui-os entre vida e força." + ConsoleUtils.RESET);
        System.out.print(ConsoleUtils.GREEN + "Vida: " + ConsoleUtils.RESET);
        int vida = scanner.nextInt();
        System.out.print(ConsoleUtils.GREEN + "Força: " + ConsoleUtils.RESET);
        int forca = scanner.nextInt();

        // Verifica se os pontos foram totalmente distribuidos
        while ((vida + forca) != pontosCriacao) {
            System.out.println(ConsoleUtils.RED + "\nPontos de distribuição incorretos!" + ConsoleUtils.RESET);
            System.out.println(ConsoleUtils.YELLOW_BOLD + "Tens " + (pontosCriacao - vida - forca) + " restantes por atribuir\n" + ConsoleUtils.RESET);
            System.out.print(ConsoleUtils.GREEN + "Vida: " + ConsoleUtils.RESET);
            int vidaRestante = scanner.nextInt();
            vida += vidaRestante;
            System.out.print(ConsoleUtils.GREEN + "Força: " + ConsoleUtils.RESET);
            int forcaRestante = scanner.nextInt();
            forca += forcaRestante;
        }

        // Criação do herói
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

        ConsoleUtils.typeWriter(ConsoleUtils.CYAN + "\nO destino do mundo mágico está agora nas tuas mãos, " + heroi.getNome() + "!" + ConsoleUtils.RESET, 50);
        System.out.println("\n==========================================================================================================");
        return heroi;
    }


    /**
     * Inicializa e executa a aventura no labirinto de Hogwarts com o herói especificado.
     * A aventura inclui a sala de entrada de Hogwarts, interação com um vendedor para
     * adquirir itens e escolha de labirintos para explorar.
     *
     * @param heroi O herói que participará da aventura. O herói deve ser do tipo
     *              Heroi. O herói começará a aventura com uma varinha básica.
     * @throws InterruptedException Se a execução for interrompida durante a execução do método.
     */
    public void labirintoHogwarts(Heroi heroi) throws InterruptedException {
        // Música de background
        Audio.playMusic("resources/hedwigs_theme.wav");

        Vendedor vendedor = new Vendedor();

        // Listas com os vários heróis permitidos
        List<String> todosPermitidos = Arrays.asList("HarryPotter", "HermioneGranger", "RonWeasley");
        List<String> apenasHarry = Arrays.asList("HarryPotter");
        List<String> apenasHermione = Arrays.asList("HermioneGranger");
        List<String> apenasRon = Arrays.asList("RonWeasley");

        // Adicionar armas à loja do vendedor
        vendedor.adicionarItem(new ArmaPrincipal("Espada de Gryffindor", 20, todosPermitidos, 20, 40));
        vendedor.adicionarItem(new ArmaPrincipal("Cajado de Dumbledore", 25, todosPermitidos, 22, 45));
        vendedor.adicionarItem(new ArmaPrincipal("Varinha de Fênix", 30, todosPermitidos, 25, 50));
        vendedor.adicionarItem(new ArmaPrincipal("Varinha do Harry", 10, apenasHarry, 20, 30));
        vendedor.adicionarItem(new ArmaPrincipal("Varinha da Hermione", 10, apenasHermione, 15, 25));
        vendedor.adicionarItem(new ArmaPrincipal("Varinha do Ron", 10, apenasRon, 10, 20));

        // Adicionar poções de cura à loja do vendedor
        vendedor.adicionarItem(new Pocao("Poção de Cura Menor", 5, todosPermitidos, 10, 0));
        vendedor.adicionarItem(new Pocao("Poção de Cura Média", 10, todosPermitidos, 15, 0));
        vendedor.adicionarItem(new Pocao("Poção de Cura Maior", 15, todosPermitidos, 20, 0));
        vendedor.adicionarItem(new Pocao("Poção da Sabedoria", 20, todosPermitidos, 25, 10));
        vendedor.adicionarItem(new Pocao("Poção de Vitalidade", 25, todosPermitidos, 30, 15));

        // Adicionar feitiços à loja do vendedor
        vendedor.adicionarItem(new ConsumivelCombate("Feitiço: Expelliarmus", 15, apenasHarry, 40));
        vendedor.adicionarItem(new ConsumivelCombate("Feitiço: Petrificus Totalus", 12, apenasHermione, 30));
        vendedor.adicionarItem(new ConsumivelCombate("Feitiço: Wingardium Leviosa", 8, apenasRon, 20));
        vendedor.adicionarItem(new ConsumivelCombate("Feitiço: Stupefy", 8, todosPermitidos, 15));
        vendedor.adicionarItem(new ConsumivelCombate("Feitiço: Reducto", 10, todosPermitidos, 25));
        vendedor.adicionarItem(new ConsumivelCombate("Feitiço: Incendio", 6, todosPermitidos, 10));
        vendedor.adicionarItem(new ConsumivelCombate("Feitiço: Confringo", 8, todosPermitidos, 20));

        // Todos começam com uma varinha básica grátis
        heroi.setArmaPrincipal(new ArmaPrincipal("Varinha de Aprendiz", 0, todosPermitidos, 10, 20));

        boolean jogar = true;
        while (jogar) {

            // Sala de Entrada de Hogwarts
            salaEntradaHogwarts(heroi, vendedor);

            System.out.println(ConsoleUtils.YELLOW + "\nEscolhe um labirinto para começar a tua aventura:" + ConsoleUtils.RESET);
            System.out.println(ConsoleUtils.BLUE + "1. Pedra Filosofal" + ConsoleUtils.RESET);
            System.out.println(ConsoleUtils.BLUE + "2. Câmara dos Segredos - UPCOMING" + ConsoleUtils.RESET);
            System.out.println(ConsoleUtils.BLUE + "3. Prisioneiro de Azkaban - UPCOMING" + ConsoleUtils.RESET);
            System.out.println(ConsoleUtils.BLUE + "4. Cálice de Fogo - UPCOMING" + ConsoleUtils.RESET);
            System.out.println(ConsoleUtils.BLUE + "5. Ordem da Fénix - UPCOMING" + ConsoleUtils.RESET);
            System.out.println(ConsoleUtils.BLUE + "6. Príncipe Misterioso - UPCOMING" + ConsoleUtils.RESET);
            System.out.println(ConsoleUtils.BLUE + "7. Talismãs da Morte - UPCOMING" + ConsoleUtils.RESET);
            System.out.print(ConsoleUtils.YELLOW + "Opção: " + ConsoleUtils.RESET);
            int opcaoFilme = scanner.nextInt();

            // Encaminha cada escolha para a sua sala
            switch (opcaoFilme) {
                case 1:
                    pedraFilosofal(heroi, todosPermitidos);
                    break;
                case 2:
                    System.out.println("\nUPCOMING IN THE NEXT RELEASE");
                    break;
                case 3:
                    System.out.println("\nUPCOMING IN THE NEXT RELEASE");
                    break;
                case 4:
                    System.out.println("\nUPCOMING IN THE NEXT RELEASE");
                    break;
                case 5:
                    System.out.println("\nUPCOMING IN THE NEXT RELEASE");
                    break;
                case 6:
                    System.out.println("\nUPCOMING IN THE NEXT RELEASE");
                    break;
                case 7:
                    System.out.println("\nUPCOMING IN THE NEXT RELEASE");
                    break;
                default:
                    System.out.println(ConsoleUtils.RED + "Opção inválida." + ConsoleUtils.RESET);
            }

            System.out.println(ConsoleUtils.GREEN + "\nFim de jogo" + ConsoleUtils.RESET);
            System.out.println(ConsoleUtils.YELLOW + "1. Começar com uma nova personagem" + ConsoleUtils.RESET);
            System.out.println(ConsoleUtils.RED + "2. Continuar com a mesma personagem" + ConsoleUtils.RESET);
            System.out.println(ConsoleUtils.RED + "3. Sair" + ConsoleUtils.RESET); // Funciona
            System.out.print(ConsoleUtils.YELLOW + "Opção: " + ConsoleUtils.RESET);
            int continuar = scanner.nextInt();

            if (continuar == 1) {

                List<Consumivel> listaVazia = new ArrayList<>();
                heroi.setOuro(0);
                heroi.setNivel(1);
                heroi.setForca(0);
                heroi.setVidaAtual(0);
                heroi.setInventario(listaVazia);
                labirintoHogwarts(criarPersonagem());
            }
            if (continuar == 2) {
                heroi.setOuro(20);
                heroi.setNivel(1);
                labirintoHogwarts(heroi);
            }
            if (continuar == 3) {
                System.out.println(ConsoleUtils.GREEN + "Obrigado por jogares!" + ConsoleUtils.RESET);
                System.exit(0);
            }
        }
    }

    private void exibirEstatisticasHeroi(Heroi heroi) {
        System.out.println(ConsoleUtils.PURPLE_BOLD + "\n**************************************" + ConsoleUtils.RESET);
        System.out.println(ConsoleUtils.PURPLE_BOLD + "*          Estatísticas do Herói      *" + ConsoleUtils.RESET);
        System.out.println(ConsoleUtils.PURPLE_BOLD + "**************************************" + ConsoleUtils.RESET);

        System.out.println(ConsoleUtils.GREEN_BOLD + "Nome: " + ConsoleUtils.RESET + heroi.getNome());
        System.out.println(ConsoleUtils.GREEN_BOLD + "Vida Atual: " + ConsoleUtils.RESET +
                ConsoleUtils.RED_BOLD + heroi.getVidaAtual() + "/" + heroi.getVidaMax() + ConsoleUtils.RESET);
        System.out.println(ConsoleUtils.GREEN_BOLD + "Força: " + ConsoleUtils.RESET + heroi.getForca());
        System.out.println(ConsoleUtils.GREEN_BOLD + "Ouro: " + ConsoleUtils.RESET + heroi.getOuro());

        System.out.println(ConsoleUtils.GREEN_BOLD + "Inventário: " + ConsoleUtils.RESET);
        if (heroi.getInventario().isEmpty()) {
            System.out.println(ConsoleUtils.RED + "  (Vazio)" + ConsoleUtils.RESET);
        } else {
            for (ItemHeroi item : heroi.getInventario()) {
                System.out.println("  - " + item.getNome());
            }
        }

        System.out.println(ConsoleUtils.PURPLE_BOLD + "**************************************" + ConsoleUtils.RESET);
    }


    /**
     * Método que representa a entrada na sala inicial do labirinto de Hogwarts, onde o herói é introduzido ao jogo
     * e tem a oportunidade de comprar itens de um vendedor antes de prosseguir para os desafios.
     *
     * @param heroi    O herói que está entrando no labirinto.
     * @param vendedor O vendedor presente na sala de entrada que oferece itens para o herói comprar.
     * @throws InterruptedException Se a execução do método for interrompida durante o uso do Thread.sleep.
     */
    public void salaEntradaHogwarts(Heroi heroi, Vendedor vendedor) throws InterruptedException {
        // Introdução ao jogo
        ConsoleUtils.typeWriter(ConsoleUtils.CYAN + "\nBem-vindo à escola de Hogwarts, " + heroi.getNome() + "!" +
                ConsoleUtils.RESET, 25);
        ConsoleUtils.typeWriter(ConsoleUtils.CYAN + "Tu foste escolhido para enfrentar os desafios do Labirinto de " +
                "Hogwarts." + ConsoleUtils.RESET, 25);
        ConsoleUtils.typeWriter(ConsoleUtils.CYAN + "O teu objetivo é ultrapassar todos os obstáculos e derrotar o " +
                "tenebroso Voldemort." + "\n" + ConsoleUtils.RESET, 25);
        Thread.sleep(1000);

        while (true) {
            // Imprime o ouro do herói atual
            System.out.println(ConsoleUtils.YELLOW_BRIGHT + heroi.getNome() + ", tens " + heroi.getOuro() + " moedas de " +
                    "ouro para gastar." + ConsoleUtils.RESET + "\n");

            List<Integer> indicesUsados = vendedor.mostrarItensLoja();
            System.out.print(ConsoleUtils.CYAN + "Escolhe um ítem (ou 0 para continuar): " + ConsoleUtils.RESET);
            int opcao = scanner.nextInt();

            if (opcao == 0) {
                break;
            }

            if (opcao > 0 && opcao <= indicesUsados.size()) {
                int indiceCorreto = indicesUsados.get(opcao - 1);
                vendedor.venderItem(heroi, indiceCorreto);
            } else {
                System.out.println(ConsoleUtils.RED + "Opção inválida, tente novamente." + ConsoleUtils.RESET);
            }
        }
    }

    /**
     * Método que inicia o jogo "Pedra Filosofal". Este método exibe mensagens iniciais e
     * então leva o herói para a sala da Floresta Proibida.
     *
     * @param heroi O herói que participará do jogo.
     * @param heroisPermitidos Lista de nomes de heróis permitidos na Floresta Proibida.
     * @throws InterruptedException Se a thread for interrompida durante a execução do método.
     */
    public void pedraFilosofal(Heroi heroi, List<String> heroisPermitidos) throws InterruptedException {
        System.out.println(ConsoleUtils.CYAN_BOLD + "\nEscolheste jogar: Pedra Filosofal" + ConsoleUtils.RESET);
        ConsoleUtils.typeWriter(ConsoleUtils.CYAN_BOLD + "Preparate para começar a tua aventura!" + ConsoleUtils.RESET, 25);
        salaFlorestaProibida(heroi, heroisPermitidos);
    }

    // Sala 1: A Floresta Proibida

    /**
     * Método que representa a sala da Floresta Proibida no jogo.
     * Esta sala oferece ao jogador duas opções: explorar a floresta ou seguir uma trilha de pegadas,
     * conduzindo a diferentes eventos de jogo.
     *
     * @param heroi O herói que vai jogar
     * @param heroisPermitidos Lista de nomes de heróis permitidos na Floresta Proibida
     * @throws InterruptedException Se a thread for interrompida durante a execução do método
     */
    public void salaFlorestaProibida(Heroi heroi, List<String> heroisPermitidos) throws InterruptedException {
        System.out.println(ConsoleUtils.GREEN_BOLD + "\n** Sala 1: A Floresta Proibida **\n" + ConsoleUtils.RESET);
        ConsoleUtils.typeWriter(ConsoleUtils.PURPLE + heroi.getNome() + ", junto com Hagrid e Neville, é levado" +
                " à Floresta Proibida como parte de uma punição por estar fora da cama após o toque de recolher.\n" + ConsoleUtils.RESET, 25);
        System.out.println(ConsoleUtils.YELLOW + "Escolhe um caminho:" + ConsoleUtils.RESET);
        System.out.println("1. Explorar a floresta.");
        System.out.println("2. Seguir uma trilha de pegadas.");
        System.out.print(ConsoleUtils.YELLOW + "Opção: " + ConsoleUtils.RESET);

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            ConsoleUtils.typeWriter(ConsoleUtils.CYAN + "\nEntraste mais fundo na floresta e encontraste um centauro."
                    + ConsoleUtils.RESET, 25);
            ConsoleUtils.typeWriter(ConsoleUtils.CYAN + "O centauro está disposto a ajudar-te, mas antes tens de " +
                    "adivinhar o número que ele está a pensar (entre 1 e 10)." + ConsoleUtils.RESET, 25);
            desafioAdivinhaNumero(heroi, heroisPermitidos);
        } else if (escolha == 2) {
            ConsoleUtils.typeWriter(ConsoleUtils.CYAN + "\nSeguiste as pegadas e encontraste Voldemort a " +
                    "beber sangue de unicórnio!\n" + ConsoleUtils.RESET, 25);
            System.out.println(ConsoleUtils.RED + "Voldemort ataca-te!" + ConsoleUtils.RESET);
            NPC voldemort = new NPC("Voldemort", 100, 20, 15);
            boolean vitoria = heroi.atacar(voldemort);

            if (vitoria) {
                ConsoleUtils.typeWriter("\n" + ConsoleUtils.YELLOW + heroi.getNome() + " abre o teu inventário para " +
                        "ver se tens alguma poção para te curar!" + ConsoleUtils.RESET, 25);
                heroi.usarPocao();
            }
        } else {
            System.out.println(ConsoleUtils.RED + "Escolha inválida." + ConsoleUtils.RESET);
            salaFlorestaProibida(heroi, heroisPermitidos);
            return;
        }

        exibirEstatisticasHeroi(heroi);
        Thread.sleep(1000);
        // Avança para a próxima sala após o desafio
        salaXadrezGigante(heroi);
    }

    /**
     * Método que desafia o herói a adivinhar um número entre 1 e 10.
     * Caso o herói acerte, ele recebe uma poção como recompensa.
     * Se errar, ele perde pontos de vida.
     *
     * @param heroi O herói que vai participar do desafio
     * @throws InterruptedException Se a thread for interrompida durante a execução do método
     */
    public void desafioAdivinhaNumero(Heroi heroi, List<String> heroisPermitidos) throws InterruptedException {
        Random random = new Random();
        int numeroCorreto = random.nextInt(10) + 1;
        int tentativas = 3;
        boolean acertou = false;

        while (tentativas > 0) {
            System.out.print(ConsoleUtils.YELLOW + "\nAdivinha o número (1 a 10): " + ConsoleUtils.RESET);
            int palpite = scanner.nextInt();

            if (palpite == numeroCorreto) {
                System.out.println(ConsoleUtils.GREEN + "Parabéns! Adivinhaste o número correto." + ConsoleUtils.RESET);
                Pocao pocao = new Pocao("Poção de Cura Menor", 0, heroisPermitidos, 10, 5);
                heroi.adicionarItemAoInventario(pocao);  // Adiciona uma poção ao inventário do herói como recompensa
                acertou = true;
                break;
            } else {
                tentativas--;
                if (tentativas > 0) {
                    System.out.println(ConsoleUtils.RED + "Palpite incorreto. Tenta novamente! Tens " + tentativas + " tentativas restantes." + ConsoleUtils.RESET);
                } else {
                    System.out.println(ConsoleUtils.RED + "Palpite incorreto. Ficaste sem tentativas!" + ConsoleUtils.RESET);
                }
            }
        }

        if (!acertou) {
            System.out.println(ConsoleUtils.RED + "\nO centauro desaparece nas sombras, e sentes a presença de algo sombrio a aproximar-se." + ConsoleUtils.RESET);
            heroi.setVidaAtual(heroi.getVidaAtual() - 10); // Se o herói não adivinhar o número, perde vida
            System.out.println(ConsoleUtils.RED + "Perdeste 10 pontos de vida!" + ConsoleUtils.RESET);
        }
    }

    // Sala 2: O Tabuleiro de Xadrez Gigante

    /**
     * Método que representa a sala do Xadrez Gigante no jogo.
     * O jogador tem a opção de participar em um jogo de xadrez
     * gigante ou procurar um atalho, o que pode resultar em uma
     * batalha menor.
     *
     * @param heroi O herói que vai jogar
     * @throws InterruptedException Se a thread for interrompida durante a execução do método
     */
    public void salaXadrezGigante(Heroi heroi) throws InterruptedException {
        System.out.println(ConsoleUtils.GREEN_BOLD + "\n** Sala 2: O Tabuleiro de Xadrez Gigante **" + ConsoleUtils.RESET);
        ConsoleUtils.typeWriter(ConsoleUtils.PURPLE + "\nQuando os três protagonistas descem para proteger a Pedra Filofal," +
                " eles enfrentam um gigantesco tabuleiro de xadrex mágico que exige que joguem uma partida para poderem avançar.\n" + ConsoleUtils.RESET, 25);
        System.out.println(ConsoleUtils.YELLOW + "Escolhe um caminho:" + ConsoleUtils.RESET);
        System.out.println("1. Participar no jogo de xadrez gigante.");
        System.out.println("2. Procurar um atalho.");
        System.out.print(ConsoleUtils.YELLOW + "Opção: " + ConsoleUtils.RESET);

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            ConsoleUtils.typeWriter(ConsoleUtils.CYAN + "\nEstás em um duelo de xadrez contra peças gigantes! O teu destino será decidido num duelo de Pedra, Papel, Tesoura!" + ConsoleUtils.RESET, 25);
            desafioPedraPapelTesoura(heroi);  // Inicia o desafio de Pedra, Papel, Tesoura
        } else if (escolha == 2) {
            ConsoleUtils.typeWriter(ConsoleUtils.CYAN + "\nProcuraste um atalho, mas encontraste peças guardiãs menores! Prepara-te para lutar!" + ConsoleUtils.RESET, 25);
            // Batalha contra peças menores
            NPC pecaMenor = new NPC("Peça Guardiã", 50, 10, 5);
            boolean vitoria = heroi.atacar(pecaMenor);

            if (vitoria) {
                ConsoleUtils.typeWriter("\n" + ConsoleUtils.YELLOW + heroi.getNome() + " abre o teu inventário para ver se tens alguma poção para te curar!" + ConsoleUtils.RESET, 25);
                heroi.usarPocao();
            }
        } else {
            System.out.println(ConsoleUtils.RED + "Escolha inválida." + ConsoleUtils.RESET);
            salaXadrezGigante(heroi);
            return;
        }

        exibirEstatisticasHeroi(heroi);
        Thread.sleep(1000);

        // Avança para a próxima sala após o desafio
        salaArmadilhaDiabo(heroi);
    }

    /**
     * Método que desafia o herói a um jogo de Pedra, Papel, Tesoura.
     * O herói tem três tentativas para vencer o adversário.
     * Caso vença, recebe uma poção de força como recompensa.
     * Se perder todas as tentativas, o herói perde pontos de vida.
     *
     * @param heroi O herói que vai participar do desafio
     * @throws InterruptedException Se a thread for interrompida durante a execução do método
     */
    public void desafioPedraPapelTesoura(Heroi heroi) throws InterruptedException {
        String[] opcoes = {"Pedra", "Papel", "Tesoura"};
        Random random = new Random();
        int tentativas = 3;
        boolean ganhou = false;

        System.out.println(ConsoleUtils.YELLOW + "\nDuelo de Pedra, Papel, Tesoura! Tens " + tentativas + " tentativas para vencer." + ConsoleUtils.RESET);

        while (tentativas > 0) {
            // Escolha do jogador
            System.out.print(ConsoleUtils.YELLOW + "\nEscolhe: 1. Pedra, 2. Papel, 3. Tesoura: " + ConsoleUtils.RESET);
            int escolhaJogador = scanner.nextInt() - 1;

            // Escolha do adversário
            int escolhaAdversario = random.nextInt(3);
            System.out.println(ConsoleUtils.CYAN + "O adversário escolheu: " + opcoes[escolhaAdversario] + ConsoleUtils.RESET);

            // Verificar o resultado
            if (escolhaJogador == escolhaAdversario) {
                System.out.println(ConsoleUtils.YELLOW + "Empate! Tenta novamente." + ConsoleUtils.RESET);
            } else if ((escolhaJogador == 0 && escolhaAdversario == 2) || // Pedra vence Tesoura
                    (escolhaJogador == 1 && escolhaAdversario == 0) || // Papel vence Pedra
                    (escolhaJogador == 2 && escolhaAdversario == 1)) { // Tesoura vence Papel
                System.out.println(ConsoleUtils.GREEN + "Parabéns! Venceste o duelo de xadrez!" + ConsoleUtils.RESET);
                Pocao pocao = new Pocao("Poção de Força", 0, Arrays.asList("Heroi"), 15, 5);
                heroi.adicionarItemAoInventario(pocao);  // Recompensa: Poção de Força
                ganhou = true;
                break;
            } else {
                tentativas--;
                System.out.println(ConsoleUtils.RED + "Perdeste este turno. Tentativas restantes: " + tentativas + ConsoleUtils.RESET);
            }
        }

        if (!ganhou) {
            System.out.println(ConsoleUtils.RED + "Foste derrotado pelas peças de xadrez. Perdeste 10 pontos de vida." + ConsoleUtils.RESET);
            heroi.setVidaAtual(heroi.getVidaAtual() - 10);  // Se perder, o herói perde vida
        }
    }

    // Sala 3: A Armadilha do Diabo

    /**
     * Método que representa a sala do Diabo Ceifador no jogo.
     * Nesta sala, o jogador tem a escolha de resolver um enigma sobre plantas mágicas ou tentar escapar lutando contra as vinhas da planta.
     *
     * @param heroi O herói que vai jogar
     * @throws InterruptedException Se a thread for interrompida durante a execução do método
     */
    public void salaArmadilhaDiabo(Heroi heroi) throws InterruptedException {
        System.out.println(ConsoleUtils.GREEN_BOLD + "\n** Sala 3: Armadilha do Diabo **" + ConsoleUtils.RESET);
        ConsoleUtils.typeWriter(ConsoleUtils.PURPLE + "\nO trio cai numa planta chamada 'Armadilha do Diabo', que se aperta" +
                " ao redor daqueles que tentam resistir. Hermione lembra-se que a planta odeia luz, o que lhes permite " +
                "escapar.\n" + ConsoleUtils.RESET,25);
        System.out.println(ConsoleUtils.YELLOW + "Escolhe um caminho:" + ConsoleUtils.RESET);
        System.out.println("1. Resolver o enigma sobre plantas mágicas.");
        System.out.println("2. Tentar escapar lutando contra as vinhas da planta.");
        System.out.print(ConsoleUtils.YELLOW + "Opção: " + ConsoleUtils.RESET);

        int escolha = scanner.nextInt();

        if (escolha == 1) {
            ConsoleUtils.typeWriter(ConsoleUtils.CYAN + "\nResponde corretamente para escapar ao Diabo Ceifador!" +
                    ConsoleUtils.RESET, 25);
            desafioQuizPlantas(heroi);  // Inicia o quiz sobre plantas mágicas
        } else if (escolha == 2) {
            ConsoleUtils.typeWriter(ConsoleUtils.CYAN + "\nVais lutar contra as vinhas, mas a planta começa a " +
                    "apertar-te! Prepara-te!" + ConsoleUtils.RESET, 25);
            // Batalha contra as vinhas
            NPC vinha = new NPC("Vinha do Diabo Ceifador", 60, 12, 8);
            boolean vitoria = heroi.atacar(vinha);

            if (vitoria) {
                ConsoleUtils.typeWriter("\n" + ConsoleUtils.YELLOW + heroi.getNome() + " abre o teu inventário para " +
                        "ver se tens alguma poção para te curar!" + ConsoleUtils.RESET, 25);
                heroi.usarPocao();
            }

        } else {
            System.out.println(ConsoleUtils.RED + "Escolha inválida." + ConsoleUtils.RESET);
            salaArmadilhaDiabo(heroi);
            return;
        }

        exibirEstatisticasHeroi(heroi);
        Thread.sleep(1000);

        // Avança para a próxima sala após o desafio
        salaChavesVoadoras(heroi);
    }

    /**
     * Método que apresenta um quiz sobre plantas mágicas ao herói.
     * O herói precisa selecionar a planta correta que emite luz para afastar o Diabo Ceifador.
     * Caso o herói escolha a resposta correta, ele prossegue no jogo.
     * Uma resposta incorreta resulta na perda de 10 pontos de vida, e o quiz se repete até que
     * a resposta correta seja escolhida ou até o herói perder toda a vida.
     *
     * @param heroi O herói que está participando do quiz
     * @throws InterruptedException Se a thread for interrompida durante a execução do método
     */
    public void desafioQuizPlantas(Heroi heroi) throws InterruptedException {
        System.out.println(ConsoleUtils.YELLOW + "\nQual destas plantas emite luz que afasta o Diabo Ceifador?" + ConsoleUtils.RESET);
        System.out.println("A) Mimbulus Mimbletonia");
        System.out.println("B) Lumosian Foliage");
        System.out.println("C) Aconite");
        System.out.println("D) Agradaluz");

        System.out.print(ConsoleUtils.YELLOW + "Resposta: " + ConsoleUtils.RESET);
        String resposta = scanner.next().toUpperCase();

        if (resposta.equals("D")) {
            System.out.println(ConsoleUtils.GREEN + "Correto! Escapaste da planta." + ConsoleUtils.RESET);
        } else {
            System.out.println(ConsoleUtils.RED + "Errado! O Diabo Ceifador aperta-te e perdes 10 pontos de vida." + ConsoleUtils.RESET);
            heroi.setVidaAtual(heroi.getVidaAtual() - 10);

            // Repetir até acertar ou morrer
            if (heroi.getVidaAtual() > 0) {
                desafioQuizPlantas(heroi);
            }
        }
    }

    // Sala 4: As Chaves Voadoras

    /**
     * Método que representa a sala das Chaves Voadoras no jogo. Nesta sala, o jogador
     * tem duas opções: tentar capturar a chave certa ou procurar uma chave mágica para
     * lutar contra as outras chaves voadoras.
     *
     * @param heroi O herói que vai jogar na sala das Chaves Voadoras
     * @throws InterruptedException Se a thread for interrompida durante a execução do método
     */
    public void salaChavesVoadoras(Heroi heroi) throws InterruptedException {
        System.out.println(ConsoleUtils.GREEN_BOLD + "\n** Sala 4: As Chaves Voadoras **" + ConsoleUtils.RESET);
        ConsoleUtils.typeWriter(ConsoleUtils.PURPLE + "\nPara continuar, os três precisam de capturar uma chave voadora" +
                " entre dezenas de outras, cada uma delas a voar de forma errática.\n" + ConsoleUtils.RESET,25);
        System.out.println(ConsoleUtils.YELLOW + "Escolhe um caminho:" + ConsoleUtils.RESET);
        System.out.println("1. Tentar capturar a chave certa.");
        System.out.println("2. Procurar uma chave mágica para lutar contra as outras.");
        System.out.print(ConsoleUtils.YELLOW + "Opção: " + ConsoleUtils.RESET);

        int escolha = scanner.nextInt();

        if (escolha == 1) {
            ConsoleUtils.typeWriter(ConsoleUtils.CYAN + "\nPrecisas de capturar a chave certa! Teste de reflexos!" + ConsoleUtils.RESET, 25);
            desafioReflexosRapidos(heroi);  // Inicia o desafio de reflexos
        } else if (escolha == 2) {
            ConsoleUtils.typeWriter(ConsoleUtils.CYAN + "\nProcuraste uma chave mágica, mas agora as chaves estão a atacar-te!" + ConsoleUtils.RESET, 25);
            // Batalha contra as chaves
            NPC chaveAgressiva = new NPC("Chave Voadora Agressiva", 40, 8, 6);
            boolean vitoria = heroi.atacar(chaveAgressiva);

            if (vitoria) {
                ConsoleUtils.typeWriter("\n" + ConsoleUtils.YELLOW + heroi.getNome() + " abre o teu inventário para ver se tens alguma poção para te curar!" + ConsoleUtils.RESET, 25);
                heroi.usarPocao();
            }
        } else {
            System.out.println(ConsoleUtils.RED + "Escolha inválida." + ConsoleUtils.RESET);
            salaChavesVoadoras(heroi);
            return;
        }

        exibirEstatisticasHeroi(heroi);
        Thread.sleep(1000);

        // Avança para a próxima sala após o desafio
        salaEspelhoOjesed(heroi);
    }

    /**
     * Método que desafia o herói a digitar um código aleatório de letras
     * dentro de um tempo limite. Se o herói digitar corretamente o código
     * no tempo especificado, ele é recompensado. Caso contrário, o herói
     * perde pontos de vida e o desafio é repetido até que ele acerte ou
     * perca toda a vida.
     *
     * @param heroi O herói que vai participar do desafio
     * @throws InterruptedException Se a thread for interrompida durante a execução do método
     */
    public void desafioReflexosRapidos(Heroi heroi) throws InterruptedException {
        String codigo = gerarCodigoAleatorio(5);  // Gera um código de 5 letras
        System.out.println(ConsoleUtils.YELLOW + "Introduz o código rapidamente: " + ConsoleUtils.RESET + ConsoleUtils.CYAN + codigo + ConsoleUtils.RESET);

        long startTime = System.currentTimeMillis();
        System.out.print("Código: ");
        String resposta = scanner.next().toUpperCase();
        long endTime = System.currentTimeMillis();

        if (resposta.equals(codigo) && (endTime - startTime) <= 3500) {
            System.out.println(ConsoleUtils.GREEN + "Correto! Apanhaste a chave certa!" + ConsoleUtils.RESET);
        } else {
            System.out.println(ConsoleUtils.RED + "Errado ou demoraste muito! As chaves atacam-te e perdes 15 pontos de vida." + ConsoleUtils.RESET);
            heroi.setVidaAtual(heroi.getVidaAtual() - 15);

            if (heroi.getVidaAtual() > 0) {
                desafioReflexosRapidos(heroi); // Repetir até acertar ou morrer
            }
        }
    }

    /**
     * Gera um código aleatório contendo letras maiúsculas do alfabeto de acordo com o tamanho especificado.
     *
     * @param tamanho O tamanho do código aleatório a ser gerado
     * @return Uma string contendo o código aleatório gerado
     */
    public String gerarCodigoAleatorio(int tamanho) {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder codigo = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            codigo.append(letras.charAt(random.nextInt(letras.length())));
        }
        return codigo.toString();
    }

    // Sala 5: O Espelho de Ojesed

    /**
     * Método que representa a sala do Espelho de Ojesed no jogo. O jogador tem a opção de enfrentar o enigma
     * de Voldemort ou atacá-lo diretamente. Dependendo da escolha, o herói pode ter que responder perguntas
     * ou enfrentar uma batalha.
     *
     * @param heroi O herói que vai jogar na sala do Espelho*/
    public void salaEspelhoOjesed(Heroi heroi) throws InterruptedException {
        System.out.println(ConsoleUtils.GREEN_BOLD + "\n** Sala 5: O Espelho de Ojesed **" + ConsoleUtils.RESET);
        ConsoleUtils.typeWriter(ConsoleUtils.PURPLE + "\nEncontra-se frente a frente com o Professor Quirrel, que" +
                " está possuído por Voldemort. O Espelho de Ojessed mostra-lhe onde está a Pedra Filosofal, e ele é capaz" +
                " de ganhar a pedra porque o seu desejo era puro.\n" + ConsoleUtils.RESET,25);
        System.out.println(ConsoleUtils.YELLOW + "Escolhe um caminho:" + ConsoleUtils.RESET);
        System.out.println("1. Enfrentar o enigma de Voldemort.");
        System.out.println("2. Atacar Voldemort diretamente.");
        System.out.print(ConsoleUtils.YELLOW + "Opção: " + ConsoleUtils.RESET);

        int escolha = scanner.nextInt();

        if (escolha == 1) {
            ConsoleUtils.typeWriter(ConsoleUtils.CYAN + "\nVoldemort faz-te perguntas sobre magia negra. Responda corretamente!" + ConsoleUtils.RESET, 25);
            desafioVerdadeiroFalso(heroi);  // Inicia o quiz de verdadeiro ou falso
        } else if (escolha == 2) {
            NPC voldemort = new NPC("Voldemort", 200, 30, 50);
            ConsoleUtils.typeWriter(ConsoleUtils.CYAN + "\nTentaste atacar Voldemort diretamente, mas ele contra-ataca! Sofres dano." + ConsoleUtils.RESET, 25);
            boolean vitoria = heroi.atacar(voldemort);

            if (vitoria) {
                ConsoleUtils.typeWriter("\n" + ConsoleUtils.YELLOW + heroi.getNome() + " abre o teu inventário para ver se tens alguma poção para te curar!" + ConsoleUtils.RESET, 25);
                heroi.usarPocao();
            }
            // Se sobreviver, continuar o enigma
            if (vitoria) {
                desafioVerdadeiroFalso(heroi);
            } else {
                System.out.println(ConsoleUtils.RED + "\nFoste derrotado por Voldemort." + ConsoleUtils.RESET);
                return;
            }
        } else {
            System.out.println(ConsoleUtils.RED + "Escolha inválida." + ConsoleUtils.RESET);
            salaEspelhoOjesed(heroi);
            return;
        }

        exibirEstatisticasHeroi(heroi);
        Thread.sleep(1000);

        // Avança para a sala final após o enigma
        salaFinalVoldemort(heroi);
    }

    /**
     * Método que desafia o herói a responder perguntas de verdadeiro ou falso.
     * O herói perde pontos de vida se errar as respostas.
     *
     * @param heroi O herói que vai participar do desafio
     * @throws InterruptedException Se a thread for interrompida durante a execução do método
     */
    public void desafioVerdadeiroFalso(Heroi heroi) throws InterruptedException {
        // Pergunta 1
        System.out.println("\nVerdadeiro ou falso: A maldição Cruciatus é usada para matar.");
        System.out.print("Resposta: ");
        String resposta1 = scanner.next().toUpperCase();
        if (resposta1.equals("FALSO")) {
            System.out.println(ConsoleUtils.GREEN + "Correto!" + ConsoleUtils.RESET);
        } else {
            System.out.println(ConsoleUtils.RED + "Errado! Perdes 10 pontos de vida." + ConsoleUtils.RESET);
            heroi.setVidaAtual(heroi.getVidaAtual() - 10);
        }

        // Pergunta 2
        System.out.println("\nVerdadeiro ou falso: Só os Sangue-Puros podem frequentar Hogwarts.");
        System.out.print("Resposta: ");
        String resposta2 = scanner.next().toUpperCase();
        if (resposta2.equals("FALSO")) {
            System.out.println(ConsoleUtils.GREEN + "Correto!" + ConsoleUtils.RESET);
        } else {
            System.out.println(ConsoleUtils.RED + "Errado! Perdes 10 pontos de vida." + ConsoleUtils.RESET);
            heroi.setVidaAtual(heroi.getVidaAtual() - 10);
        }

        // Pergunta 3
        System.out.println("\nVerdadeiro ou falso: O feitiço 'Expelliarmus' desarma o oponente.");
        System.out.print("Resposta: ");
        String resposta3 = scanner.next().toUpperCase();
        if (resposta3.equals("VERDADEIRO")) {
            System.out.println(ConsoleUtils.GREEN + "Correto!" + ConsoleUtils.RESET);
        } else {
            System.out.println(ConsoleUtils.RED + "Errado! Perdes 10 pontos de vida." + ConsoleUtils.RESET);
            heroi.setVidaAtual(heroi.getVidaAtual() - 10);
        }
    }

    // Sala Final: Confronto com Voldemort

    /**
     * Esta função representa a sala final onde o herói confronta Voldemort.
     * O herói tem três opções: entregar a Pedra Filosofal, tentar destruí-la,
     * ou protegê-la sem usá-la. Dependendo da escolha, o final do jogo varia.
     *
     * @param heroi O objeto do tipo Heroi que representa o jogador.
     * @throws InterruptedException Pode ser lançada durante a interrupção das ações do jogador.
     */
    public void salaFinalVoldemort(Heroi heroi) throws InterruptedException {
        System.out.println(ConsoleUtils.GREEN_BOLD + "\n** Sala Final: Confronto com Voldemort **" + ConsoleUtils.RESET);
        ConsoleUtils.typeWriter(ConsoleUtils.CYAN + "\nChegaste ao confronto final com Voldemort. Ele tenta convencer-te a entregar a Pedra Filosofal!\n" + ConsoleUtils.RESET, 25);

        System.out.println(ConsoleUtils.YELLOW_BOLD + "Escolhe uma ação:" + ConsoleUtils.RESET);
        System.out.println("1. Entregar a pedra a Voldemort.");
        System.out.println("2. Tentar destruir a pedra.");
        System.out.println("3. Proteger a pedra sem usá-la.");
        System.out.print(ConsoleUtils.YELLOW + "Opção: " + ConsoleUtils.RESET);

        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                ConsoleUtils.typeWriter(ConsoleUtils.RED + "Entregaste a pedra a Voldemort! Ele recupera o poder e o jogo termina." + ConsoleUtils.RESET, 25);
                heroi.setVidaAtual(0);  // Fim mau
                break;
            case 2:
                ConsoleUtils.typeWriter(ConsoleUtils.YELLOW + "Tentaste destruir a pedra! A pedra é destruída, mas perdes poder." + ConsoleUtils.RESET, 25);
                heroi.setVidaAtual(heroi.getVidaAtual() - 30);  // Fim neutro
                break;
            case 3:
                ConsoleUtils.typeWriter(ConsoleUtils.GREEN + "Protegeste a pedra sem usá-la! Voldemort foge, e tu triunfas!" + ConsoleUtils.RESET, 25);
                // Fim bom
                break;
            default:
                System.out.println(ConsoleUtils.RED + "Escolha inválida." + ConsoleUtils.RESET);
                salaFinalVoldemort(heroi);  // Repete a escolha
                return;
        }

        if (heroi.getVidaAtual() > 0) {
            System.out.println(ConsoleUtils.GREEN_BOLD + "\n** Parabéns! Concluíste a aventura com sucesso! **" + ConsoleUtils.RESET);
            ConsoleUtils.printFinalMessage();
        } else {
            System.out.println(ConsoleUtils.RED_BOLD + "\n** Infelizmente, foste derrotado por Voldemort. **" + ConsoleUtils.RESET);
        }
    }

}
