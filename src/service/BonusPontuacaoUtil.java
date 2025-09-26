package service;

import java.util.Map;

public class BonusPontuacaoUtil {

    public static int aplicarBonus(int pontuacaoBase, String nomeUsuario, Map<String, Integer> desafiosRespondidos) {
        int pontuacao = pontuacaoBase;
        int totalDesafios = desafiosRespondidos.getOrDefault(nomeUsuario, 0);

        if (totalDesafios % 3 == 0 && totalDesafios > 0) {
            pontuacao = (int) (pontuacao * 1.20);
            System.out.println("Bônus de streak aplicado!");
        }

        if (pontuacaoBase > 50) {
            pontuacao *= 2;
            System.out.println("Double XP ativado!");
        }

        return pontuacao;
    }
}
