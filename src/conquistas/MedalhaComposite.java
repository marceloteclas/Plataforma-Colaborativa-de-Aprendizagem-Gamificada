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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Categoria: ").append(nome).append("] ").append(descricao).append("\n");
        for (Conquista c : conquistas) {
            sb.append("  -> ").append(c.toString()).append("\n");
        }
        return sb.toString();
    }
}
