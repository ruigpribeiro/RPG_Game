package RPG.Jogo;

import RPG.Jogo.Entidades.Heroi;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Jogo jogo = new Jogo();
        Heroi heroi = jogo.criarPersonagem();
        jogo.combateMortal(heroi);
    }
}