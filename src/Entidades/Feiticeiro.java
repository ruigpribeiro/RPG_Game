package Entidades;

import Itens.ArmaPrincipal;
import Itens.Consumivel;
import Itens.ConsumivelCombate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Feiticeiro extends Heroi {
    private Scanner scanner;

    public Feiticeiro(String nome, int vidaMax, int forca, int ouro) {
        super(nome, vidaMax, forca, ouro);
        scanner = new Scanner(System.in);
    }

    @Override
    public boolean atacar(NPC inimigo) {
        boolean jaUsouAtaqueEspecial = false;

        while (this.vidaAtual > 0 && inimigo.vidaAtual > 0) {

            // Turno do Feiticeiro para atacar
            System.out.println("O feiticiero vai atacar...");
            int opcao;

            do {
                System.out.println("Tipos de ataque:");
                System.out.println("1. Ataque Normal");
                System.out.println("2. Ataque Especial");
                System.out.println("3. Ataque Consumível");
                System.out.print("Escolha o ataque: ");
                opcao = scanner.nextInt();
            } while (opcao < 1 || opcao > 3);

            switch (opcao) {
                // Ataque normal
                case 1:
                    inimigo.vidaAtual -= this.forca + this.getArmaPrincipal().getAtaque();
                    System.out.println("Ataque Normal! Vida do inimigo: " + inimigo.vidaAtual);
                    break;
                // Ataque Especial
                case 2:
                    if (!jaUsouAtaqueEspecial) {
                        inimigo.vidaAtual -= this.forca + this.getArmaPrincipal().getAtaqueEspecial();;
                        jaUsouAtaqueEspecial = true;
                        System.out.println("Ataque Especial! Vida do inimigo: " + inimigo.vidaAtual);
                        break;
                    }
                    System.out.println("O Ataque Especial já foi usado!");
                    inimigo.vidaAtual -= this.forca + this.getArmaPrincipal().getAtaque();
                    break;
                // Ataque Consumivel
                case 3:
                    System.out.println("Lista de Consumíveis: ");
                    List<ConsumivelCombate> consumivelCombates = new ArrayList<>();
                    int counter = 1;

                    // Imprime todos os consumiveis de combate e guarda-os num novo arraylist
                    for (Consumivel consumivel : this.getInventario()) {
                        if (consumivel instanceof ConsumivelCombate) {
                            System.out.println(counter + ": " + consumivel.toString());
                            consumivelCombates.add((ConsumivelCombate) consumivel);
                            counter++;
                        }
                    }

                    // Verifica se a nova arraylist com consumiveis de combate está vazia
                    if (consumivelCombates.isEmpty()) {
                        System.out.println("Nenhum consumível disponível.");
                        break;
                    }

                    System.out.println(counter+ ": Cancelar ataque e voltar ao menu anterior");
                    System.out.print("Escolha uma opção: ");
                    int opcaoConsumivel = scanner.nextInt();

                    // Cancela o ataque se a opção neste caso for 4
                    if (opcaoConsumivel == counter) {
                        System.out.println("Ataque cancelado.");
                        break;
                    }

                    // Executa o ataque
                    if (opcaoConsumivel > 0 && opcaoConsumivel < counter) {
                        ConsumivelCombate consumivel = consumivelCombates.get(opcaoConsumivel-1);
                        inimigo.vidaAtual -= consumivel.getAtaqueInstantaneo();
                        this.getInventario().remove(consumivel);
                        System.out.println("Ataque Consumível! Vida do inimigo: " + inimigo.vidaAtual);
                    } else {
                        System.out.println("Opção Inválida. A voltar ao menu anterior.");
                    }
                    break;
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


            // Turno do Inimigo para atacar
            System.out.println("O inimigo vai atacar...");
            this.vidaAtual -= inimigo.forca;
            System.out.println("Vida do Feiticeiro: " + this.vidaAtual);

            // Verifica se o feiticeiro foi derrotado
            if (this.vidaAtual <= 0) {
                System.out.println("O " + nome + " foi derrotado");
                return false;
            }
        }
        return false;
    }
}
