package conquistas;

import desafios.PontuacaoDoubleXP;
import desafios.PontuacaoPorDificuldade;
import desafios.PontuacaoStrategy;
import desafios.PontuacaoStreak;

public class ConquistaTest {
    public static void main(String[] args) {
    // Teste 1
       
        System.out.println("--- Teste 1: Conquistas com Composite e Decorator ---");
        System.out.println("");

        Medalha medalha = new Medalha("Primeiro Desafio", "Completou o primeiro desafio!");
        Nivel nivel = new Nivel(1, "Iniciante");

        // Composite
        ConquistaGrupo grupo = new ConquistaGrupo("Conquistas Iniciais", "Conquistas básicas");
        grupo.adicionar(medalha);
        grupo.adicionar(nivel);

        // Decorator
        Conquista medalhaEspecial = new ConquistaBrilhante(medalha);

        System.out.println(medalha);
        System.out.println(nivel);
        System.out.println(grupo);
        System.out.println(medalhaEspecial);

    // Teste 2
        System.out.println("");
        System.out.println("");
        System.out.println("--- Teste 2: Hierarquia de Medalhas com Composite ---");
        System.out.println("");

        Medalha medalha1 = new Medalha("Primeiro Desafio", "Completou o primeiro desafio");
        Medalha medalha2 = new Medalha("5 Desafios", "Concluiu 5 desafios");
        Medalha medalha3 = new Medalha("10 Desafios", "Concluiu 10 desafios");

        MedalhaComposite iniciante = new MedalhaComposite("Medalhas de Iniciante", "Primeiros passos");
        iniciante.adicionar(medalha1);
        iniciante.adicionar(medalha2);

        MedalhaComposite avancado = new MedalhaComposite("Medalhas Avançadas", "Desafios mais difíceis");
        avancado.adicionar(medalha3);

        MedalhaComposite todas = new MedalhaComposite("Todas Medalhas", "Hierarquia completa");
        todas.adicionar(iniciante);
        todas.adicionar(avancado);

        System.out.println(todas);
    }
}
