package relatorios;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RelatorioCSV implements RelatorioStrategy {
    
    @Override
    public void gerarRelatorio(List<String> dados, String caminhoArquivo) {
        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            for (String linha : dados) {
                writer.write(linha + "\n");
            }
            System.out.println("Relatório CSV gerado em: " + caminhoArquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
