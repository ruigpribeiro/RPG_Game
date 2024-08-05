package Hogwarts.Entidades;

public abstract class Entidade {

    private String nome;
    private int vidaMax;
    private int vidaAtual;
    private int forca;

    public Entidade(String nome, int vidaMax, int vidaAtual, int forca) {
        this.nome = nome;
        this.vidaMax = vidaMax;
        this.vidaAtual = vidaMax;
        this.forca = forca;
    }

    public void mostrarDetalhes() {
        System.out.println("Nome: " + nome + ", Vida Atual: " + vidaAtual + ", Força: " + forca);
    }
}
