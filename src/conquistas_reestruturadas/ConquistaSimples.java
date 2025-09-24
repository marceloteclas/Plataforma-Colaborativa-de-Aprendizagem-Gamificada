package conquistas_reestruturadas;

public class ConquistaSimples implements Conquista {
    private String nome;
    private String descricao;

    public ConquistaSimples(String nome, String descricao) {
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
        return "Conquista: " + nome + " - " + descricao;
    }
}
