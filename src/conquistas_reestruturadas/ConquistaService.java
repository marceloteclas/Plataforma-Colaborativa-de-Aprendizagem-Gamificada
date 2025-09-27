package conquistas_reestruturadas;

import desafios.Desafio;
import java.util.*;

public class ConquistaService {

    public Map<String, List<Conquista>> atribuirConquistas(Desafio desafio, Map<String, Integer> ranking) {
        Map<String, List<Conquista>> conquistasPorUsuario = new HashMap<>();

        List<Map.Entry<String, Integer>> listaOrdenada = new ArrayList<>(ranking.entrySet());
        listaOrdenada.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        int posicao = 0;
        for (Map.Entry<String, Integer> entrada : listaOrdenada) {
            String usuario = entrada.getKey();
            List<Conquista> conquistas = new ArrayList<>();

            conquistas.add(new ConquistaSimples("Desafio concluído: " + desafio.getTitulo(),
                                                "Completou o desafio " + desafio.getTitulo()));

            if (posicao == 0) {
                conquistas.add(new MedalhaDeOuro("Maior pontuação no desafio " + desafio.getTitulo()));
            } else if (posicao == 1) {
                conquistas.add(new MedalhaSimples("Medalha de Prata", "Segundo lugar no desafio " + desafio.getTitulo()));
            } else if (posicao == 2) {
                conquistas.add(new MedalhaSimples("Medalha de Bronze", "Terceiro lugar no desafio " + desafio.getTitulo()));
            }

            conquistasPorUsuario.put(usuario, conquistas);
            posicao++;
        }

        return conquistasPorUsuario;
    }
}
