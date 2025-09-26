package service;

import infra.Sessao;
import conquistas_reestruturadas.Conquista;

import java.util.Map;
import java.util.Set;

public class ConquistaService {
    private final Sessao sessao;
    private final Map<String, Set<Conquista>> conquistasPorUsuario;

    public ConquistaService(Sessao sessao, Map<String, Set<Conquista>> conquistasPorUsuario) {
        this.sessao = sessao;
        this.conquistasPorUsuario = conquistasPorUsuario;
    }

    public void listarConquistas() {
        if (sessao.getUsuarioAtual() == null) {
            System.out.println("Você precisa estar logado para ver suas conquistas.");
            return;
        }

        String nome = sessao.getUsuarioAtual().getNome();
        Set<Conquista> conquistas = conquistasPorUsuario.get(nome);

        if (conquistas == null || conquistas.isEmpty()) {
            System.out.println("Nenhuma conquista desbloqueada ainda.");
        } else {
            System.out.println("Conquistas de " + nome + ":");
            for (Conquista c : conquistas) {
                System.out.println("🏅 " + c.getNome() + " - " + c.getDescricao());
            }
        }
    }
}
