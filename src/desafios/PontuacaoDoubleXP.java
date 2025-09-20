package desafios;

public class PontuacaoDoubleXP extends PontuacaoDecorator {
    public PontuacaoDoubleXP(PontuacaoStrategy estrategiaBase) {
        super(estrategiaBase);
    }

    @Override
    public int calcularPontuacao(Desafio desafio, int acertos, long tempoRespostaSegundos) {
        int base = super.calcularPontuacao(desafio, acertos, tempoRespostaSegundos);
        return base * 2;
    }
}