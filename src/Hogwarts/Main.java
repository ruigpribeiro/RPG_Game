package Hogwarts;

import Hogwarts.entidades.Herois.Heroi;
import Hogwarts.jogo.Jogo;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Jogo jogo = new Jogo();
        Heroi heroi = jogo.criarPersonagem();
        jogo.labirintoHogwarts(heroi);
    }
}
