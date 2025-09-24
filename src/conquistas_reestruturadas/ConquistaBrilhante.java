package conquistas_reestruturadas;

public class ConquistaBrilhante extends ConquistaSimples {
    private String nome;
    private String descricao;

    public ConquistaBrilhante(String nome, String descricao) {
        super(nome, descricao);
    }

    @Override
    public String getNome() {
        return "+ " + nome + " +";
    }

    @Override
    public String getDescricao() {
        return descricao + " (destaque especial)";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ConquistaBrilhante that = (ConquistaBrilhante) obj;
        return nome.equals(that.nome) && descricao.equals(that.descricao);
    }

    @Override
    public int hashCode() {
        return this.hashCode();
    }

}
