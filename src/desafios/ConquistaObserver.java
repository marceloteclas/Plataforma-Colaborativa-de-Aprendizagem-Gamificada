package desafios;

import conquistas.Conquista;

public interface ConquistaObserver {
    void notificarConquista(String usuario, Conquista conquista);
}