package controller;

import historico.HistoricoDeComandos;
import historico.HistoricoDeConquistas;
import infra.Sessao;

import java.util.Map;

public class HistoricoController {
    private final Sessao sessao;
    private final Map<String, HistoricoDeComandos> historicosPorUsuario;
    private final Map<String, HistoricoDeConquistas> historicosConquistasPorUsuario;

    public HistoricoController(
            Sessao sessao,
            Map<String, HistoricoDeComandos> historicosPorUsuario,
            Map<String, HistoricoDeConquistas> historicosConquistasPorUsuario
    ) {
        this.sessao = sessao;
        this.historicosPorUsuario = historicosPorUsuario;
        this.historicosConquistasPorUsuario = historicosConquistasPorUsuario;
    }

    public void desfazerUltimaAcao() {
        if (sessao.getUsuarioAtual() != null) {
            String nome = sessao.getUsuarioAtual().getNome();
            HistoricoDeComandos historico = historicosPorUsuario.get(nome);
            if (historico != null) {
                historico.desfazerUltimo();
            } else {
                System.out.println("Nenhuma ação registrada para este usuário.");
            }
        } else {
            System.out.println("Faça login para desfazer ações.");
        }
    }

    public void mostrarHistoricoDeAcoes() {
        if (sessao.getUsuarioAtual() == null) {
            System.out.println("Você precisa estar logado para ver o histórico.");
            return;
        }

        String nome = sessao.getUsuarioAtual().getNome();
        HistoricoDeComandos historico = historicosPorUsuario.get(nome);

        if (historico != null) {
            historico.mostrarHistorico();
        } else {
            System.out.println("Nenhuma ação registrada para este usuário.");
        }
    }

    public void mostrarHistoricoDeConquistas() {
        if (sessao.getUsuarioAtual() == null) {
            System.out.println("Você precisa estar logado para ver o histórico de conquistas.");
            return;
        }

        String nome = sessao.getUsuarioAtual().getNome();
        HistoricoDeConquistas historico = historicosConquistasPorUsuario.get(nome);

        if (historico != null) {
            historico.mostrarHistorico();
        } else {
            System.out.println("Nenhuma conquista desbloqueada ainda.");
        }
    }
}
