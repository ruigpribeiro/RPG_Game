package Hogwarts.jogo;

import Hogwarts.entidades.Herois.HarryPotter;
import Hogwarts.entidades.Herois.HermioneGranger;
import Hogwarts.entidades.Herois.Heroi;
import Hogwarts.entidades.Herois.RonWeasley;
import Hogwarts.entidades.NPC;
import Hogwarts.entidades.Vendedor;
import Hogwarts.itens.ArmaPrincipal;
import Hogwarts.itens.ConsumivelCombate;
import Hogwarts.itens.Pocao;
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
     * Cria uma personagem com as escolhas do utilizador
     *
     * @return Heroi
     */
    public Heroi criarPersonagem() {

        ConsoleUtils.printTitle();

        // Escolha de herói
        Scanner scanner = new Scanner(System.in);
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

        // Distribui os pontos de criação
        System.out.println(ConsoleUtils.YELLOW_BOLD + "\nTens " + pontosCriacao + " pontos de criação. Distribui-os entre vida e força." + ConsoleUtils.RESET);
        System.out.print(ConsoleUtils.GREEN + "Vida: " + ConsoleUtils.RESET);
        int vida = scanner.nextInt();
        System.out.print(ConsoleUtils.GREEN + "Força: " + ConsoleUtils.RESET);
        int forca = scanner.nextInt();

        // Verifica se os pontos foram totalmente distribuidos
        while (vida + forca != pontosCriacao) {
            System.out.println(ConsoleUtils.RED + "Pontos de distribuição incorretos!" + ConsoleUtils.RESET);
            System.out.print(ConsoleUtils.GREEN + "Vida: " + ConsoleUtils.RESET);
            vida = scanner.nextInt();
            System.out.print(ConsoleUtils.GREEN + "Força: " + ConsoleUtils.RESET);
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

        System.out.println("==========================================================================================================");
        return heroi;
    }


    /**
     * Lógica do jogo
     *
     * @param heroi O herói que vai jogar
     */
    public void labirintoHogwarts(Heroi heroi) throws InterruptedException {
        // Música de background
        Audio.playMusic("resources/hedwigs_theme.wav");

        Vendedor vendedor = new Vendedor();

        // Listas com os vários heróis
        List<String> todosPermitidos = Arrays.asList("HarryPotter", "HermioneGranger", "RonWeasley");
        List<String> apenasHarry = Arrays.asList("HarryPotter");
        List<String> apenasHermione = Arrays.asList("HermioneGranger");
        List<String> apenasRon = Arrays.asList("RonWeasley");

        // Adicionar armas à loja do vendedor
        vendedor.adicionarItem(new ArmaPrincipal("Espada de Gryffindor", 30, todosPermitidos, 20, 40));
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
        vendedor.adicionarItem(new ConsumivelCombate("Feitiço: Expelliarmus", 10, apenasHarry, 50));
        vendedor.adicionarItem(new ConsumivelCombate("Feitiço: Petrificus Totalus", 10, apenasHermione, 40));
        vendedor.adicionarItem(new ConsumivelCombate("Feitiço: Wingardium Leviosa", 10, apenasRon, 30));
        vendedor.adicionarItem(new ConsumivelCombate("Feitiço: Stupefy", 15, todosPermitidos, 25));
        vendedor.adicionarItem(new ConsumivelCombate("Feitiço: Reducto", 20, todosPermitidos, 35));
        vendedor.adicionarItem(new ConsumivelCombate("Feitiço: Incendio", 12, todosPermitidos, 20));
        vendedor.adicionarItem(new ConsumivelCombate("Feitiço: Confringo", 18, todosPermitidos, 30));

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
                    camaraSegredos(heroi);
                    break;
                default:
                    System.out.println(ConsoleUtils.RED + "Opção inválida." + ConsoleUtils.RESET);
            }

            System.out.println(ConsoleUtils.GREEN + "\nDesejas continuar a jogar?" + ConsoleUtils.RESET);
            System.out.println(ConsoleUtils.YELLOW + "1. Começar com uma nova personagem" + ConsoleUtils.RESET); // Não funciona
            System.out.println(ConsoleUtils.RED + "2. Continuar com a mesma personagem" + ConsoleUtils.RESET); // Não funciona
            System.out.println(ConsoleUtils.RED + "3. Sair" + ConsoleUtils.RESET); // Funciona
            System.out.print(ConsoleUtils.YELLOW + "Opção: " + ConsoleUtils.RESET);
            int continuar = scanner.nextInt();
            if (continuar == 1) {
                labirintoHogwarts(criarPersonagem());
            }
            if (continuar == 2) {
                heroi.setOuro(20);
                heroi.setNivel(1);
                labirintoHogwarts(heroi);
            }
            if (continuar == 3) {
                System.out.println(ConsoleUtils.GREEN + "Obrigado por jogar!" + ConsoleUtils.RESET);
                System.exit(0);
            }
        }
    }

    public void salaEntradaHogwarts(Heroi heroi, Vendedor vendedor) throws InterruptedException {
        // Introdução do jogo
        ConsoleUtils.typeWriter(ConsoleUtils.CYAN + "\nBem-vindo à escola de Hogwarts, " + heroi.getNome() + "!", 25);
        ConsoleUtils.typeWriter("Tu foste escolhido para enfrentar os desafios do Labirinto de Hogwarts.", 25);
        ConsoleUtils.typeWriter("O teu objetivo é ultrapassar todos os obstáculos e derrotar o tenebroso Voldemort." + "\n", 25);
        Thread.sleep(1000);

        while (true) {
            // Imprime o ouro do herói
            System.out.println(ConsoleUtils.CYAN + heroi.getNome() + ", tens " + heroi.getOuro() + " moedas de ouro." + ConsoleUtils.RESET + "\n");

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


    // PEDRA FILOSOFAL

    /**
     * Método para apenas imprimir a escolha do utilizador
     * @param heroi O Herói
     * @throws InterruptedException
     */
    private void pedraFilosofal(Heroi heroi,List<String> heroisPermitidos) throws InterruptedException {
        System.out.println(ConsoleUtils.CYAN_BOLD + "\nEscolheste: Pedra Filosofal" + ConsoleUtils.RESET);
        salaFlorestaProibida(heroi, heroisPermitidos);
    }

    /**
     * Sala onde recebe uma poção ou enfrenta o Voldemort
     * @param heroi O herói
     * @param heroisPermitidos Uma lista de heróis permitidos
     */
    private void salaFlorestaProibida(Heroi heroi, List<String> heroisPermitidos) {
        System.out.println(ConsoleUtils.GREEN + "\n** Sala 1: Floresta Proibida **" + ConsoleUtils.RESET);
        System.out.println(ConsoleUtils.YELLOW + "Escolhe um caminho:" + ConsoleUtils.RESET);
        System.out.println("1. Explorar a floresta.");
        System.out.println("2. Seguir uma trilha de pegadas.");
        System.out.print(ConsoleUtils.YELLOW + "Opção: " + ConsoleUtils.RESET);

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            System.out.println(ConsoleUtils.CYAN + "Encontraste um centauro que avisa-te sobre o perigo que corres." + ConsoleUtils.RESET);
            Pocao pocao = new Pocao("La Vida", 0, heroisPermitidos, 10, 5);
            heroi.adicionarItemAoInventario(pocao);
        } else if (escolha == 2) {
            System.out.println(ConsoleUtils.CYAN + "Encontraste o Voldemort a beber sangue de unicórnio!" + ConsoleUtils.RESET);
            NPC voldemort = new NPC("Voldemort", 100, 20, 15);
            heroi.atacar(voldemort);
        } else {
            System.out.println(ConsoleUtils.RED + "Escolha inválida." + ConsoleUtils.RESET);
            salaFlorestaProibida(heroi, heroisPermitidos);
        }

        salaXadrezGigante(heroi);
    }

    // Falta implementar
    private void salaXadrezGigante(Heroi heroi) {
        System.out.println(ConsoleUtils.GREEN_BOLD + "\n** Sala 2: Sala do Xadrez Gigante **" + ConsoleUtils.RESET);
        System.out.println(ConsoleUtils.YELLOW_BACKGROUND + "Escolhe um caminho:" + ConsoleUtils.RESET);
        System.out.println("1. Participar no jogo de xadrez gigante.");
        System.out.println("2. Procurar um atalho.");
        System.out.print(ConsoleUtils.YELLOW + "Opção: " + ConsoleUtils.RESET);

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            System.out.println(ConsoleUtils.CYAN + "Estás em um duelo de xadrez! Derrota o adversário." + ConsoleUtils.RESET);
            // Implementar lógica do duelo.
        } else if (escolha == 2) {
            System.out.println(ConsoleUtils.CYAN + "Encontraste peças guardiãs menores, prepare-te para lutar!" + ConsoleUtils.RESET);
            // Implementar batalha contra peças menores.
        } else {
            System.out.println(ConsoleUtils.RED + "Escolha inválida." + ConsoleUtils.RESET);
            salaXadrezGigante(heroi);
        }

        salaEnigmaPocoes(heroi);
    }


    private void salaEnigmaPocoes(Heroi heroi) {
        System.out.println(ConsoleUtils.GREEN_BOLD + "\n** Sala 3: Enigma das Poções **" + ConsoleUtils.RESET);
        System.out.println(ConsoleUtils.YELLOW_BACKGROUND + "Escolhe um caminho:" + ConsoleUtils.RESET);
        System.out.println("1. Resolver o enigma do Professor Snape.");
        System.out.println("2. Tentar adivinhar a poção correta.");
        System.out.print(ConsoleUtils.YELLOW + "Opção: " + ConsoleUtils.RESET);

        int escolha = scanner.nextInt();
        Random random = new Random();
        if (escolha == 1) {
            System.out.println(ConsoleUtils.CYAN + "Resolveste o enigma e podes avançar com segurança." + ConsoleUtils.RESET);
        } else if (escolha == 2) {
            int sorte = random.nextInt(2);
            if (sorte == 0) {
                System.out.println(ConsoleUtils.CYAN + "Escolheste a poção correta e podes avançar!" + ConsoleUtils.RESET);
            } else {
                System.out.println(ConsoleUtils.RED + "Escolha errada! Sofreste algum dano." + ConsoleUtils.RESET);
                heroi.setVidaAtual(heroi.getVidaAtual() - 10);
            }
        } else {
            System.out.println(ConsoleUtils.RED + "Escolha inválida." + ConsoleUtils.RESET);
            salaEnigmaPocoes(heroi);
        }

        salaFinalPedraFilosofal(heroi);
    }

    /**
     * Sala final onde enfrenta o Voldemort
     * @param heroi O herói
     */
    private void salaFinalPedraFilosofal(Heroi heroi) {
        System.out.println(ConsoleUtils.GREEN_BOLD + "\n** Sala Final: Confronto com Voldemort **" + ConsoleUtils.RESET);
        NPC voldemort = new NPC("Voldemort", 150, 30, 20);
        heroi.atacar(voldemort);

        if (heroi.getVidaAtual() > 0) {
            System.out.println(ConsoleUtils.CYAN + "Derrotaste o Voldemort e salvaste a Pedra Filosofal!" + ConsoleUtils.RESET);
        } else {
            System.out.println(ConsoleUtils.RED + "Voldemort venceu. Fim de jogo." + ConsoleUtils.RESET);
        }
    }

