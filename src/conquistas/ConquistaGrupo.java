package conquistas;

import java.util.ArrayList;
import java.util.List;

public class ConquistaGrupo implements Conquista {
    private String nome;
    private String descricao;
    private List<Conquista> conquistas = new ArrayList<>();

    public ConquistaGrupo(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public void adicionar(Conquista conquista) {
        conquistas.add(conquista);
    }

    public List<Conquista> getConquistas() {
        return conquistas;
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
        return "Grupo: " + nome + " -> " + conquistas.toString();
    }
}