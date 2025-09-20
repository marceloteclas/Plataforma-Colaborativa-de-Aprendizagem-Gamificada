package desafios;

public abstract class PontuacaoDecorator implements PontuacaoStrategy {
    protected PontuacaoStrategy estrategiaBase;

    public PontuacaoDecorator(PontuacaoStrategy estrategiaBase) {
        this.estrategiaBase = estrategiaBase;
    }

    @Override
    public int calcularPontuacao(Desafio desafio, int acertos, long tempoRespostaSegundos) {
        return estrategiaBase.calcularPontuacao(desafio, acertos, tempoRespostaSegundos);
    }
}