package Entidades;

public class NPC extends Entidade {
    private int ouro;

    public NPC(String nome, int vidaMax, int vidaAtual, int forca, int ouro) {
        super(nome, vidaMax, vidaAtual, forca);
        this.ouro = ouro;
    }

    @Override
    public void mostrarDetalhes() {
        super.mostrarDetalhes();
        System.out.println("Ouro: " + this.ouro);
    }
}
