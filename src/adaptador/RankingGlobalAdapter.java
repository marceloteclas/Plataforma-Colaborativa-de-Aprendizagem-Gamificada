package adaptador;

import api_externa.RankingGlobalAPI;
import java.util.Arrays;
import java.util.List;

public class RankingGlobalAdapter {
    private final RankingGlobalAPI apiExterna;

    public RankingGlobalAdapter(RankingGlobalAPI apiExterna) {
        this.apiExterna = apiExterna;
    }

    public List<String> getRankingComoLista() {
        // Converte o String[] da API em List<String>
        return Arrays.asList(apiExterna.getTopPlayers());
    }
}
