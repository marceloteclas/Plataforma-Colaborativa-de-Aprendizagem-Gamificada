package conquistas_reestruturadas;

import java.util.ArrayList;
import java.util.List;

public class ConquistaRepositorioMemoria {
    private List<Conquista> conquistas = new ArrayList<>();

    public void salvar(Conquista conquista) {
        conquistas.add(conquista);
    }

    public List<Conquista> listarTodas() {
        return conquistas;
    }
}