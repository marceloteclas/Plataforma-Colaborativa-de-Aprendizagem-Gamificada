package conquistas_reestruturadas;

public class MedalhaSimples implements Conquista {
    private String nome;
    private String descricao;

    public MedalhaSimples(String descricao) {
        this.nome = "Medalha Simples";
        this.descricao = descricao;
    }
    
    public MedalhaSimples(String nome, String descricao) {
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
        MedalhaSimples medalha = (MedalhaSimples) o;
        return nome.equals(medalha.nome) && descricao.equals(medalha.descricao);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(nome, descricao);
    }


    @Override
    public String toString() {
        return getNome() + " - " + getDescricao();
    }
}
