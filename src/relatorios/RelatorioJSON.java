package relatorios;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RelatorioJSON implements RelatorioStrategy {
    
    @Override
    public void gerarRelatorio(List<String> dados, String caminhoArquivo) {
        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            writer.write("{\n  \"relatorio\": [\n");
            for (int i = 0; i < dados.size(); i++) {
                writer.write("    \"" + dados.get(i) + "\"");
                if (i < dados.size() - 1) writer.write(",");
                writer.write("\n");
            }
            writer.write("  ]\n}");
            System.out.println("Relatório JSON gerado em: " + caminhoArquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}