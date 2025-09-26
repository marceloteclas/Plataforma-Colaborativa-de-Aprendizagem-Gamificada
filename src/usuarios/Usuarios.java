package usuarios;

public abstract class Usuarios {
    protected String nome;
    private String senha;
    private int pontuacao = 0;

    public Usuarios(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }
    public int getPontuacao() {
        return pontuacao;
    }

    public void adicionarPontuacao(int pontos) {
        this.pontuacao += pontos;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public abstract String getTipo();

    public String exportarDados() {
        return "Nome: " + nome + ", Tipo: " + getTipo() + ", Pontuação: " + pontuacao;
    }
}
