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
    public String toString() {
        return "Medalha: " + nome + " - " + descricao;
    }
}
