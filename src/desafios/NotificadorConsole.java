package desafios;

import conquistas_reestruturadas.Conquista;
public class NotificadorConsole implements ConquistaObserver {

    @Override
    public void notificarConquista(String usuario, Conquista conquista) {
        System.out.println("🔔 [Notificação] Usuário " + usuario + " desbloqueou conquista: " +
                conquista.getNome() + " - " + conquista.getDescricao());
    }
}
