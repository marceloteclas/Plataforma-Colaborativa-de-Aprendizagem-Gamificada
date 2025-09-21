package conquistas;

public class ConquistaBrilhante extends ConquistaDecorator {
    public ConquistaBrilhante(Conquista conquista) {
        super(conquista);
    }

    @Override
    public String getNome() {
        return "+ " + conquista.getNome() + " +";
    }

    @Override
    public String getDescricao() {
        return conquista.getDescricao() + " (destaque especial)";
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

}
