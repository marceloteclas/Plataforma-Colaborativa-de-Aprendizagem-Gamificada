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
}
