package desafios;

import java.util.List;

public class Desafio {
    private String titulo;
    private String descricao;
    private String tipo;
    private int nivelDificuldade;
    private List<String> perguntas;
    private List<String> respostas;

    public Desafio(String titulo, String descricao, String tipo, List<String> perguntas, List<String> respostas) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipo = tipo;
        this.perguntas = perguntas;
        this.respostas = respostas;
        this.nivelDificuldade = 1;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getNivelDificuldade() {
        return nivelDificuldade;
    }

    public List<String> getPerguntas() {
        return perguntas;
    }

    public List<String> getRespostas() {
        return respostas;
    }

    private PontuacaoStrategy estrategiaPontuacao;

    public Desafio(String titulo, String descricao, String tipo, List<String> perguntas, List<String> respostas,
            int nivelDificuldade, PontuacaoStrategy estrategiaPontuacao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipo = tipo;
        this.perguntas = perguntas;
        this.respostas = respostas;
        this.nivelDificuldade = nivelDificuldade;
        this.estrategiaPontuacao = estrategiaPontuacao;
    }

    public PontuacaoStrategy getEstrategiaPontuacao() {
        return estrategiaPontuacao;
    }
}
