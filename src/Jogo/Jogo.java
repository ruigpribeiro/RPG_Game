package Jogo;

import Entidades.*;
import Itens.ArmaPrincipal;
import Itens.ConsumivelCombate;
import Itens.Pocao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Jogo {
    private Scanner scanner;

    public Jogo() {
        scanner = new Scanner(System.in);
    }

    public Heroi criarPersonagem() {

        // Imprime um menu de opções de heróis
        System.out.println("Tipos de Herói:");
        System.out.println("1. Cavaleiro");
        System.out.println("2. Feiticeiro");
        System.out.println("3. Arqueiro");
        System.out.print("Escolha uma opção: ");
        int opcaoHeroi = scanner.nextInt();

        // Verifica se o utilizador deu um input errado
        while (opcaoHeroi < 1 || opcaoHeroi > 3) {
            System.out.print("Opção Inválida.\nEscolha novamente: ");
            opcaoHeroi = scanner.nextInt();
        }

        // Imprime um menu de opções de dificuldade
        System.out.println("\nTipo de Dificuldade: ");
        System.out.println("1. Fácil");
        System.out.println("2. Difícil");
        System.out.print("Escolha uma opção: ");
        int opcaoDificuldade = scanner.nextInt();

        // Verifica se o utilizador deu um input errado
        while (opcaoDificuldade < 1 || opcaoDificuldade > 2) {
            System.out.print("Opção Inválida.\nEscolha novamente: ");
            opcaoDificuldade = scanner.nextInt();
        }

        int vida = 0;
        int forca = 0;
        int ouro = 0;
        int pontos = 0;

        // Atribui os pontos baseado na dificuldade
        if (opcaoDificuldade == 1) {
            pontos = 300;
            ouro = 20;
        } else {
            pontos = 225;
            ouro = 15;
        }

        while (pontos > 0) {
            System.out.print("\nPontos de Criação Disponíveis: " + pontos + "\n\n");

            // Distribuir pontos Vida
            System.out.print("Pontos a distribuir para vida: ");
            int pontosVida = scanner.nextInt();

            // Verifica se tem pontos disponiveis
            while (pontosVida > pontos) {
                System.out.print("Pontos insuficientes. Por favor, introduza novamente: ");
                pontosVida = scanner.nextInt();
            }

            vida += pontosVida;
            pontos -= pontosVida;

            // Distribuir pontos Força
            System.out.print("Pontos a distribuir para força: ");
            int pontosForca = scanner.nextInt();

            // Verifica se tem pontos disponiveis
            while ((pontosForca / 5 ) > pontos) {
                System.out.print("Pontos insuficientes. Por favor, introduza novamente: ");
                pontosForca = scanner.nextInt();
            }

            forca += pontosForca;
            pontos -= (pontosForca / 5);

        }

        // Cria os objetos personalizados
        Heroi heroi = null;
        switch (opcaoHeroi) {
            case 1:
                heroi = new Cavaleiro("Cavaleiro", vida, forca, ouro);
                break;
            case 2:
                heroi = new Feiticeiro("Feiticeiro", vida, forca, ouro);
                break;
            case 3:
                heroi = new Arqueiro("Arqueiro", vida, forca, ouro);
                break;
        }

        return heroi;
    }

    public void combateMortal(Heroi heroi) throws InterruptedException {
        // Instancia de Vendedor
        Vendedor vendedor = new Vendedor();

        // Lista de heróis permitidos
        List<String> todosHerois = Arrays.asList("Cavaleiro", "Feiticeiro", "Arqueiro");
        List<String> apenasCavaleiro = List.of("Cavaleiro");
        List<String> apenasFeiticeiro = List.of("Feiticeiro");
        List<String> apenasArqueiro = List.of("Arqueiro");

        // Cria itens e adiciona ao vendedor

        // Poções
        vendedor.adicionarItem(new Pocao("Poção de Vida", 10, todosHerois, 25, 0));
        vendedor.adicionarItem(new Pocao("Poção de Força", 15, todosHerois, 0, 10));
        vendedor.adicionarItem(new Pocao("Poção de Vida Grande", 25, todosHerois, 50, 0));
        vendedor.adicionarItem(new Pocao("Poção de Poder", 20, todosHerois, 0, 15));
        vendedor.adicionarItem(new Pocao("Poção de Vida Pequena", 5, todosHerois, 10, 0));
        vendedor.adicionarItem(new Pocao("Poção de Agilidade", 15, todosHerois, 0, 5));

        // Armas Principais
        vendedor.adicionarItem(new ArmaPrincipal("Espada Longa", 20, apenasCavaleiro, 10, 5));
        vendedor.adicionarItem(new ArmaPrincipal("Arco de Caça", 15, apenasArqueiro, 7, 3));
        vendedor.adicionarItem(new ArmaPrincipal("Varinha Mágica", 25, apenasFeiticeiro, 8, 10));
        vendedor.adicionarItem(new ArmaPrincipal("Espada Curta", 10, apenasCavaleiro, 5, 3));
        vendedor.adicionarItem(new ArmaPrincipal("Machado de Guerra", 30, apenasCavaleiro, 12, 8));

        // Consumíveis de Combate
        vendedor.adicionarItem(new ConsumivelCombate("Bomba de Fogo", 10, todosHerois, 15));
        vendedor.adicionarItem(new ConsumivelCombate("Flecha Explosiva", 10, apenasArqueiro, 15));
        vendedor.adicionarItem(new ConsumivelCombate("Granada", 12, todosHerois, 20));

        Thread.sleep(1000);

        // Começo da história
        System.out.println("\nBem-vindo ao Combate Mortal, " + heroi.getNome() + "!");
        System.out.println("Estás prestes a entrar no Labirinto dos Perigos.");
        System.out.println("Antes de começar, podes comprar itens do vendedor.\n");

        Thread.sleep(1000);

        // Imprime a lista de itens da loja e permite o herói comprá-los
        while (true) {
            vendedor.imprimirLoja();
            System.out.println("\nTens " + heroi.getOuro() + " moedas de ouro para gastar.");
            System.out.print("\nEscolha o número do item que deseja comprar ou introduza -1 para sair: ");
            int opcao = scanner.nextInt();

            if (opcao == -1) {
                Thread.sleep(1000);
                System.out.println("O Vendedor está a ir embora...");
                Thread.sleep(1000);
                break;
            }
            vendedor.vender(heroi, opcao);
        }


        System.out.println("\n************ Labirinto ************\n");
        System.out.println("Encontraste 2 caminhos");
        System.out.println("1. Vale dos Mortos");
        System.out.println("2. Montanha das Virtudes");
        System.out.print("Qual caminho queres seguir: ");
        int opcao = scanner.nextInt();

        while (opcao < 1 || opcao > 2) {
            System.out.print("Opção Inválida. Tenta novamente: ");
            opcao = scanner.nextInt();
        }

        // Vale dos Mortos
        if (opcao == 1) {

        }

        // Montanha das Virtudes
        if (opcao == 2) {
            Entidade inimigo = new NPC("Inimigo " + 5, 50, 10, 10);

            System.out.println("\nEntraste na Montanha das Virtudes!");
            Thread.sleep(1000);
            System.out.println("Atenção! Encotraste um inimigo!");
            Thread.sleep(1000);
            System.out.println("Vais ter que lutar contra ele. Prepara-te!\n");
            Thread.sleep(2000);

            heroi.atacar((NPC) inimigo);


        }


        // Opcao Vale dos Mortos: Instancia inimigos para lutar
        // Duas opçoes outra vez: 1. instancia inimigos 2. aparece o vendedor

        // Opção Montanha das virtudes: Instancia inimigos para lutar
        // Opcao Tesouro: Poção de Vida

        // No final de cada sala, imprimir na consola o inventário de poções dar a escolher ao heroi se quer usar as poções ou avançar

        // As salas com inimigos vão invocar o método atacar e verificar qual o vencedorr
        // Se o Heroi ganhar, continua o jogo, caso contrario o jogo acaba, no main apresenta uma mensagem de derrota
        // e pergunta se quer jogar novamente com um menu: jogar novamente com a mesma personagem, criar uma nova ou fechar o programa



    }
}
