package Hogwarts.Jogo;

import Hogwarts.Entidades.Herois.HarryPotter;
import Hogwarts.Entidades.Herois.HermioneGranger;
import Hogwarts.Entidades.Herois.Heroi;
import Hogwarts.Entidades.Herois.RonWeasley;
import Hogwarts.Entidades.NPC;
import Hogwarts.Entidades.Vendedor;
import Hogwarts.Itens.ArmaPrincipal;
import Hogwarts.Itens.Consumivel;
import Hogwarts.Itens.ConsumivelCombate;
import Hogwarts.Itens.Pocao;
import Hogwarts.Utils.ConsoleUtils;

import java.io.Console;
import java.util.*;

import java.util.Scanner;

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
    private static final String BOLD = "\u001B[1m";

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
        ConsoleUtils.typeWriter(YELLOW + "*** Escolhe o teu herói ***", 0);
        ConsoleUtils.typeWriter(BLUE + "1. Harry Potter", 0);
        ConsoleUtils.typeWriter(BLUE + "2. Hermione Granger", 0);
        ConsoleUtils.typeWriter(BLUE + "3. Ron Weasley",0);
        System.out.print(YELLOW + "Opção: ");
        int opcaoHeroi = scanner.nextInt();

        // Escolha de dificuldade
        ConsoleUtils.typeWriter(YELLOW + "\n*** Escolhe a dificuldade ***", 0);
        ConsoleUtils.typeWriter(GREEN + "1. Fácil",0);
        ConsoleUtils.typeWriter(RED + "2. Difícil", 0);
        System.out.print(YELLOW + "Opção: ");
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
        System.out.println(YELLOW + "\nTens " + pontosCriacao + " pontos de criação. Distribui-os entre vida e força." + BOLD);
        System.out.print(GREEN + "Vida: ");
        int vida = scanner.nextInt();
        System.out.print(GREEN + "Força: ");
        int forca = scanner.nextInt();

        // Verifica se os pontos foram totalmente distribuidos
        while (vida + forca != pontosCriacao) {
            System.out.println(RED + "Pontos de distribuição incorretos!");
            System.out.print(GREEN + "Vida: ");
            vida = scanner.nextInt();
            System.out.print(GREEN + "Força: ");
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

            System.out.println(YELLOW + "\nEscolhe um labirinto para começar a tua aventura:" + RESET);
            System.out.println(BLUE + "1. Pedra Filosofal" + RESET);
            System.out.println(BLUE + "2. Câmara dos Segredos - UPCOMING" + RESET);
            System.out.println(BLUE + "3. Prisioneiro de Azkaban - UPCOMING" + RESET);
            System.out.println(BLUE + "4. Cálice de Fogo - UPCOMING" + RESET);
            System.out.println(BLUE + "5. Ordem da Fénix - UPCOMING" + RESET);
            System.out.println(BLUE + "6. Príncipe Misterioso - UPCOMING" + RESET);
            System.out.println(BLUE + "7. Talismãs da Morte - UPCOMING" + RESET);
            System.out.print(YELLOW + "Opção: " + RESET);
            int opcaoFilme = scanner.nextInt();

            // Encaminha cada escolha para a sua sala
            switch (opcaoFilme) {
                case 1:
                    pedraFilosofal(heroi, todosPermitidos);
                    break;
                case 2:
                    camaraSegredos(heroi);
                    break;
                case 3:
                    prisioneiroAzkaban(heroi);
                    break;
                case 4:
                    caliceFogo(heroi);
                    break;
                case 5:
                    ordemFenix(heroi);
                    break;
                case 6:
                    principeMisterioso(heroi);
                    break;
                case 7:
                    talismasMorte(heroi, todosPermitidos);
                    break;
                default:
                    System.out.println(RED + "Opção inválida." + RESET);
            }


            System.out.println(GREEN + "\nDesejas continuar a jogar?" + RESET);
            System.out.println(YELLOW + "1. Começar com uma nova personagem" + RESET); // Não funciona
            System.out.println(RED + "2. Continuar com a mesma personagem" + RESET); // Não funciona
            System.out.println(RED + "3. Sair" + RESET); // Funciona
            System.out.print(YELLOW + "Opção: " + RESET);
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
                System.out.println(GREEN + "Obrigado por jogar!" + RESET);
                System.exit(0);
            }
        }
    }

    /**
     * Sala inicial em que aparece o vendedor
     * @param heroi O Herói
     * @param vendedor O Vendedor
     * @throws InterruptedException
     */
    public void salaEntradaHogwarts(Heroi heroi, Vendedor vendedor) throws InterruptedException {
        // Introdução do jogo
        ConsoleUtils.typeWriter(CYAN + "\nBem-vindo à escola de Hogwarts, " + heroi.getNome() + "!", 25);
        ConsoleUtils.typeWriter("Tu foste escolhido para enfrentar os desafios do Labirinto de Hogwarts.", 25);
        ConsoleUtils.typeWriter("O teu objetivo é ultrapassar todos os obstáculos e derrotar o tenebroso Voldemort." + "\n", 25);
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

    // PEDRA FILOSOFAL

    /**
     * Método para apenas imprimir a escolha do utilizador
     * @param heroi O Herói
     * @throws InterruptedException
     */
    private void pedraFilosofal(Heroi heroi,List<String> heroisPermitidos) throws InterruptedException {
        System.out.println(CYAN + "\nEscolheste: Pedra Filosofal" + RESET);
        salaFlorestaProibida(heroi, heroisPermitidos);
    }

    /**
     * Sala onde recebe uma poção ou enfrenta o Voldemort
     * @param heroi O herói
     * @param heroisPermitidos Uma lista de heróis permitidos
     */
    private void salaFlorestaProibida(Heroi heroi, List<String> heroisPermitidos) {
        System.out.println(GREEN + "\n** Sala 1: Floresta Proibida **" + RESET);
        System.out.println(YELLOW + "Escolhe um caminho:" + RESET);
        System.out.println("1. Explorar a floresta.");
        System.out.println("2. Seguir uma trilha de pegadas.");
        System.out.print(YELLOW + "Opção: " + RESET);

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            System.out.println(CYAN + "Encontraste um centauro que avisa-te sobre o perigo que corres." + RESET);
            Pocao pocao = new Pocao("La Vida", 0, heroisPermitidos, 10, 5);
            heroi.adicionarItemAoInventario(pocao);
        } else if (escolha == 2) {
            System.out.println(CYAN + "Encontraste o Voldemort a beber sangue de unicórnio!" + RESET);
            NPC voldemort = new NPC("Voldemort", 100, 20, 15);
            heroi.atacar(voldemort);
        } else {
            System.out.println(RED + "Escolha inválida." + RESET);
            salaFlorestaProibida(heroi, heroisPermitidos);
        }

        salaXadrezGigante(heroi);
    }

    // Falta implementar
    private void salaXadrezGigante(Heroi heroi) {
        System.out.println(GREEN + "\n** Sala 2: Sala do Xadrez Gigante **" + RESET);
        System.out.println(YELLOW + "Escolhe um caminho:" + RESET);
        System.out.println("1. Participar no jogo de xadrez gigante.");
        System.out.println("2. Procurar um atalho.");
        System.out.print(YELLOW + "Opção: " + RESET);

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            System.out.println(CYAN + "Estás em um duelo de xadrez! Derrota o adversário." + RESET);
            // Implementar lógica do duelo.
        } else if (escolha == 2) {
            System.out.println(CYAN + "Encontraste peças guardiãs menores, prepare-te para lutar!" + RESET);
            // Implementar batalha contra peças menores.
        } else {
            System.out.println(RED + "Escolha inválida." + RESET);
            salaXadrezGigante(heroi);
        }

        salaEnigmaPocoes(heroi);
    }


    private void salaEnigmaPocoes(Heroi heroi) {
        System.out.println(GREEN + "\n** Sala 3: Enigma das Poções **" + RESET);
        System.out.println(YELLOW + "Escolhe um caminho:" + RESET);
        System.out.println("1. Resolver o enigma do Professor Snape.");
        System.out.println("2. Tentar adivinhar a poção correta.");
        System.out.print(YELLOW + "Opção: " + RESET);

        int escolha = scanner.nextInt();
        Random random = new Random();
        if (escolha == 1) {
            System.out.println(CYAN + "Resolveste o enigma e podes avançar com segurança." + RESET);
        } else if (escolha == 2) {
            int sorte = random.nextInt(2);
            if (sorte == 0) {
                System.out.println(CYAN + "Escolheste a poção correta e podes avançar!" + RESET);
            } else {
                System.out.println(RED + "Escolha errada! Sofreste algum dano." + RESET);
                heroi.setVidaAtual(heroi.getVidaAtual() - 10);
            }
        } else {
            System.out.println(RED + "Escolha inválida." + RESET);
            salaEnigmaPocoes(heroi);
        }

        salaFinalPedraFilosofal(heroi);
    }

    /**
     * Sala final onde enfrenta o Voldemort
     * @param heroi O herói
     */
    private void salaFinalPedraFilosofal(Heroi heroi) {
        System.out.println(GREEN + "\n** Sala Final: Confronto com Voldemort **" + RESET);
        NPC voldemort = new NPC("Voldemort", 150, 30, 20);
        heroi.atacar(voldemort);

        if (heroi.getVidaAtual() > 0) {
            System.out.println(CYAN + "Derrotaste o Voldemort e salvaste a Pedra Filosofal!" + RESET);
        } else {
            System.out.println(RED + "Voldemort venceu. Fim de jogo." + RESET);
        }
    }

    // CAMARA DOS SEGREDOS

    /**
     * Método para apenas imprimir a escolha do utilizador
     * @param heroi O herói
     * @throws InterruptedException
     */
    public void camaraSegredos(Heroi heroi) throws InterruptedException {
        System.out.println(CYAN + "\nEscolheste: Câmara dos Segredos" + RESET);
        entradaCamaraSegredos(heroi);
    }

    /**
     * Sala onde vai direto para a câmara dos segredos ou recebe dano
     * @param heroi O herói
     * @throws InterruptedException
     */
    public void entradaCamaraSegredos(Heroi heroi) throws InterruptedException {
        System.out.println(GREEN + "\n** Sala 1: Entrada da Câmara dos Segredos **" + RESET);
        System.out.println(YELLOW + "Escolhe um caminho:" + RESET);
        System.out.println("1. Explorar a casa de banho.");
        System.out.println("2. Seguir uma voz sibilante que vem dos corredores.");
        System.out.print(YELLOW + "Opção: " + RESET);

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            System.out.println(CYAN + "Descobriste a entrada para a Câmara dos Segredos ao abrir a torneira certa." + RESET);
            camaraSubterranea(heroi);
        } else if (escolha == 2) {
            System.out.println(CYAN + "Seguiste a voz e enfrentaste perigos nos corredores escuros." + RESET);
            Random random = new Random();
            int dano = random.nextInt(20) + 10;
            heroi.setVidaAtual(heroi.getVidaAtual() - dano);
            System.out.println(RED + "Sofreste " + dano + " de dano ao seguir a voz do Basilisco!" + RESET);
            if (heroi.getVidaAtual() > 0) {
                camaraSubterranea(heroi);
            } else {
                System.out.println(RED + "Foste derrotado pela voz sibilante." + RESET);
            }
        } else {
            System.out.println(RED + "Escolha inválida." + RESET);
        }
    }

    private void camaraSubterranea(Heroi heroi) throws InterruptedException {
        System.out.println(GREEN + "\n** Sala 2: Câmara Subterrânea **" + RESET);
        System.out.println(YELLOW + "Escolhe um caminho:" + RESET);
        System.out.println("1. Navegar por uma caverna escura.");
        System.out.println("2. Enfrentar uma série de portas mágicas.");
        System.out.print(YELLOW + "Opção: " + RESET);

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            System.out.println(CYAN + "Navegas pela caverna escura, mas deparas-te com várias armadilhas." + RESET);
            Random random = new Random();
            int dano = random.nextInt(10) + 5; // Dano entre 5 e 15
            heroi.setVidaAtual(heroi.getVidaAtual() - dano);
            System.out.println(RED + "Sofreste " + dano + " de dano ao evitar os obstáculos." + RESET);
        } else if (escolha == 2) {
            System.out.println(CYAN + "Enfrentaste uma série de portas, cada uma com um enimga mágico." + RESET);
            Random random = new Random();
            boolean sucesso = random.nextBoolean();
            if (sucesso) {
                System.out.println(CYAN + "Resolveste todos os enigmas e avançaste!" + RESET);
            } else {
                int dano = random.nextInt(10) + 5; // Dano entre 5 e 15
                heroi.setVidaAtual(heroi.getVidaAtual() - dano);
                System.out.println(RED + "Falhaste no enigma e sofreste " + dano + " de dano." + RESET);
            }
        } else {
            System.out.println(RED + "Escolha inválida." + RESET);
            camaraSubterranea(heroi);
            return;
        }

        encontroTomRiddle(heroi);
    }

    private void encontroTomRiddle(Heroi heroi) throws InterruptedException {
        System.out.println(GREEN + "\n** Sala 3: Encontro com Tom Riddle **" + RESET);
        System.out.println(YELLOW + "Escolhe um caminho:" + RESET);
        System.out.println("1. Dialogar com Tom Riddle e tentar ganhar tempo.");
        System.out.println("2. Lutar contra criaturas invocadas por Tom Riddle.");
        System.out.print(YELLOW + "Opção: " + RESET);

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            System.out.println(CYAN + "Tentaste ganhar tempo ao dialogar com Tom Riddle, mas ele observa-te com cautela." + RESET);
            // Pode-se adicionar um efeito ou interação adicional, como reduzir a força de Tom Riddle na batalha final.
        } else if (escolha == 2) {
            System.out.println(CYAN + "Tom Riddle invoca criaturas para lutar contra ti!" + RESET);
            NPC criatura = new NPC("Criatura Invocada", 40, 15, 10);
            heroi.atacar(criatura);
            if (heroi.getVidaAtual() <= 0) {
                System.out.println(RED + "Foste derrotado pelas criaturas invocadas." + RESET);
                return;
            }
        } else {
            System.out.println(RED + "Escolha inválida." + RESET);
            encontroTomRiddle(heroi);
            return;
        }

        batalhaFinalCamaraSegredos(heroi);
    }

    private void batalhaFinalCamaraSegredos(Heroi heroi) throws InterruptedException {
        System.out.println(GREEN + "\n** Sala Final: Batalha contra o Basilisco e Tom Riddle **" + RESET);
        NPC basilisco = new NPC("Basilisco", 120, 30, 25);
        NPC tomRiddle = new NPC("Tom Riddle", 100, 25, 20);

        System.out.println(CYAN + "Enfrentaste o poderoso Basilisco e a figura de Tom Riddle!" + RESET);

        heroi.atacar(basilisco);
        if (heroi.getVidaAtual() > 0) {
            heroi.atacar(tomRiddle);
        }

        if (heroi.getVidaAtual() > 0) {
            System.out.println(CYAN + "Conseguiste derrotar o Basilisco e destruíste o diário de Tom Riddle!" + RESET);
        } else {
            System.out.println(RED + "Foste derrotado na Câmara dos Segredos. Fim de jogo." + RESET);
        }
    }

    // PRISIONEIRO DE AZKABAN ---------------------------------------

    public void prisioneiroAzkaban(Heroi heroi) throws InterruptedException {
        System.out.println(CYAN + "\nEscolheste: O Prisioneiro de Azkaban" + RESET);
        salaEncontroSirius(heroi);
    }

    private void salaEncontroSirius(Heroi heroi) throws InterruptedException {
        System.out.println(GREEN + "\n** Sala 1: Encontro com Sirius Black **" + RESET);
        System.out.println(YELLOW + "Escolhe um caminho:" + RESET);
        System.out.println("1. Investigar o esconderijo de Sirius Black na Casa dos Gritos.");
        System.out.println("2. Seguir as pistas do Mapa do Maroto.");
        System.out.print(YELLOW + "Opção: " + RESET);

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            System.out.println(CYAN + "Entras na Casa dos Gritos e deparas-te com Sirius Black, que te revela a verdade sobre a sua inocência." + RESET);
            // Adicionar algum efeito ou interação.
        } else if (escolha == 2) {
            System.out.println(CYAN + "Seguiste as pistas no Mapa do Maroto e descobriste o paradeiro de Peter Pettigrew." + RESET);
            // Adicionar interação relacionada com Pettigrew.
        } else {
            System.out.println(RED + "Escolha inválida." + RESET);
            salaEncontroSirius(heroi);
            return;
        }

        salaPatrono(heroi);
    }

    private void salaPatrono(Heroi heroi) throws InterruptedException {
        System.out.println(GREEN + "\n** Sala 2: Floresta Proibida e o Patrono **" + RESET);
        System.out.println(YELLOW + "Escolhe um caminho:" + RESET);
        System.out.println("1. Enfrentar os Dementores usando o feitiço Patrono.");
        System.out.println("2. Procurar refúgio na cabana de Hagrid.");
        System.out.print(YELLOW + "Opção: " + RESET);

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            System.out.println(CYAN + "Tu conjuras um Patrono para afastar os Dementores." + RESET);
            // Implementar lógica do feitiço Patrono.
        } else if (escolha == 2) {
            System.out.println(CYAN + "Refugiaste-te na cabana de Hagrid, mas os Dementores estão a cercá-la." + RESET);
            // Implementar um confronto ou interação.
        } else {
            System.out.println(RED + "Escolha inválida." + RESET);
            salaPatrono(heroi);
            return;
        }

        salaViagemNoTempo(heroi);
    }

    private void salaViagemNoTempo(Heroi heroi) throws InterruptedException {
        System.out.println(GREEN + "\n** Sala 3: Viagem no Tempo **" + RESET);
        System.out.println(YELLOW + "Escolhe um caminho:" + RESET);
        System.out.println("1. Usar o Vira-Tempo para salvar Sirius e Bicuço.");
        System.out.println("2. Tentar enfrentar os desafios sem alterar o tempo.");
        System.out.print(YELLOW + "Opção: " + RESET);

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            System.out.println(CYAN + "Usaste o Vira-Tempo e conseguiste salvar Sirius Black e Bicuço." + RESET);
            // Implementar alguma interação ou bônus.
        } else if (escolha == 2) {
            System.out.println(CYAN + "Decidiste não usar o Vira-Tempo e enfrentas os desafios como eles são." + RESET);
            Random random = new Random();
            int dano = random.nextInt(15) + 5; // Dano entre 5 e 20
            heroi.setVidaAtual(heroi.getVidaAtual() - dano);
            System.out.println(RED + "Tu sofres " + dano + " de dano ao enfrentar os desafios." + RESET);
        } else {
            System.out.println(RED + "Escolha inválida." + RESET);
            salaViagemNoTempo(heroi);
            return;
        }

        salaFinalPrisioneiroAzkaban(heroi);
    }

    private void salaFinalPrisioneiroAzkaban(Heroi heroi) throws InterruptedException {
        System.out.println(GREEN + "\n** Sala Final: Confronto com os Dementores **" + RESET);
        System.out.println(CYAN + "Chegaste ao confronto final contra os Dementores que querem capturar Sirius Black." + RESET);
        NPC dementor = new NPC("Dementor", 80, 20, 15);

        System.out.println(CYAN + "Tu precisas conjurar um Patrono poderoso para afastá-los!" + RESET);
        heroi.atacar(dementor);

        if (heroi.getVidaAtual() > 0) {
            System.out.println(CYAN + "Conseguiste afastar os Dementores e salvar Sirius Black!" + RESET);
        } else {
            System.out.println(RED + "Os Dementores venceram. Fim de jogo." + RESET);
        }
    }

    // CALICE DE FOGO

    public void caliceFogo(Heroi heroi) throws InterruptedException {
        System.out.println(CYAN + "\nEscolheste: O Cálice de Fogo" + RESET);
        salaDesafioDragao(heroi);
    }

    private void salaDesafioDragao(Heroi heroi) throws InterruptedException {
        System.out.println(GREEN + "\n** Sala 1: O Desafio do Dragão **" + RESET);
        System.out.println(YELLOW + "Escolhe um caminho:" + RESET);
        System.out.println("1. Enfrentar o dragão e recuperar o ovo dourado.");
        System.out.println("2. Tentar desviar do dragão usando astúcia e velocidade.");
        System.out.print(YELLOW + "Opção: " + RESET);

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            System.out.println(CYAN + "Tu decides enfrentar o dragão diretamente! A luta é intensa, mas consegues recuperar o ovo dourado." + RESET);
            // Implementar lógica da luta com o dragão.
        } else if (escolha == 2) {
            System.out.println(CYAN + "Usaste a tua inteligência para desviar do dragão e recuperar o ovo sem entrar em combate direto." + RESET);
            // Implementar interação de evitar combate.
        } else {
            System.out.println(RED + "Escolha inválida." + RESET);
            salaDesafioDragao(heroi);
            return;
        }

        salaLagoNegro(heroi);
    }

    private void salaLagoNegro(Heroi heroi) throws InterruptedException {
        System.out.println(GREEN + "\n** Sala 2: O Desafio do Lago Negro **" + RESET);
        System.out.println(YELLOW + "Escolhe um caminho:" + RESET);
        System.out.println("1. Mergulhar nas águas escuras para resgatar os reféns.");
        System.out.println("2. Tentar encontrar uma forma alternativa de salvar os reféns sem entrar no lago.");
        System.out.print(YELLOW + "Opção: " + RESET);

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            System.out.println(CYAN + "Mergulhaste nas profundezas do Lago Negro e enfrentaste as criaturas subaquáticas para salvar os reféns." + RESET);
            // Implementar lógica do desafio subaquático.
        } else if (escolha == 2) {
            System.out.println(CYAN + "Tentaste encontrar uma alternativa, mas o tempo é curto. Acabas por ter que mergulhar de qualquer forma." + RESET);
            Random random = new Random();
            int dano = random.nextInt(10) + 5; // Dano entre 5 e 15
            heroi.setVidaAtual(heroi.getVidaAtual() - dano);
            System.out.println(RED + "Sofreste " + dano + " de dano ao enfrentar as criaturas do lago." + RESET);
        } else {
            System.out.println(RED + "Escolha inválida." + RESET);
            salaLagoNegro(heroi);
            return;
        }

        salaLabirinto(heroi);
    }

    private void salaLabirinto(Heroi heroi) throws InterruptedException {
        System.out.println(GREEN + "\n** Sala 3: O Labirinto **" + RESET);
        System.out.println(YELLOW + "Escolhe um caminho:" + RESET);
        System.out.println("1. Seguir em frente enfrentando as criaturas e armadilhas do labirinto.");
        System.out.println("2. Procurar uma rota alternativa através do labirinto, evitando confrontos diretos.");
        System.out.print(YELLOW + "Opção: " + RESET);

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            System.out.println(CYAN + "Tu decides seguir o caminho mais direto, enfrentando os perigos do labirinto." + RESET);
            // Implementar lógica de combate no labirinto.
        } else if (escolha == 2) {
            System.out.println(CYAN + "Tentaste evitar os confrontos, mas acabaste por encontrar um inimigo no final do percurso." + RESET);
            Random random = new Random();
            int dano = random.nextInt(15) + 5; // Dano entre 5 e 20
            heroi.setVidaAtual(heroi.getVidaAtual() - dano);
            System.out.println(RED + "Sofreste " + dano + " de dano ao tentar evitar os confrontos." + RESET);
        } else {
            System.out.println(RED + "Escolha inválida." + RESET);
            salaLabirinto(heroi);
            return;
        }

        salaFinalCaliceFogo(heroi);
    }

    private void salaFinalCaliceFogo(Heroi heroi) throws InterruptedException {
        System.out.println(GREEN + "\n** Sala Final: O Cemitério e o Confronto com Voldemort **" + RESET);
        System.out.println(CYAN + "Chegaste ao cemitério onde ocorre o renascimento de Voldemort. Agora precisas enfrentá-lo!" + RESET);
        NPC voldemort = new NPC("Voldemort", 200, 35, 25);

        System.out.println(CYAN + "Tu precisas usar todas as tuas habilidades para sobreviver e escapar com vida!" + RESET);
        heroi.atacar(voldemort);

        if (heroi.getVidaAtual() > 0) {
            System.out.println(CYAN + "Conseguiste sobreviver e escapar com vida, mas o perigo de Voldemort está apenas a começar." + RESET);
        } else {
            System.out.println(RED + "Voldemort venceu. Fim de jogo." + RESET);
        }
    }

    // ORDEM DE FENIX

    public void ordemFenix(Heroi heroi) throws InterruptedException {
        System.out.println(CYAN + "\nEscolheste: Ordem da Fênix" + RESET);
        salaMinisterioMagia(heroi);
    }

    private void salaMinisterioMagia(Heroi heroi) throws InterruptedException {
        System.out.println(GREEN + "\n** Sala 1: Ministério da Magia **" + RESET);
        System.out.println(YELLOW + "Escolhe um caminho:" + RESET);
        System.out.println("1. Invadir o Ministério da Magia para resgatar a profecia.");
        System.out.println("2. Procurar uma entrada secreta para evitar os aurores.");
        System.out.print(YELLOW + "Opção: " + RESET);

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            System.out.println(CYAN + "Tu e os teus amigos invadiram o Ministério da Magia e estão em busca da profecia." + RESET);
            // Implementar lógica de enfrentamento dos aurores.
        } else if (escolha == 2) {
            System.out.println(CYAN + "Decidiste procurar uma entrada secreta, mas o tempo é curto e foste descoberto." + RESET);
            Random random = new Random();
            int dano = random.nextInt(10) + 5; // Dano entre 5 e 15
            heroi.setVidaAtual(heroi.getVidaAtual() - dano);
            System.out.println(RED + "Sofreste " + dano + " de dano ao ser descoberto pelos aurores." + RESET);
        } else {
            System.out.println(RED + "Escolha inválida." + RESET);
            salaMinisterioMagia(heroi);
            return;
        }

        salaSalaDasProfecias(heroi);
    }

    private void salaSalaDasProfecias(Heroi heroi) throws InterruptedException {
        System.out.println(GREEN + "\n** Sala 2: Sala das Profecias **" + RESET);
        System.out.println(YELLOW + "Escolhe um caminho:" + RESET);
        System.out.println("1. Encontrar a profecia antes dos Comensais da Morte.");
        System.out.println("2. Tentar usar magia para destruir a sala e impedir que a profecia seja tomada.");
        System.out.print(YELLOW + "Opção: " + RESET);

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            System.out.println(CYAN + "Tu e os teus amigos encontraram a profecia, mas os Comensais da Morte chegaram!" + RESET);
            // Implementar lógica para recuperar a profecia.
        } else if (escolha == 2) {
            System.out.println(CYAN + "Tentaste destruir a sala, mas a magia é instável e causou um colapso." + RESET);
            Random random = new Random();
            int dano = random.nextInt(15) + 5; // Dano entre 5 e 20
            heroi.setVidaAtual(heroi.getVidaAtual() - dano);
            System.out.println(RED + "Sofreste " + dano + " de dano ao tentar destruir a sala." + RESET);
        } else {
            System.out.println(RED + "Escolha inválida." + RESET);
            salaSalaDasProfecias(heroi);
            return;
        }

        salaDepartamentoMistérios(heroi);
    }

    private void salaDepartamentoMistérios(Heroi heroi) throws InterruptedException {
        System.out.println(GREEN + "\n** Sala 3: Departamento de Mistérios **" + RESET);
        System.out.println(YELLOW + "Escolhe um caminho:" + RESET);
        System.out.println("1. Lutar contra os Comensais da Morte.");
        System.out.println("2. Tentar escapar com a profecia antes que te cerquem.");
        System.out.print(YELLOW + "Opção: " + RESET);

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            System.out.println(CYAN + "Tu e os teus amigos enfrentam os Comensais da Morte numa batalha intensa." + RESET);
            // Implementar lógica de combate.
        } else if (escolha == 2) {
            System.out.println(CYAN + "Tentaste escapar, mas foste cercado. Agora precisas lutar para sobreviver." + RESET);
            Random random = new Random();
            int dano = random.nextInt(15) + 10; // Dano entre 10 e 25
            heroi.setVidaAtual(heroi.getVidaAtual() - dano);
            System.out.println(RED + "Sofreste " + dano + " de dano ao tentar escapar." + RESET);
        } else {
            System.out.println(RED + "Escolha inválida." + RESET);
            salaDepartamentoMistérios(heroi);
            return;
        }

        salaFinalOrdemFenix(heroi);
    }

    private void salaFinalOrdemFenix(Heroi heroi) throws InterruptedException {
        System.out.println(GREEN + "\n** Sala Final: Confronto no Ministério da Magia **" + RESET);
        System.out.println(CYAN + "Os Comensais da Morte te cercaram e a Ordem da Fênix chega para ajudar!" + RESET);
        NPC belatriz = new NPC("Belatriz Lestrange", 180, 35, 25);
        NPC lucio = new NPC("Lúcio Malfoy", 170, 30, 20);

        System.out.println(CYAN + "Precisas lutar ao lado da Ordem da Fênix para derrotar os Comensais da Morte." + RESET);
        heroi.atacar(belatriz);
        heroi.atacar(lucio);

        if (heroi.getVidaAtual() > 0) {
            System.out.println(CYAN + "Derrotaste os Comensais da Morte, mas Sirius Black foi morto. Voldemort ainda está à solta." + RESET);
        } else {
            System.out.println(RED + "Foste derrotado pelos Comensais da Morte. Fim de jogo." + RESET);
        }
    }

    // PRINCIPE MISTERIOSO

    public void principeMisterioso(Heroi heroi) throws InterruptedException {
        System.out.println(CYAN + "\nEscolheste: O Príncipe Misterioso" + RESET);
        salaCavernaHorcrux(heroi);
    }

    private void salaCavernaHorcrux(Heroi heroi) throws InterruptedException {
        System.out.println(GREEN + "\n** Sala 1: A Caverna da Horcrux **" + RESET);
        System.out.println(YELLOW + "Escolhe um caminho:" + RESET);
        System.out.println("1. Navegar pelo lago negro em busca da Horcrux.");
        System.out.println("2. Investigar as paredes da caverna em busca de pistas.");
        System.out.print(YELLOW + "Opção: " + RESET);

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            System.out.println(CYAN + "Tu e Dumbledore navegam até o centro do lago, onde está a Horcrux." + RESET);
            System.out.println(CYAN + "Precisas proteger Dumbledore enquanto ele enfraquece após beber a poção." + RESET);
            // Implementar lógica de proteção e recuperação da Horcrux.
        } else if (escolha == 2) {
            System.out.println(CYAN + "Enquanto investigas, criaturas do Inferi começam a emergir das águas!" + RESET);
            Random random = new Random();
            int dano = random.nextInt(10) + 5; // Dano entre 5 e 15
            heroi.setVidaAtual(heroi.getVidaAtual() - dano);
            System.out.println(RED + "Sofreste " + dano + " de dano ao enfrentar os Inferi." + RESET);
        } else {
            System.out.println(RED + "Escolha inválida." + RESET);
            salaCavernaHorcrux(heroi);
            return;
        }

        salaTorreAstronomia(heroi);
    }

    private void salaTorreAstronomia(Heroi heroi) throws InterruptedException {
        System.out.println(GREEN + "\n** Sala 2: Torre de Astronomia **" + RESET);
        System.out.println(YELLOW + "Escolhe um caminho:" + RESET);
        System.out.println("1. Subir com Dumbledore até o topo da torre.");
        System.out.println("2. Investigar o andar inferior da torre em busca de sinais de invasores.");
        System.out.print(YELLOW + "Opção: " + RESET);

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            System.out.println(CYAN + "Chegaste ao topo da torre e vês Draco Malfoy prestes a agir." + RESET);
            System.out.println(CYAN + "Tens de decidir se interferes ou esperas por Dumbledore." + RESET);
            // Implementar lógica de decisão.
        } else if (escolha == 2) {
            System.out.println(CYAN + "Encontras os Comensais da Morte infiltrados, mas és pego desprevenido." + RESET);
            Random random = new Random();
            int dano = random.nextInt(15) + 10; // Dano entre 10 e 25
            heroi.setVidaAtual(heroi.getVidaAtual() - dano);
            System.out.println(RED + "Sofreste " + dano + " de dano ao ser surpreendido pelos Comensais da Morte." + RESET);
        } else {
            System.out.println(RED + "Escolha inválida." + RESET);
            salaTorreAstronomia(heroi);
            return;
        }

        salaInfernoFogo(heroi);
    }

    private void salaInfernoFogo(Heroi heroi) throws InterruptedException {
        System.out.println(GREEN + "\n** Sala 3: Inferno de Fogo **" + RESET);
        System.out.println(YELLOW + "Escolhe um caminho:" + RESET);
        System.out.println("1. Lutar contra os Comensais da Morte que invadiram Hogwarts.");
        System.out.println("2. Proteger os alunos e levá-los em segurança até o Salão Principal.");
        System.out.print(YELLOW + "Opção: " + RESET);

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            System.out.println(CYAN + "Tu e os professores lutam bravamente contra os Comensais da Morte." + RESET);
            // Implementar lógica de combate.
        } else if (escolha == 2) {
            System.out.println(CYAN + "Levas os alunos em segurança, mas os Comensais da Morte destroem partes da escola." + RESET);
            Random random = new Random();
            int dano = random.nextInt(10) + 5; // Dano entre 5 e 15
            heroi.setVidaAtual(heroi.getVidaAtual() - dano);
            System.out.println(RED + "Sofreste " + dano + " de dano ao proteger os alunos." + RESET);
        } else {
            System.out.println(RED + "Escolha inválida." + RESET);
            salaInfernoFogo(heroi);
            return;
        }

        salaFinalPrincipeMisterioso(heroi);
    }

    private void salaFinalPrincipeMisterioso(Heroi heroi) throws InterruptedException {
        System.out.println(GREEN + "\n** Sala Final: Batalha na Torre de Astronomia **" + RESET);
        System.out.println(CYAN + "Dumbledore está enfraquecido e Draco Malfoy está prestes a atacar." + RESET);
        NPC draco = new NPC("Draco Malfoy", 100, 25, 15);
        NPC snape = new NPC("Severo Snape", 150, 35, 25);

        System.out.println(CYAN + "Snape aparece e toma o lugar de Draco. Uma escolha difícil está por vir." + RESET);
        heroi.atacar(draco);

        if (heroi.getVidaAtual() > 0) {
            System.out.println(CYAN + "Vês Snape assassinar Dumbledore e foge com os Comensais da Morte. O luto por Dumbledore pesa sobre todos." + RESET);
        } else {
            System.out.println(RED + "Foste derrotado durante o confronto. Fim de jogo." + RESET);
        }
    }

    // TALISMAS DA MORTE

    public void talismasMorte(Heroi heroi, List<String> permitidos) throws InterruptedException {
        System.out.println(CYAN + "\nEscolheste: Os Talismãs da Morte" + RESET);
        salaGrutaHorcruxes(heroi, permitidos);
    }

    private void salaGrutaHorcruxes(Heroi heroi, List<String> permitidos) throws InterruptedException {
        System.out.println(GREEN + "\n** Sala 1: A Gruta das Horcruxes **" + RESET);
        System.out.println(YELLOW + "Escolhe um caminho:" + RESET);
        System.out.println("1. Investigar o escondido esconderijo das Horcruxes.");
        System.out.println("2. Enfrentar as criaturas guardiãs das Horcruxes.");
        System.out.print(YELLOW + "Opção: " + RESET);

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            System.out.println(CYAN + "Encontras pistas sobre a localização das Horcruxes restantes e ganhas um novo item." + RESET);
            heroi.adicionarItemAoInventario(new ConsumivelCombate("Mapa das Horcruxes", 0, permitidos, 30));
        } else if (escolha == 2) {
            System.out.println(CYAN + "Lutas contra criaturas mágicas que defendem as Horcruxes." + RESET);
            NPC criaturas = new NPC("Criaturas Guardiãs", 120, 30, 20);
            heroi.atacar(criaturas);
        } else {
            System.out.println(RED + "Escolha inválida." + RESET);
            salaGrutaHorcruxes(heroi, permitidos);
            return;
        }

        salaDesertoHocrux(heroi, permitidos);
    }

    private void salaDesertoHocrux(Heroi heroi, List<String> permitidos) throws InterruptedException {
        System.out.println(GREEN + "\n** Sala 2: Deserto das Horcruxes **" + RESET);
        System.out.println(YELLOW + "Escolhe um caminho:" + RESET);
        System.out.println("1. Explorar as ruínas antigas em busca da Horcrux.");
        System.out.println("2. Enfrentar uma emboscada dos Comensais da Morte.");
        System.out.print(YELLOW + "Opção: " + RESET);

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            System.out.println(CYAN + "Encontras uma Horcrux escondida nas ruínas e a pista para o próximo local." + RESET);
            heroi.adicionarItemAoInventario(new ConsumivelCombate("Horcrux", 0, permitidos, 50));
        } else if (escolha == 2) {
            System.out.println(CYAN + "Os Comensais da Morte te emboscam. Prepara-te para lutar!" + RESET);
            NPC comensais = new NPC("Comensais da Morte", 150, 35, 25);
            heroi.atacar(comensais);
        } else {
            System.out.println(RED + "Escolha inválida." + RESET);
            salaDesertoHocrux(heroi, permitidos);
            return;
        }

        salaFinalHogwarts(heroi);
    }

    private void salaFinalHogwarts(Heroi heroi) throws InterruptedException {
        System.out.println(GREEN + "\n** Sala Final: A Batalha de Hogwarts **" + RESET);
        System.out.println(YELLOW + "Escolhe um caminho:" + RESET);
        System.out.println("1. Combater os Comensais da Morte na Grande Sala.");
        System.out.println("2. Confrontar Voldemort diretamente.");
        System.out.print(YELLOW + "Opção: " + RESET);

        int escolha = scanner.nextInt();
        if (escolha == 1) {
            System.out.println(CYAN + "Tu e os aliados lutam bravamente contra os Comensais da Morte." + RESET);
            NPC comensais = new NPC("Comensais da Morte", 200, 40, 30);
            heroi.atacar(comensais);
        } else if (escolha == 2) {
            System.out.println(CYAN + "Encontras Voldemort para um confronto final." + RESET);
            NPC voldemort = new NPC("Lord Voldemort", 250, 50, 40);
            heroi.atacar(voldemort);

            if (heroi.getVidaAtual() > 0) {
                System.out.println(CYAN + "Parabéns! Derrotaste o Voldemort e salvaste o mundo." + RESET);
            } else {
                System.out.println(RED + "Foste derrotado por Voldemort. Fim de jogo." + RESET);
            }
        } else {
            System.out.println(RED + "Escolha inválida." + RESET);
            salaFinalHogwarts(heroi);
            return;
        }
    }
}
