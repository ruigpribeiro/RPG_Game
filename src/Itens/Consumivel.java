package Itens;

import java.util.List;

public abstract class Consumivel extends ItemHeroi {

    public Consumivel(String nome, int precoMoedasOuro, List<String> heroisPermitidos) {
        super(nome, precoMoedasOuro, heroisPermitidos);
    }

}
