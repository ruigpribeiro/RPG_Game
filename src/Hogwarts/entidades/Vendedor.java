package Hogwarts.entidades;

import Hogwarts.entidades.Herois.Heroi;
import Hogwarts.itens.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Vendedor {
    private List<ItemHeroi> loja;


    public Vendedor() {
        loja = new ArrayList<>();
    }

    /**
     * Vende um item da loja para um herói, verificando se o herói pode usar o item,
     * se possui ouro suficiente e atualiza o ouro e os itens do herói em conformidade.
     *
     * @param heroi O herói que está comprando o item.
     * @param opcao O índice do item na loja que o herói deseja comprar.
     */
    public void venderItem(Heroi heroi, int opcao) {
        ItemHeroi item = loja.get(opcao);

        // Verifica se o herói pode usar o ítem
        if (!item.getHeroisPermitidos().contains(heroi.getClass().getSimpleName())) {
            System.out.println("\nEste ítem não é adequado para o teu herói.\n");
            return;
        }

        // Verifica se o herói tem ouro suficiente para comprar o ítem
        if (heroi.getOuro() < item.getPreco()) {
            System.out.println("\nNão tens moedas suficientes para comprar este ítem.\n");
            return;
        }

        // Subtrai o ouro do herói
        heroi.setOuro(heroi.getOuro() - item.getPreco());

        if (item instanceof ArmaPrincipal) {
            heroi.setArmaPrincipal((ArmaPrincipal) item);
            System.out.println("\nA varinha foi substítuida pela arma principal!");
        } else if (item instanceof Pocao) {
            heroi.adicionarItemAoInventario(item);
            System.out.println("\nA poção foi adicionada ao teu inventário.");
        } else if (item instanceof ConsumivelCombate) {
            heroi.adicionarItemAoInventario(item);
            System.out.println("\nO feitiço foi adicionado ao teu inventário.");
        }
    }

    /**
     * Mostra itens disponíveis na loja para o usuário. Se a loja contém menos de 10 itens, uma mensagem
     * será exibida solicitando para adicionar mais itens. Em seguida, seleciona aleatoriamente 10 itens
     * da loja, imprime os seus detalhes e retorna a lista dos índices dos itens exibidos.
     *
     * @return Uma lista de índices dos itens que foram exibidos da loja.
     */
    public List<Integer> mostrarItensLoja() {
        Random random = new Random();

        // Verifica se a loja tem pelo menos 10 ítens
        if (loja.size() < 10) {
            System.out.println("A loja tem menos do que 10 itens! Adiciona mais.");
        }

        List<Integer> indicesUsados = new ArrayList<>();

        int count = 0;
        while (count < 10) {
            int index = random.nextInt(loja.size());

            // Adiciona o índice à lista de índices usados, se ainda não estiver nela
            if (!indicesUsados.contains(index)) {
                indicesUsados.add(index);
                count++;
            }
        }

        // Imprime os ítens
        for (int i = 0; i < indicesUsados.size(); i++) {
            System.out.print((i + 1) + ": ");
            loja.get(indicesUsados.get(i)).mostrarDetalhes();
            System.out.println();
        }

        return indicesUsados;
    }

    /**
     * Adiciona um item à loja do vendedor.
     *
     * @param item O item a ser adicionado à loja.
     */
    public void adicionarItem(ItemHeroi item) {
        loja.add(item);
    }
}
