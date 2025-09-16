package historico;

import desafios.Desafio;
import infra.Sessao;

public class ResponderDesafioCommand implements Comando {
    private Desafio desafio;
    private int pontuacaoAnterior;
    private int novaPontuacao;
    private Sessao sessao;

    public ResponderDesafioCommand(Desafio desafio, int novaPontuacao, Sessao sessao) {
        this.desafio = desafio;
        this.novaPontuacao = novaPontuacao;
        this.sessao = sessao;
    }

    @Override
    public void executar() {
        pontuacaoAnterior = sessao.getUsuarioAtual().getPontuacao();
        sessao.getUsuarioAtual().adicionarPontuacao(novaPontuacao);
        System.out.println("Desafio respondido. Pontuação adicionada.");
    }

    @Override
    public void desfazer() {
        sessao.getUsuarioAtual().setPontuacao(pontuacaoAnterior);
        System.out.println("Pontuação revertida.");
    }
}
