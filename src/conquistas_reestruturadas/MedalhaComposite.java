package conquistas_reestruturadas;

import java.util.ArrayList;
import java.util.List;

public class MedalhaComposite implements Conquista {
    private String nome;
    private String descricao;
    private List<Conquista> medalhas = new ArrayList<>();

    public MedalhaComposite(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public void adicionar(Conquista medalha) {
        medalhas.add(medalha);
    }

    public void remover(Conquista medalha) {
        medalhas.remove(medalha);
    }

    public List<Conquista> getMedalhas() {
        return medalhas;
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
        for (Conquista m : medalhas) {
            sb.append("  -> ").append(m.toString()).append("\n");
        }
        return sb.toString();
    }
}
