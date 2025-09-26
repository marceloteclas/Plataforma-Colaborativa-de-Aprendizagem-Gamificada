package relatorios;

import java.io.FileOutputStream;
import java.util.List;

import org.openpdf.text.*;
import org.openpdf.text.pdf.PdfWriter;

public class RelatorioPDF implements RelatorioStrategy {
    
    @Override
    public void gerarRelatorio(List<String> dados, String caminhoArquivo) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(caminhoArquivo));
            document.open();

            // Título
            Font tituloFont = new Font(Font.HELVETICA, 16, Font.BOLD);
            document.add(new Paragraph("Relatório de Desempenho", tituloFont));
            document.add(new Paragraph(" ")); // espaço em branco

            // Linhas de dados
            for (String linha : dados) {
                document.add(new Paragraph(linha));
            }

            document.close();
            System.out.println("PDF gerado em: " + caminhoArquivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}