package service;

import desafios.Desafio;
import desafios.DesafioRepositorioMemoria;
import desafios.PontuacaoStrategy;
import historico.*;
import infra.Sessao;
import conquistas_reestruturadas.*;

import java.util.*;

public class DesafioService {

    private final DesafioRepositorioMemoria desafioRepositorio;
    private final Sessao sessao;
    private final Map<String, HistoricoDeComandos> historicosPorUsuario;
    private final Map<String, Integer> desafiosRespondidosPorUsuario;
    private final Map<String, Set<Conquista>> conquistasPorUsuario;
    private final GerenciadorConquistas gerenciadorConquistas;
    private final Map<String, HistoricoDeConquistas> historicosConquistasPorUsuario;

    public DesafioService(
            DesafioRepositorioMemoria desafioRepositorio,
            Sessao sessao,
            Map<String, HistoricoDeComandos> historicosPorUsuario,
            Map<String, Integer> desafiosRespondidosPorUsuario,
            Map<String, Set<Conquista>> conquistasPorUsuario,
            GerenciadorConquistas gerenciadorConquistas,
            Map<String, HistoricoDeConquistas> historicosConquistasPorUsuario
    ) {
        this.desafioRepositorio = desafioRepositorio;
        this.sessao = sessao;
        this.historicosPorUsuario = historicosPorUsuario;
        this.desafiosRespondidosPorUsuario = desafiosRespondidosPorUsuario;
        this.conquistasPorUsuario = conquistasPorUsuario;
        this.gerenciadorConquistas = gerenciadorConquistas;
        this.historicosConquistasPorUsuario = historicosConquistasPorUsuario;
    }

    // Criar desafio
    public void criarDesafio(
            String titulo,
            String descricao,
            String tipoDesafio,
            List<String> perguntas,
            List<String> respostas,
            int nivelDificuldade,
            PontuacaoStrategy estrategia
    ) {
        Desafio desafio = new Desafio(
                titulo,
                descricao,
                tipoDesafio,
                perguntas,
                respostas,
                nivelDificuldade,
                estrategia
        );
        desafioRepositorio.salvar(desafio);
    }

    public void responderDesafio(Scanner scanner) {
        List<Desafio> desafios = desafioRepositorio.listarTodos();
        if (desafios.isEmpty()) {
            System.out.println("Nenhum desafio disponível.");
            return;
        }

        System.out.println("Desafios disponíveis:");
        for (int i = 0; i < desafios.size(); i++) {
            System.out.println((i + 1) + " - " + desafios.get(i).getTitulo());
        }

        System.out.print("Escolha o número do desafio: ");
        int escolha;
        try {
            escolha = Integer.parseInt(scanner.nextLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
            return;
        }

        if (escolha < 0 || escolha >= desafios.size()) {
            System.out.println("Desafio inválido.");
            return;
        }

        Desafio desafioSelecionado = desafios.get(escolha);
        long tempoInicio = System.currentTimeMillis();

        int acertos = 0;
        for (int i = 0; i < desafioSelecionado.getPerguntas().size(); i++) {
            System.out.println("Pergunta " + (i + 1) + ": " + desafioSelecionado.getPerguntas().get(i));
            String resp = scanner.nextLine();
            if (resp.equalsIgnoreCase(desafioSelecionado.getRespostas().get(i))) {
                acertos++;
            }
        }

        long tempoFim = System.currentTimeMillis();
        long tempoGasto = (tempoFim - tempoInicio) / 1000;

        int pontuacaoBase = desafioSelecionado.getEstrategiaPontuacao()
                .calcularPontuacao(desafioSelecionado, acertos, tempoGasto);

        int pontuacaoFinal = BonusPontuacaoUtil.aplicarBonus(
    pontuacaoBase,
    sessao.getUsuarioAtual().getNome(),
    desafiosRespondidosPorUsuario
);


        String nomeAluno = sessao.getUsuarioAtual().getNome();
        desafiosRespondidosPorUsuario.put(nomeAluno,
                desafiosRespondidosPorUsuario.getOrDefault(nomeAluno, 0) + 1);

        Set<Conquista> conquistasDoUsuario = conquistasPorUsuario.getOrDefault(nomeAluno, new HashSet<>());
        List<Conquista> novasConquistas = AvaliadorConquistas.avaliarConquistas(
        nomeAluno,
        pontuacaoFinal,
        acertos,
        desafioSelecionado.getPerguntas().size(),
        gerenciadorConquistas,
        desafiosRespondidosPorUsuario,
        conquistasPorUsuario,
        conquistasDoUsuario
        );


        conquistasPorUsuario.put(nomeAluno, conquistasDoUsuario);

        HistoricoDeConquistas historicoConquistas = historicosConquistasPorUsuario
                .computeIfAbsent(nomeAluno, k -> new HistoricoDeConquistas());
        for (Conquista conquista : novasConquistas) {
            historicoConquistas.registrar(conquista);
        }

        HistoricoDeComandos historico = historicosPorUsuario
                .computeIfAbsent(nomeAluno, k -> new HistoricoDeComandos());
        ResponderDesafioCommand comando = new ResponderDesafioCommand(
                desafioSelecionado,
                pontuacaoFinal,
                sessao);
        historico.executarComando(comando);

        System.out.println("Você acertou " + acertos + " de " + desafioSelecionado.getPerguntas().size());
        System.out.println("Pontuação final: " + pontuacaoFinal);
    }

    private int aplicarBonusPontuacao(int pontuacaoBase, String nomeUsuario,
                                      Map<String, Integer> desafiosRespondidos) {
        int pontuacao = pontuacaoBase;
        int totalDesafios = desafiosRespondidos.getOrDefault(nomeUsuario, 0);
        if (totalDesafios % 3 == 0 && totalDesafios > 0) {
            pontuacao = (int) (pontuacao * 1.20);
            System.out.println("Bônus de streak aplicado!");
        }
        if (pontuacaoBase > 50) {
            pontuacao *= 2;
            System.out.println("Double XP ativado!");
        }
        return pontuacao;
    }
}


