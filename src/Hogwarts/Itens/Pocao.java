package Hogwarts.Itens;

import java.util.List;

public class Pocao extends Consumivel {
    private int vidaCurar;
    private int aumentoForca;

    public Pocao(String nome, int preco, List<String> heroisPermitidos, int vidaCurar, int aumentoForca) {
        super(nome, preco, heroisPermitidos);
        this.vidaCurar = vidaCurar;
        this.aumentoForca = aumentoForca;
    }

    @Override
    public void mostrarDetalhes() {
        super.mostrarDetalhes();
        System.out.print(", Vida a Curar: " + vidaCurar + ", Aumento de Força: " + aumentoForca);
    }

    public int getVidaCurar() {
        return vidaCurar;
    }

    public int getAumentoForca() {
        return aumentoForca;
    }
}