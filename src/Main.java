import Entidades.Heroi;
import Entidades.Vendedor;
import Itens.ConsumivelCombate;
import Jogo.Jogo;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Jogo jogo = new Jogo();
        Heroi heroi = jogo.criarPersonagem();
        jogo.combateMortal(heroi);
    }
}