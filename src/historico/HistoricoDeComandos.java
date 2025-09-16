package historico;

import java.util.Stack;

public class HistoricoDeComandos {
    private Stack<Comando> comandos = new Stack<>();

    public void executarComando(Comando comando) {
        comando.executar();
        comandos.push(comando);
    }

    public void desfazerUltimo() {
        if (!comandos.isEmpty()) {
            Comando ultimo = comandos.pop();
            ultimo.desfazer();
        } else {
            System.out.println("Nenhuma ação para desfazer.");
        }
    }

    public void mostrarHistorico() {
        if (comandos.isEmpty()) {
            System.out.println("Nenhuma ação registrada.");
        } else {
            System.out.println("Histórico de ações:");
            for (Comando c : comandos) {
                System.out.println("- " + c.getClass().getSimpleName());
            }
        }
    }
}
