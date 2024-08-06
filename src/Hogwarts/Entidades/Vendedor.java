package Hogwarts.Entidades;

import Hogwarts.Entidades.Herois.Heroi;
import Hogwarts.Itens.ConsumivelCombate;
import Hogwarts.Itens.ItemHeroi;
import Hogwarts.Itens.Pocao;
import Hogwarts.Itens.ArmaPrincipal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Vendedor {
    private List<ItemHeroi> loja;

    /**
     * Método construtor
     */
    public Vendedor() {
        loja = new ArrayList<>();
    }

    /**
     * Vende ítens ao herói e adiciona-os ao seu inventário ou substítui caso seja varinha.
     * @param heroi O herói
     * @param opcao A opção da lista
     */
    public void venderItem(Heroi heroi, int opcao) {

        ItemHeroi item = loja.get(opcao);

        // Verifica se o herói pode usar o ítem
        if (!item.getHeroisPermitidos().contains(heroi.getClass().getSimpleName())) {
            System.out.println("Este ítem não é adequado para o teu herói.");
            return;
        }

        // Verifica se o herói tem ouro suficiente para comprar o ítem
        if (heroi.getOuro() < item.getPreco()) {
            System.out.println("Não tens moedas suficientes para comprar este ítem.");
            return;
        }

        heroi.setOuro(heroi.getOuro() - item.getPreco());

        if (heroi.getOuro() >= item.getPreco()) {

            if (item instanceof ArmaPrincipal) {
                heroi.setVarinha((ArmaPrincipal) item);
                System.out.println("Uma nova Varinha para teu arsenal mágico!");
            }

            if (item instanceof Pocao) {
                heroi.adicionarItemAoInventario(item);
                System.out.println("A poção foi adicionada ao teu inventário.");
            }

            if (item instanceof ConsumivelCombate) {
                heroi.adicionarItemAoInventario(item);
                System.out.println("O Artefato Mágico foi adicionado ao teu inventário.");
            }
        }

        // Imprime o ouro restante
        System.out.println("Tens agora " + heroi.getOuro() + " moedas de ouro restantes.");
    }


    /**
     * Imprime aleatóriamente 10 itens da loja.
     */
    public void mostrarItensLoja() {
        Random random = new Random();

        // Verifica se a loja tem pelo menos 10 ítens
        if (loja.size() < 10) {
            System.out.println("A loja tem menos do que 10 itens! Adiciona mais.");
            return;
        }

        List<Integer> indicesUsados = new ArrayList<>();

        int count = 0;
        while (count < 10) {
            int index = random.nextInt(loja.size());

            // Verifica se ainda não está na lista de índices usados
            if (!indicesUsados.contains(index)) {
                indicesUsados.add(index);
                System.out.print(index + ": ");
                loja.get(index).mostrarDetalhes();
                System.out.println();
                count++;
            }
        }
    }

    /**
     * Adiciona um ítem à lista loja do vendedor
     * @param item O ìtem a adicionar
     */
    public void adicionarItem(ItemHeroi item) {
        loja.add(item);
    }
}
