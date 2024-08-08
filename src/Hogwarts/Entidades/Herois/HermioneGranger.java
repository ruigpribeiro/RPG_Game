package Hogwarts.Entidades.Herois;

import Hogwarts.Entidades.Inimigos.NPC;
import Hogwarts.Itens.ConsumivelCombate;
import Hogwarts.Itens.ItemHeroi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HermioneGranger extends Heroi {

    /**
     * Método construtor.
     *
     * @param nome      Nome do herói
     * @param vidaMax   Vida Max do herói
     * @param forca     Força do herói
     * @param nivel     Nivel do herói
     * @param ouro      Moedas de ouro do herói
     */
    public HermioneGranger(String nome, int vidaMax, int forca, int nivel, int ouro) {
        super(nome, vidaMax, forca, nivel, ouro);
        scanner = new Scanner(System.in);
    }

    @Override
    public boolean atacar(NPC inimigo) {
        boolean ataqueEspecialUsado = false;

        while (this.vidaAtual > 0 && inimigo.getVidaAtual() > 0) {

            boolean jaEscolheu = false;

            while (!jaEscolheu) {
                // Escolha do tipo de ataque do herói
                System.out.println("Escolha o tipo de ataque:");
                System.out.println("1. Ataque Normal");
                System.out.println("2. Ataque Especial");
                System.out.println("3. Ataque Consumível");
                System.out.print("Escolha o tipo de ataque: ");
                int opcao = scanner.nextInt();

                int dano = 0;

                switch (opcao) {
                    case 1:
                        // Ataque Normal
                        dano = this.forca + this.armaPrincipal.getAtaque();
                        System.out.println(this.nome + " usou um Ataque Normal! Dano: " + dano);
                        inimigo.setVidaAtual(inimigo.getVidaAtual() - dano);
                        jaEscolheu = true;
                        break;

                    case 2:
                        // Ataque Especial
                        if (!ataqueEspecialUsado) {
                            dano = this.forca + this.armaPrincipal.getAtaqueEspecial();
                            System.out.println(this.nome + " usou um Ataque Especial! Dano: " + dano);
                            inimigo.setVidaAtual(inimigo.getVidaAtual() - dano);
                            ataqueEspecialUsado = true;
                            jaEscolheu = true;
                        } else {
                            System.out.println("O Ataque Especial já foi usado neste combate.");
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
                            System.out.println("Nenhum consumível disponível.");
                            continue;
                        }

                        int count = 1;
                        for (Integer indice : indicesConsumivelCombate) {
                            System.out.print(count + ": ");
                            inventario.get(indice).mostrarDetalhes();
                            count++;
                        }

                        System.out.print("Escolha um consumível (ou 0 para cancelar): ");
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
                        continue;
                }
            }

            // Verifica se o inimigo foi derrotado
            if (inimigo.getVidaAtual() <= 0) {
                System.out.println(this.nome + " venceu a luta!");
                this.nivel++; // Aumenta de nível
                this.vidaMax += 10; // Aumenta 10 pontos de vida
                this.forca += 1; // Aumenta 1 ponto de força
                this.ouro += inimigo.getOuro(); // Adiciona o ouro do inimigo ao herói
                return true;
            }


            // Inimigo ataca primeiro
            int danoInimigo = inimigo.getForca();
            this.vidaAtual -= danoInimigo;
            System.out.println(inimigo.getNome() + " atacou! Dano: " + danoInimigo);

            // Verifica se o herói foi derrotado
            if (this.vidaAtual <= 0) {
                System.out.println(this.nome + " foi derrotado!");
                break;
            }
        }

        return false;
    }
}
