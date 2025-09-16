package desafios;

public class NotificadorConsole implements ConquistaObserver {
    @Override
    public void notificar(String mensagem) {
        System.out.println("Conquista: " + mensagem);
    }
}