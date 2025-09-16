package desafios;

public class PontuacaoPorTempo implements PontuacaoStrategy {
    @Override
    public int calcularPontuacao(Desafio desafio, int acertos, long tempoRespostaSegundos) {
        // Base de 100 pontos, diminuída pelo tempo
        int base = (int) Math.max(0, 100 - tempoRespostaSegundos);
        // Pontuação proporcional ao número de acertos
        return (base * acertos) / desafio.getPerguntas().size();
    }
}
