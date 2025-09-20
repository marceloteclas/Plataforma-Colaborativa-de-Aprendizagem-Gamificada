package conquistas;

public class Nivel implements Conquista {
    private int nivel;
    private String descricao;

    public Nivel(int nivel, String descricao) {
        this.nivel = nivel;
        this.descricao = descricao;
    }

    @Override
    public String getNome() {
        return "Nível " + nivel;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "Nível: " + nivel + " - " + descricao;
    }
}