package Hogwarts;

import Hogwarts.Entidades.Herois.Heroi;
import Hogwarts.Jogo.Jogo;

public class Main {
    public static void main(String[] args) {

        /*
        Escolher uma casa:
        - gryffindor: bonus de coragem e poder de ataque
        - slytherin: bonus para astucia e habilidades de furtividade
        - Ravenclaw: bonus para inteligencia e poder de feiticos
        - Hufflepuff: bonus para resiliencia e habilidades de cura
         */

        Jogo jogo = new Jogo();
        Heroi heroi = jogo.criarPersonagem();
        jogo.labirintoMagico(heroi);
    }
}
