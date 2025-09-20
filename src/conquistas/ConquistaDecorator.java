package conquistas;

public abstract class ConquistaDecorator implements Conquista {
    protected Conquista conquista;

    public ConquistaDecorator(Conquista conquista) {
        this.conquista = conquista;
    }

    @Override
    public String getNome() {
        return conquista.getNome();
    }

    @Override
    public String getDescricao() {
        return conquista.getDescricao();
    }

    @Override
    public String toString() {
        return getNome() + " - " + getDescricao();
    }
}