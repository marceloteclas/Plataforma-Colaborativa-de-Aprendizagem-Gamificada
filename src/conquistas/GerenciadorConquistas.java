package conquistas;

import desafios.ConquistaObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerenciadorConquistas {
    private List<ConquistaObserver> observadores = new ArrayList<>();
    private ConquistaRepositorioMemoria repositorio = new ConquistaRepositorioMemoria();

    private Map<String, List<Conquista>> conquistasPorUsuario = new HashMap<>();

    public void adicionarObservador(ConquistaObserver observer) {
        observadores.add(observer);
    }

    public void removerObservador(ConquistaObserver observer) {
        observadores.remove(observer);
    }

    public void registrarConquista(String usuario, Conquista conquista) {
        List<Conquista> conquistas = conquistasPorUsuario.computeIfAbsent(usuario, k -> new ArrayList<>());
        if (!conquistas.contains(conquista)) {
            conquistas.add(conquista);
            repositorio.salvar(conquista);
            notificarObservadores(usuario, conquista);
        }
    }

    private void notificarObservadores(String usuario, Conquista conquista) {
        for (ConquistaObserver obs : observadores) {
            obs.notificarConquista(usuario, conquista);
        }
    }

    public List<Conquista> getConquistasDoUsuario(String usuario) {
        return conquistasPorUsuario.getOrDefault(usuario, new ArrayList<>());
    }
}
