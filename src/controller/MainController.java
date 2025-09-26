package controller;

import java.util.HashMap;
import java.util.Map;

public class MainController {
    private final Map<Integer, Runnable> acoes = new HashMap<>();

    public void registrarAcao(int codigo, Runnable acao) {
        acoes.put(codigo, acao);
    }

    public void executar(int opcao) {
        Runnable acao = acoes.get(opcao);
        if (acao != null) {
            acao.run();
        } else {
            System.out.println("Opção inválida.");
        }
    }
}
