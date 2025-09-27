package relatorios;

import java.util.ArrayList;
import java.util.List;

import adaptador.RankingGlobalAdapter;
import api_externa.RankingGlobalAPI;

public class TesteRelatorios {
    public static void main(String[] args) {
        RelatorioFacade facade = new RelatorioFacade();
        
        List<String> dados = new ArrayList<>();
        dados.add("Aluno: João - 1200 pontos");
        dados.add("Aluno: Maria - 980 pontos");
        dados.add("Aluno: Ana - 750 pontos");

        // Exportar CSV
        facade.gerar("CSV", dados, "relatorio.csv");

        // Exportar JSON
        facade.gerar("JSON", dados, "relatorio.json");

        // Exportar PDF 
        facade.gerar("PDF", dados, "relatorio.pdf");

        // Exportar Relatório Interno
        RankingGlobalAPI api = new RankingGlobalAPI();
        RankingGlobalAdapter adapter = new RankingGlobalAdapter(api);
        var dadosExternos = adapter.getRankingComoLista();

        dados.add("\n ---- Ranking Global ---- \n");
        dados.addAll(dadosExternos);
        facade.gerar("PDF", dados, "relatorio_completo.pdf");
    }
}
