package desafios;

import java.util.ArrayList;
import java.util.List;

public class DesafioRepositorioMemoria {
    private List<Desafio> desafios = new ArrayList<>();

    public void salvar(Desafio desafio) {
        desafios.add(desafio);
    }

    public List<Desafio> listarTodos() {
        return desafios;
    }
}