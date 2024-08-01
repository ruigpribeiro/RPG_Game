package Entidades;

import Itens.ArmaPrincipal;
import Itens.ItemHeroi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Vendedor {
    private List<ItemHeroi> loja;
    private Scanner scanner;

    public Vendedor() {
        loja = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    /**
     * Imprime aleatoriamente 10 itens em stock
     */
    public void imprimirLoja() {
        if (loja.size() < 10) {
            System.out.println("A loja tem menos que 10 itens em stock.");
            return;
        }

        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            int num = random.nextInt(loja.size());
            System.out.print(i+1 + ": ");
            loja.get(num).mostrarDetalhes();
            System.out.println();
        }
    }

    /**
     * Adiciona um item ao inventário do herói ou subtitui a sua Arma Principal
     * @param heroi
     */
    public void vender(Heroi heroi) {
        imprimirLoja();
        System.out.print("Escolha o número do item que deseja comprar: ");
        int opcao = scanner.nextInt();

        // Verifica se a escolha está fora do range de 0 ao tamanho do array
        if (opcao < 0 || opcao >= loja.size()) {
            System.out.println("A opção escolhida é inválida");
            return;
        }

        ItemHeroi item = loja.get(opcao);

        // Verifica se o heroi tem moedas suficientes para comprar o item
        if (heroi.getOuro() < item.getPrecoMoedasOuro()) {
            System.out.println("Não tens moedas suficientes para comprar este item.");
            return;
        }

        // Verifica se o item é arma principal, se for substitui a arma principal do Herói
        if (item instanceof ArmaPrincipal) {
            heroi.setArmaPrincipal((ArmaPrincipal) item);
            System.out.println("Arma principal foi substituída por: " + item.getNome());
            return;
        }

        // Adiciona o item no inventário do herói
        System.out.println("Um novo item foi adicionado ao teu inventário, " + heroi.getNome());
        heroi.setOuro(heroi.getOuro()-item.getPrecoMoedasOuro());

        // Remove o item da loja
        loja.remove(opcao);
    }

    /**
     * Adiciona um item à arraylist loja
     * @param itemHeroi Recebe um item como parametro
     */
    public void adicionarItem(ItemHeroi itemHeroi) {
        loja.add(itemHeroi);
    }

}
