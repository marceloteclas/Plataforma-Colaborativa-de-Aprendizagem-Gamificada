package conquistas;

import java.util.ArrayList;
import java.util.List;

public class MedalhaComposite implements Conquista {
    private String nome;
    private String descricao;
    private List<Conquista> conquistas = new ArrayList<>();

    public MedalhaComposite(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public void adicionar(Conquista conquista) {
        conquistas.add(conquista);
    }

    public void remover(Conquista conquista) {
        conquistas.remove(conquista);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedalhaComposite that = (MedalhaComposite) o;
        return nome.equals(that.nome) && descricao.equals(that.descricao);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(nome, descricao);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Categoria: ").append(nome).append("] ").append(descricao).append("\n");
        for (Conquista c : conquistas) {
            sb.append("  -> ").append(c.toString()).append("\n");
        }
        return sb.toString();
    }
}
