package RPG.Jogo.Entidades;

import RPG.Jogo.Itens.ArmaPrincipal;
import RPG.Jogo.Itens.Consumivel;
import RPG.Jogo.Itens.ItemHeroi;

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

        List<Integer> indicesJaUsados = new ArrayList<>();
        Random random = new Random();

        int count = 0;
        while (count < 10) {
            int index = random.nextInt(loja.size());

           if (!indicesJaUsados.contains(index)) {
               indicesJaUsados.add(index);
               System.out.print(index + ": ");
               loja.get(index).mostrarDetalhes();
               System.out.println();
               count++;
           }
        }

    }

    /**
     * Adiciona um ítem ao inventário do herói ou subtitui a sua Arma Principal
     *
     * @param heroi
     */
    public void vender(Heroi heroi, int opcao) throws InterruptedException {

        ItemHeroi item = loja.get(opcao);

        // Verifica se o item tem na lista de permitidos o herói
        if (!item.getHeroisPermitidos().contains(heroi.getClass().getSimpleName())) {
            System.out.println("\nEste herói não pode comprar este item!");
            return;
        }

        // Verifica se o heroi tem moedas suficientes para comprar o ítem
        if (heroi.getOuro() < item.getPrecoMoedasOuro()) {
            System.out.println("\nNão tens moedas suficientes para comprar este item.");
            return;
        }

        // Verifica se o ítem é arma principal, se for substitui a arma principal do Herói
        if (item instanceof ArmaPrincipal) {
            heroi.setArmaPrincipal((ArmaPrincipal) item);
            heroi.setOuro(heroi.getOuro() - item.getPrecoMoedasOuro());
            System.out.println("\nArma principal foi substituída por: " + item.getNome() + "!");
        }

        // Adiciona o ítem no inventário do herói
        if (item instanceof Consumivel) {
            System.out.println("\nUm novo item foi adicionado ao teu inventário, " + heroi.getNome() + "!");
            heroi.getInventario().add((Consumivel) item);
            heroi.setOuro(heroi.getOuro() - item.getPrecoMoedasOuro());
        }

        // Remove o item da loja
        Thread.sleep(1000);
        System.out.println("" + loja.get(opcao).getNome() + " foi removida da loja.\n");
        loja.remove(opcao);
    }

    /**
     * Adiciona um ítem à arraylist loja
     *
     * @param item Recebe um ítem como parametro
     */
    public void adicionarItem(ItemHeroi item) {
        loja.add(item);
    }

}
