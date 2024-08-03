package Entidades;

import Itens.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cavaleiro extends Heroi {
    private Scanner scanner;

    public Cavaleiro(String nome, int vidaMax, int forca, int ouro) {
        super(nome, vidaMax, forca, ouro);
        scanner = new Scanner(System.in);
    }

    /**
     * Implementa uma série de ataques até que um dos players termine sem vida.
     * Começa pelo ataque do ínimigo e vai trocando de turno com o cavaleiro.
     * O cavaleiro tem vários tipos de ataque por onde escolher.
     * @param inimigo
     * @return True se o cavaleiro venceu. False se o cavaleiro foi derrotado.
     */
    @Override
    public boolean atacar(NPC inimigo) {
        // Boleano para ver se o ataque especial já foi usado
        boolean jaUsouAtaqueEspecial = false;

        // Itera até que um deles tenha a vida menor ou igual a 0
        while (this.vidaAtual > 0 && inimigo.vidaAtual > 0) {

            // Turno do inimigo para atacar
            System.out.println("O inimigo vai atacar...");
            this.vidaAtual -= (int) (inimigo.forca * 0.8);
            System.out.println("Vida do Cavaleiro: " + this.vidaAtual);

            // Verifica se o cavaleiro foi derrotado
            if (this.vidaAtual <= 0) {
                System.out.println("O " + nome + " foi derrotado");
                return false;
            }

            // Turno do cavaleiro para atacar
            System.out.println("O " + nome + " vai atacar...\n");

            boolean skip = false;

            while (!skip) {
                int opcao;
                do {
                    System.out.println("Tipos de ataque:");
                    System.out.println("1. Ataque Normal");
                    System.out.println("2. Ataque Especial");
                    System.out.println("3. Ataque Consumível");
                    System.out.print("Escolha o ataque: ");
                    opcao = scanner.nextInt();
                    System.out.println();
                } while (opcao < 1 || opcao > 3);

                switch (opcao) {
                    // Ataque normal
                    case 1:
                        inimigo.vidaAtual -= this.forca + this.getArmaPrincipal().getAtaque();
                        System.out.println("Ataque Normal! Vida do inimigo: " + inimigo.vidaAtual);
                        skip = true;
                        break;
                    // Ataque Especial
                    case 2:
                        if (!jaUsouAtaqueEspecial) {
                            inimigo.vidaAtual -= this.forca + this.getArmaPrincipal().getAtaqueEspecial();
                            System.out.println("Dano do Ataque Especial: " + this.getArmaPrincipal().getAtaqueEspecial());
                            jaUsouAtaqueEspecial = true;
                            System.out.println("Ataque Especial! Vida do inimigo: " + inimigo.vidaAtual);
                            skip = true;
                        } else {
                            System.out.println("O Ataque Especial já foi usado!\n");
                        }
                        break;
                    // Ataque Consumivel
                    case 3:
                        System.out.println("Lista de Consumíveis: ");

                        List<ConsumivelCombate> consumivelCombates = new ArrayList<>();
                        int counter = 1;

                        // Imprime todos os consumiveis de combate e guarda-os num novo arraylist
                        for (Consumivel consumivel : this.getInventario()) {
                            if (consumivel instanceof ConsumivelCombate) {
                                System.out.print(counter + ": ");
                                consumivel.mostrarDetalhes();
                                consumivelCombates.add((ConsumivelCombate) consumivel);
                                counter++;
                            }
                        }

                        // Verifica se a nova arraylist com consumiveis de combate está vazia
                        if (consumivelCombates.isEmpty()) {
                            System.out.println("Nenhum consumível disponível.\n");
                            break;
                        }

                        System.out.println("\n" + counter + ": Cancelar ataque e voltar ao menu anterior");
                        System.out.print("Escolha uma opção: ");
                        int opcaoConsumivel = scanner.nextInt();

                        // Cancela o ataque se a opção neste caso for 4
                        if (opcaoConsumivel == counter) {
                            System.out.println("Ataque cancelado.");
                            break;
                        }

                        // Executa o ataque
                        if (opcaoConsumivel > 0 && opcaoConsumivel < counter) {
                            ConsumivelCombate consumivel = consumivelCombates.get(opcaoConsumivel - 1);
                            inimigo.vidaAtual -= consumivel.getAtaqueInstantaneo();
                            this.getInventario().remove(consumivel);
                            System.out.println("\nAtaque Consumível! Vida do inimigo: " + inimigo.vidaAtual);
                            skip = true;
                        } else {
                            System.out.println("Opção Inválida. A voltar ao menu anterior.");
                        }
                        break;

                }
            }

            // Verifica se o inimigo foi derrotado
            if (inimigo.vidaAtual <= 0) {
                System.out.println("O inimigo foi derrotado! Parabéns, " + this.nome + "!");
                this.setNivel(this.getNivel() + 1); // Aumenta o nível
                super.vidaAtual += 10; // Aumenta a vida em 10 pontos
                super.forca += 1; // Aumenta a força em 1 ponto
                this.setOuro(this.getOuro()+inimigo.getOuro()); // Aumenta o ouro com o valor do ouro do inimigo
                return true;
            }
        }
        return false;
    }
}
