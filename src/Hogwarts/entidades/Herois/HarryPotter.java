package Hogwarts.entidades.Herois;

import Hogwarts.entidades.NPC;
import Hogwarts.itens.ConsumivelCombate;
import Hogwarts.itens.ItemHeroi;
import Hogwarts.utils.ConsoleUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HarryPotter extends Heroi {
    private Scanner scanner;

    /**
     * Método construtor.
     *
     * @param nome    Nome do herói
     * @param vidaMax Vida Max do herói
     * @param forca   Força do herói
     * @param nivel   Nivel do herói
     * @param ouro    Moedas de ouro do herói
     */
    public HarryPotter(String nome, int vidaMax, int forca, int nivel, int ouro) {
        super(nome, vidaMax, forca, nivel, ouro);
        scanner = new Scanner(System.in);
    }

    /**
     * Método que representa o ataque de um herói contra um NPC inimigo.
     * O herói pode escolher entre diferentes tipos de ataques: normal, especial e consumo de itens.
     * A vitória ou derrota depende da força e vida restante de ambos os personagens.
     *
     * @param inimigo O NPC inimigo contra o qual o herói está lutando.
     * @return true se o herói vencer a luta, false se o herói for derrotado.
     */
    @Override
    public boolean atacar(NPC inimigo) {
        boolean ataqueEspecialUsado = false;

        while (this.vidaAtual > 0 && inimigo.getVidaAtual() > 0) {

            // Inimigo ataca primeiro
            int danoInimigo = (int) (inimigo.getForca() * 0.8); // Recebe apenas 80% do dano do inimigo
            this.vidaAtual -= danoInimigo;
            System.out.println(ConsoleUtils.RED_BOLD + inimigo.getNome() + " atacou! Dano: " + danoInimigo + ConsoleUtils.RESET);

            // Verifica se o herói foi derrotado
            if (this.vidaAtual <= 0) {
                System.out.println(this.nome + " foi derrotado!");
                break;
            }

            boolean jaEscolheu = false;

            while (!jaEscolheu) {
                // Escolha do tipo de ataque do herói
                System.out.println(ConsoleUtils.YELLOW + "\nEscolha o tipo de ataque:" + ConsoleUtils.RESET);
                System.out.println("1. Ataque Normal");
                System.out.println("2. Ataque Especial");
                System.out.println("3. Ataque Consumível");
                System.out.print("Opção: ");
                int opcao = scanner.nextInt();

                int dano = 0;

                switch (opcao) {
                    case 1:
                        // Ataque Normal
                        dano = this.forca + this.armaPrincipal.getAtaque();
                        System.out.println("\n" + this.nome + " usou um Ataque Normal! Dano: " + dano);
                        inimigo.setVidaAtual(inimigo.getVidaAtual() - dano);
                        jaEscolheu = true;
                        break;

                    case 2:
                        // Ataque Especial
                        if (!ataqueEspecialUsado) {
                            dano = this.forca + this.armaPrincipal.getAtaqueEspecial();
                            System.out.println("\n" + this.nome + " usou um Ataque Especial! Dano: " + dano);
                            inimigo.setVidaAtual(inimigo.getVidaAtual() - dano);
                            ataqueEspecialUsado = true;
                            jaEscolheu = true;
                        } else {
                            System.out.println(ConsoleUtils.RED_BOLD + "\nO Ataque Especial já foi usado neste combate." + ConsoleUtils.RESET);
                        }
                        break;

                    case 3:
                        // Usar Consumível
                        List<Integer> indicesConsumivelCombate = new ArrayList<>();
                        for (int i = 0; i < inventario.size(); i++) {
                            ItemHeroi item = inventario.get(i);
                            if (item instanceof ConsumivelCombate) {
                                indicesConsumivelCombate.add(i);
                            }
                        }

                        if (indicesConsumivelCombate.isEmpty()) {
                            System.out.println(ConsoleUtils.RED_BOLD + "\nNenhum consumível disponível." + ConsoleUtils.RESET);
                            continue;
                        }

                        int count = 1;
                        for (Integer indice : indicesConsumivelCombate) {
                            System.out.print(count + ": ");
                            inventario.get(indice).mostrarDetalhes();
                            System.out.println();
                            count++;
                        }

                        System.out.print(ConsoleUtils.YELLOW + "Escolha um consumível (ou 0 para cancelar): " + ConsoleUtils.RESET);
                        int opcaoConsumivel = scanner.nextInt();

                        if (opcaoConsumivel > 0 && opcaoConsumivel <= indicesConsumivelCombate.size()) {
                            int indiceEscolhido = indicesConsumivelCombate.get(opcaoConsumivel - 1);
                            ConsumivelCombate consumivel = (ConsumivelCombate) inventario.get(indiceEscolhido);
                            dano = consumivel.getAtaqueInstantaneo();
                            System.out.println(this.nome + " usou " + consumivel.getNome() + " causando " + dano + " de dano.");
                            inimigo.setVidaAtual(inimigo.getVidaAtual() - dano);
                            inventario.remove(indiceEscolhido); // Remove do inventário o consumível usado
                        } else {
                            System.out.println("Ação cancelada.");
                            continue;
                        }
                        jaEscolheu = true;
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            }

            // Verifica se o inimigo foi derrotado
            if (inimigo.getVidaAtual() <= 0) {
                System.out.println(ConsoleUtils.GREEN_BOLD + this.nome + " venceu a luta! Parabéns!" + ConsoleUtils.RESET );
                this.nivel++; // Aumenta de nível
                this.vidaMax += 10; // Aumenta 10 pontos de vida
                this.forca += 1; // Aumenta 1 ponto de força
                this.ouro += inimigo.getOuro(); // Adiciona o ouro do inimigo ao herói
                return true;
            }
        }

        return false;
    }
}
