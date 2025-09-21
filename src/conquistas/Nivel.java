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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nivel nivelObj = (Nivel) o;
        return nivel == nivelObj.nivel && descricao.equals(nivelObj.descricao);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(nivel, descricao);
    }
    @Override
    public String toString() {
        return "Nível: " + nivel + " - " + descricao;
    }
}