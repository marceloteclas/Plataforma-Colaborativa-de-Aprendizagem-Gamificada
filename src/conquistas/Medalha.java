package conquistas;

public class Medalha implements Conquista {
    private String nome;
    private String descricao;

    public Medalha(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medalha medalha = (Medalha) o;
        return nome.equals(medalha.nome) && descricao.equals(medalha.descricao);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(nome, descricao);
    }


    @Override
    public String toString() {
        return "Medalha: " + nome + " - " + descricao;
    }
}
