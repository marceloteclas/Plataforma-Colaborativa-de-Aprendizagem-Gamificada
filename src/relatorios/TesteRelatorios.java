package relatorios;

import java.util.Arrays;

public class TesteRelatorios {
    public static void main(String[] args) {
        RelatorioFacade facade = new RelatorioFacade();
        var dados = Arrays.asList("Aluno: João - 1200 pontos", "Aluno: Maria - 980 pontos", "Aluno: Ana - 750 pontos");

        // Exportar CSV
        facade.exportar("CSV", dados, "relatorio.csv");

        // Exportar JSON
        facade.exportar("JSON", dados, "relatorio.json");

        // Exportar PDF 
        facade.exportar("PDF", dados, "relatorio.pdf");
    }
}
