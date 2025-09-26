package desafios;

import conquistas_reestruturadas.Conquista;
public interface ConquistaObserver {
    void notificarConquista(String usuario, conquistas_reestruturadas.Conquista conquista);
}