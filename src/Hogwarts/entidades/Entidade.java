package Hogwarts.entidades;

public abstract class Entidade {

    protected String nome;
    protected int vidaMax;
    protected int vidaAtual;
    protected int forca;

    public Entidade(String nome, int vidaMax, int forca) {
        this.nome = nome;
        this.vidaMax = vidaMax;
        this.forca = forca;
        this.vidaAtual = vidaMax; // A vida atual começa igual à vida máxima
    }

    /**
     * Exibe os detalhes da entidade, incluindo o nome, a vida atual
     * e a força no formato "Nome: [nome], Vida Atual: [vidaAtual],
     * Força: [forca]".
     */
    public void mostrarDetalhes() {
        System.out.println("Nome: " + nome + ", Vida Atual: " + vidaAtual + ", Força: " + forca);
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public int getVidaAtual() {
        return vidaAtual;
    }

    public int getForca() {
        return forca;
    }

    public void setVidaAtual(int vidaAtual) {
        this.vidaAtual = vidaAtual;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getVidaMax() {
        return vidaMax;
    }
}
