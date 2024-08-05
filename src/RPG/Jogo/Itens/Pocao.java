package RPG.Jogo.Itens;

import java.util.List;

public class Pocao extends Consumivel {
    private int vidaACurar;
    private int aumentoForca;

    public Pocao(String nome, int precoMoedasOuro, List<String> heroisPermitidos, int vidaACurar, int aumentoForca) {
        super(nome, precoMoedasOuro, heroisPermitidos);
        this.vidaACurar = vidaACurar;
        this.aumentoForca = aumentoForca;
    }

    public int getVidaACurar() {
        return vidaACurar;
    }

    public int getAumentoForca() {
        return aumentoForca;
    }

    @Override
    public void mostrarDetalhes() {
        super.mostrarDetalhes();
        System.out.print(" | Vida a Curar: " + this.vidaACurar + " | Aumento da Força: " + aumentoForca);
    }
}
