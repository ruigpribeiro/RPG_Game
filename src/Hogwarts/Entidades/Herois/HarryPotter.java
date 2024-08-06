package Hogwarts.Entidades.Herois;

import Hogwarts.Entidades.Inimigos.NPC;

import java.util.Scanner;

public class HarryPotter extends Heroi {
    private Scanner scanner;

    /**
     * Método construtor.
     *
     * @param nome      Nome do herói
     * @param vidaMax   Vida Max do herói
     * @param forca     Força do herói
     * @param nivel     Nivel do herói
     * @param ouro      Moedas de ouro do herói
     */
    public HarryPotter(String nome, int vidaMax, int forca, int nivel, int ouro) {
        super(nome, vidaMax, forca, nivel, ouro);
    }

    @Override
    public boolean atacar(NPC inimigo) {
        boolean ataqueEspecialUsado = false;

        while (this.vidaAtual > 0 && inimigo.getVidaAtual() > 0) {

            // Inimigo ataca primeiro
            int danoInimigo = (int) (inimigo.getForca() * 0.8);
            this.vidaAtual -= danoInimigo;
            System.out.println(inimigo.getNome() + " atacou primeiro! Dano: " + danoInimigo);

            // Verifica se o herói foi derrotado
            if (this.vidaAtual <= 0) {
                System.out.println(this.nome + " foi derrotado!");
                break;
            }

            // Escolha do tipo de ataque do herói
            System.out.println("Escolha o tipo de ataque: ");
            System.out.println("1. Ataque Normal");
            System.out.println("2. Ataque Especial");
            System.out.println("3. Ataque Consumível");
            int opcao = scanner.nextInt();

            int dano = 0;

            switch (opcao) {
                case 1:
                    // Ataque Normal
                    System.out.println(nome + " usou um Ataque Normal!");
                    dano = this.forca + this.armaPrincipal.getAtaque();
                    inimigo.setVidaAtual(inimigo.getVidaAtual() - dano);
                    break;
                case 2:
                    // Ataque Especial
                    if (!ataqueEspecialUsado) {
                        System.out.println(nome + " usou um Ataque Especial!");
                        dano = this.forca + this.armaPrincipal.getAtaqueEspecial();
                        inimigo.setVidaAtual(inimigo.getVidaAtual() - dano);
                        ataqueEspecialUsado = true;
                    } else {
                        System.out.println("O ataque Especial já foi usado nesta luta.");
                    }
                    break;
                case 3:
                    // Ataque Consumível
                    break;
            }

            // Verifica se o inimigo foi derrotado
            if (inimigo.getVidaAtual() <= 0) {
                System.out.println(this.getNome() + " venceu a luta!");
                this.nivel++;; // Aumenta de nível
                this.vidaMax += 10; // Aumenta 10 pontos de vida
                this.forca += 1; // Aumenta 1 ponto de força
                this.ouro += inimigo.getOuro(); // Adiciona o ouro do inimigo ao herói
                break;
            }
        }

        return false;
    }
}
