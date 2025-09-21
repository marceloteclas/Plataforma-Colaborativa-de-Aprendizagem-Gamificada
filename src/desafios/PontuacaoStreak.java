package desafios;

public class PontuacaoStreak extends PontuacaoDecorator {
    private int streakDias;

    public PontuacaoStreak(PontuacaoStrategy estrategiaBase, int streakDias) {
        super(estrategiaBase);
        this.streakDias = streakDias;
    }

    @Override
    public int calcularPontuacao(Desafio desafio, int acertos, long tempoRespostaSegundos) {
        int base = super.calcularPontuacao(desafio, acertos, tempoRespostaSegundos);
        return base + (streakDias * 5); 
    }
}
