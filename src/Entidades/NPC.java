package Entidades;

public class NPC extends Entidade {
    private int ouro;

    public NPC(String nome, int vidaMax, int forca, int ouro) {
        super(nome, vidaMax, forca);
        this.ouro = ouro;
    }

    public int getOuro() {
        return ouro;
    }

    @Override
    public void mostrarDetalhes() {
        super.mostrarDetalhes();
        System.out.println("Ouro: " + this.ouro);
    }
}
