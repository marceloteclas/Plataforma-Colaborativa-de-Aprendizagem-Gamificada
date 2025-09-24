package conquistas_reestruturadas;

import desafios.PontuacaoDoubleXP;
import desafios.PontuacaoPorDificuldade;
import desafios.PontuacaoStrategy;
import desafios.PontuacaoStreak;

public class ConquistaTest {
    public static void main(String[] args) {
    // Teste 1
       
        System.out.println("--- Teste 1: Conquistas com Composite ---");
        System.out.println("");

        MedalhaSimples medalha = new MedalhaSimples("Primeiro Desafio", "Completou o primeiro desafio!");
        Nivel nivel = new Nivel(1, "Iniciante");

        // Composite
        ConquistaComposite grupo = new ConquistaComposite("Conquistas Iniciais", "Conquistas básicas");
        grupo.adicionar(medalha);
        grupo.adicionar(nivel);

        System.out.println(medalha);
        System.out.println(nivel);
        System.out.println(grupo);

    // Teste 2
        System.out.println("");
        System.out.println("");
        System.out.println("--- Teste 2: Hierarquia de Medalhas com Composite ---");
        System.out.println("");

        MedalhaSimples medalha1 = new MedalhaSimples("Primeiro Desafio", "Completou o primeiro desafio");
        MedalhaSimples medalha2 = new MedalhaSimples("5 Desafios", "Concluiu 5 desafios");
        MedalhaSimples medalha3 = new MedalhaSimples("10 Desafios", "Concluiu 10 desafios");

        MedalhaComposite iniciante = new MedalhaComposite("Medalhas de Iniciante", "Primeiros passos");
        iniciante.adicionar(medalha1);
        iniciante.adicionar(medalha2);

        MedalhaComposite avancado = new MedalhaComposite("Medalhas Avançadas", "Desafios mais difíceis");
        avancado.adicionar(medalha3);

        MedalhaComposite todas = new MedalhaComposite("Todas as Medalhas", "Hierarquia completa");
        todas.adicionar(iniciante);
        todas.adicionar(avancado);

        System.out.println(todas);
    }
}