// CAMARA DOS SEGREDOS

    /**
     * Método para apenas imprimir a escolha do utilizador
     * @param heroi O herói
     * @throws InterruptedException
     */
    public void camaraSegredos(Heroi heroi) throws InterruptedException {
        System.out.println(ConsoleUtils.CYAN_BOLD + "\nEscolheste: Câmara dos Segredos" + ConsoleUtils.RESET);
        entradaCamaraSegredos(heroi);
    }

    /**
     * Sala onde vai direto para a câmara dos segredos ou recebe dano
     * @param heroi O herói
     * @throws InterruptedException
     */
    public void entradaCamaraSegredos(Heroi heroi) throws InterruptedException {
        System.out.println(ConsoleUtils.GREEN_BOLD + "\n** Sala 1: Entrada da Câmara dos Segredos **" + ConsoleUtils.RESET);
        System.out.println(ConsoleUtils.YELLOW_BACKGROUND + "Escolhe um caminho:" + ConsoleUtils.RESET);
        System.out.println("1. Explorar a casa de banho.");
        System.out.println("2. Seguir uma voz sibilante que vem dos corredores.");
        System.out.print(ConsoleUtils.YELLOW + "Opção: " + ConsoleUtils.RESET);

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            System.out.println(ConsoleUtils.CYAN + "Descobriste a entrada para a Câmara dos Segredos ao abrir a torneira certa." + ConsoleUtils.RESET);
            camaraSubterranea(heroi);
        } else if (escolha == 2) {
            System.out.println(ConsoleUtils.CYAN + "Seguiste a voz e enfrentaste perigos nos corredores escuros." + ConsoleUtils.RESET);
            Random random = new Random();
            int dano = random.nextInt(20) + 10;
            heroi.setVidaAtual(heroi.getVidaAtual() - dano);
            System.out.println(ConsoleUtils.RED + "Sofreste " + dano + " de dano ao seguir a voz do Basilisco!" + ConsoleUtils.RESET);
            if (heroi.getVidaAtual() > 0) {
                camaraSubterranea(heroi);
            } else {
                System.out.println(ConsoleUtils.RED + "Foste derrotado pela voz sibilante." + ConsoleUtils.RESET);
            }
        } else {
            System.out.println(ConsoleUtils.RED + "Escolha inválida." + ConsoleUtils.RESET);
        }
    }

    private void camaraSubterranea(Heroi heroi) throws InterruptedException {
        System.out.println(ConsoleUtils.GREEN_BOLD + "\n** Sala 2: Câmara Subterrânea **" + ConsoleUtils.RESET);
        System.out.println(ConsoleUtils.YELLOW_BACKGROUND + "Escolhe um caminho:" + ConsoleUtils.RESET);
        System.out.println("1. Navegar por uma caverna escura.");
        System.out.println("2. Enfrentar uma série de portas mágicas.");
        System.out.print(ConsoleUtils.YELLOW + "Opção: " + ConsoleUtils.RESET);

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            System.out.println(ConsoleUtils.CYAN + "Navegas pela caverna escura, mas deparas-te com várias armadilhas." + ConsoleUtils.RESET);
            Random random = new Random();
            int dano = random.nextInt(10) + 5; // Dano entre 5 e 15
            heroi.setVidaAtual(heroi.getVidaAtual() - dano);
            System.out.println(ConsoleUtils.RED + "Sofreste " + dano + " de dano ao evitar os obstáculos." + ConsoleUtils.RESET);
        } else if (escolha == 2) {
            System.out.println(ConsoleUtils.CYAN + "Enfrentaste uma série de portas, cada uma com um enimga mágico." + ConsoleUtils.RESET);
            Random random = new Random();
            boolean sucesso = random.nextBoolean();
            if (sucesso) {
                System.out.println(ConsoleUtils.CYAN + "Resolveste todos os enigmas e avançaste!" + ConsoleUtils.RESET);
            } else {
                int dano = random.nextInt(10) + 5; // Dano entre 5 e 15
                heroi.setVidaAtual(heroi.getVidaAtual() - dano);
                System.out.println(ConsoleUtils.RED + "Falhaste no enigma e sofreste " + dano + " de dano." + ConsoleUtils.RESET);
            }
        } else {
            System.out.println(ConsoleUtils.RED + "Escolha inválida." + ConsoleUtils.RESET);
            camaraSubterranea(heroi);
            return;
        }

        encontroTomRiddle(heroi);
    }

    private void encontroTomRiddle(Heroi heroi) throws InterruptedException {
        System.out.println(ConsoleUtils.GREEN_BOLD + "\n** Sala 3: Encontro com Tom Riddle **" + ConsoleUtils.RESET);
        System.out.println(ConsoleUtils.YELLOW_BACKGROUND + "Escolhe um caminho:" + ConsoleUtils.RESET);
        System.out.println("1. Dialogar com Tom Riddle e tentar ganhar tempo.");
        System.out.println("2. Lutar contra criaturas invocadas por Tom Riddle.");
        System.out.print(ConsoleUtils.YELLOW + "Opção: " + ConsoleUtils.RESET);

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            System.out.println(ConsoleUtils.CYAN + "Tentaste ganhar tempo ao dialogar com Tom Riddle, mas ele observa-te com cautela." + ConsoleUtils.RESET);
            // Pode-se adicionar um efeito ou interação adicional, como reduzir a força de Tom Riddle na batalha final.
        } else if (escolha == 2) {
            System.out.println(ConsoleUtils.CYAN + "Tom Riddle invoca criaturas para lutar contra ti!" + ConsoleUtils.RESET);
            NPC criatura = new NPC("Criatura Invocada", 40, 15, 10);
            heroi.atacar(criatura);
            if (heroi.getVidaAtual() <= 0) {
                System.out.println(ConsoleUtils.RED + "Foste derrotado pelas criaturas invocadas." + ConsoleUtils.RESET);
                return;
            }
        } else {
            System.out.println(ConsoleUtils.RED + "Escolha inválida." + ConsoleUtils.RESET);
            encontroTomRiddle(heroi);
            return;
        }

        batalhaFinalCamaraSegredos(heroi);
    }

    private void batalhaFinalCamaraSegredos(Heroi heroi) throws InterruptedException {
        System.out.println(ConsoleUtils.GREEN_BOLD + "\n** Sala Final: Batalha contra o Basilisco e Tom Riddle **" + ConsoleUtils.RESET);
        NPC basilisco = new NPC("Basilisco", 120, 30, 25);
        NPC tomRiddle = new NPC("Tom Riddle", 100, 25, 20);

        System.out.println(ConsoleUtils.CYAN + "Enfrentaste o poderoso Basilisco e a figura de Tom Riddle!" + ConsoleUtils.RESET);

        heroi.atacar(basilisco);
        if (heroi.getVidaAtual() > 0) {
            heroi.atacar(tomRiddle);
        }

        if (heroi.getVidaAtual() > 0) {
            System.out.println(ConsoleUtils.CYAN + "Conseguiste derrotar o Basilisco e destruíste o diário de Tom Riddle!" + ConsoleUtils.RESET);
        } else {
            System.out.println(ConsoleUtils.RED + "Foste derrotado na Câmara dos Segredos. Fim de jogo." + ConsoleUtils.RESET);
        }
    }
}
