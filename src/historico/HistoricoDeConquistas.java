package historico;

import conquistas_reestruturadas.Conquista;
import java.util.ArrayList;
import java.util.List;

public class HistoricoDeConquistas {
    private List<Conquista> conquistas = new ArrayList<>();

    public void registrar(Conquista conquista) {
        conquistas.add(conquista);
    }

    public void mostrarHistorico() {
        if (conquistas.isEmpty()) {
            System.out.println("Nenhuma conquista desbloqueada ainda.");
        } else {
            System.out.println("Histórico de conquistas:");
            for (Conquista c : conquistas) {
                System.out.println("- " + c.getNome() + " (" + c.getDescricao() + ")");
            }
        }
    }
}
