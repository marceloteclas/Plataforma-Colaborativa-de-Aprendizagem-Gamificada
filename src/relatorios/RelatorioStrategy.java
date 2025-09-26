package relatorios;

import java.util.List;

public interface RelatorioStrategy {
    void gerarRelatorio(List<String> dados, String caminhoArquivo);
}
