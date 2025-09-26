package relatorios;

import java.util.List;

public class RelatorioFacade {
    private RelatorioStrategy relatorioStrategy;

    public void setRelatorioStrategy(RelatorioStrategy strategy) {
        this.relatorioStrategy = strategy;
    }

    public void exportar(String tipo, List<String> dados, String caminhoArquivo) {
        switch (tipo) {
            case "CSV":
                relatorioStrategy = new RelatorioCSV();
                break;
            case "JSON":
                relatorioStrategy = new RelatorioJSON();
                break;
            case "PDF":
                relatorioStrategy = new RelatorioPDF();
                break;
            case "PDF2":
                relatorioStrategy = new RelatorioPDF();
                break;
            default:
                throw new IllegalArgumentException("Tipo de relatório desconhecido: " + tipo);
        }
        
        if (relatorioStrategy == null) {
            throw new IllegalStateException("Nenhuma estratégia definida para exportar relatório!");
        }
        relatorioStrategy.gerarRelatorio(dados, caminhoArquivo);
    }
}