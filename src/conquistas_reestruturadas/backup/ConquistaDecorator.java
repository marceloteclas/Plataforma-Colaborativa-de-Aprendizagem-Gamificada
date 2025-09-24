/* package conquistas;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        ConquistaDecorator other = (ConquistaDecorator) o;
        return conquista.equals(other.conquista);
    }

    @Override
    public int hashCode() {
        return conquista.hashCode();
    }

    @Override
    public String toString() {
        return getNome() + " - " + getDescricao();
    }
} 
*/