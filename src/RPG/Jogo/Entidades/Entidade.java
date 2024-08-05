package RPG.Jogo.Entidades;

public abstract class Entidade {
    protected String nome;
    protected int vidaMax;
    protected int vidaAtual;
    protected int forca;

    public Entidade(String nome, int vidaMax, int forca) {
        this.nome = nome;
        this.vidaMax = vidaMax;
        this.vidaAtual = vidaMax; // Começa com o valor máximo
        this.forca = forca;
    }

    public void mostrarDetalhes() {
        System.out.println("Nome: " + nome);
        System.out.println("Vida Máxima: " + vidaMax);
        System.out.println("Vida Atual: " + vidaAtual);
        System.out.println("Força: " + forca);
    }

    public String getNome() {
        return nome;
    }

    public int getVidaAtual() {
        return vidaAtual;
    }
}
