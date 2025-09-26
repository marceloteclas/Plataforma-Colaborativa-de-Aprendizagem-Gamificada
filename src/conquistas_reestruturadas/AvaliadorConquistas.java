package conquistas_reestruturadas;

import java.util.*;

public class AvaliadorConquistas {

    public static List<Conquista> avaliarConquistas(
            String nomeUsuario,
            int pontuacaoFinal,
            int acertos,
            int totalPerguntas,
            GerenciadorConquistas gerenciador,
            Map<String, Integer> desafiosRespondidosPorUsuario,
            Map<String, Set<Conquista>> conquistasPorUsuarioGlobal,
            Set<Conquista> conquistasDoUsuario) {

        int totalRespondidos = desafiosRespondidosPorUsuario.getOrDefault(nomeUsuario, 0);
        List<Conquista> novasConquistas = new ArrayList<>();

        if (totalRespondidos == 1 && !jaPossuiConquista(conquistasDoUsuario, "Desafio 1")) {
            novasConquistas.add(new ConquistaDesafio1());
        }
        if (totalRespondidos == 5 && !jaPossuiConquista(conquistasDoUsuario, "Desafio X5")) {
            novasConquistas.add(new ConquistaX5());
        }
        if (totalRespondidos == 10 && !jaPossuiConquista(conquistasDoUsuario, "Desafio X10")) {
            novasConquistas.add(new ConquistaX10());
        }
        if (totalRespondidos == 15 && !jaPossuiConquista(conquistasDoUsuario, "Desafio X15")) {
            novasConquistas.add(new ConquistaX15());
        }
        if (pontuacaoFinal > 100 && !jaPossuiConquista(conquistasDoUsuario, "Pontuação Alta")) {
            novasConquistas.add(new ConquistaPontuacaoAlta());
        }
        if (acertos == totalPerguntas && totalPerguntas > 0 &&
                !jaPossuiConquista(conquistasDoUsuario, "Desafio Perfeito")) {
            novasConquistas.add(new MedalhaSimples("Desafio Perfeito", "Acertou todas as perguntas!"));
        }

        conquistasDoUsuario.addAll(novasConquistas);
        conquistasPorUsuarioGlobal.put(nomeUsuario, conquistasDoUsuario);

        for (Conquista conquista : novasConquistas) {
            gerenciador.registrarConquista(nomeUsuario, conquista);
        }

        return novasConquistas;
    }

    private static boolean jaPossuiConquista(Set<Conquista> conquistas, String nome) {
        for (Conquista c : conquistas) {
            if (c.getNome().equals(nome)) return true;
        }
        return false;
    }
}