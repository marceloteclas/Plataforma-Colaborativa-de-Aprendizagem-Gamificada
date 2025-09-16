package desafios;

public class PontuacaoPorDificuldade implements PontuacaoStrategy {
    @Override
    public int calcularPontuacao(Desafio desafio, int acertos, long tempoRespostaSegundos) {
        // Cada acerto vale dificuldade * 10 pontos
        return acertos * desafio.getNivelDificuldade() * 10;
    }
}
