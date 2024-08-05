package Hogwarts.Itens;

import java.util.List;

public abstract class Consumivel extends ItemHeroi {

    /**
     * Método construtor.
     *
     * @param nome             Nome do ítem
     * @param preco            Preço do ítem em ouro
     * @param heroisPermitidos Lista de heróis permitidos usar este ítem
     */
    public Consumivel(String nome, int preco, List<String> heroisPermitidos) {
        super(nome, preco, heroisPermitidos);
    }
}
