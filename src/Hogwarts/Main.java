package Hogwarts;

import Hogwarts.Entidades.Herois.Heroi;
import Hogwarts.Jogo.Jogo;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Jogo jogo = new Jogo();
        Heroi heroi = jogo.criarPersonagem();
        jogo.labirintoHogwarts(heroi);
    }
}
