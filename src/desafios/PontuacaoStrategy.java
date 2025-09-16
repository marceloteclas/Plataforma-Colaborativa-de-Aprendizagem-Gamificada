package desafios;

public interface PontuacaoStrategy {
    int calcularPontuacao(Desafio desafio, int acertos, long tempoRespostaSegundos);
}
