package controller;

import service.DesafioService;

import java.util.Scanner;

public class DesafioController {
    private final DesafioService desafioService;
    private final Scanner scanner;

    public DesafioController(DesafioService desafioService, Scanner scanner) {
        this.desafioService = desafioService;
        this.scanner = scanner;
    }

    public void criarDesafio() {
        System.out.print("Título do desafio: ");
        String titulo = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Tipo (quiz/codigo): ");
        String tipoDesafio = scanner.nextLine();
        System.out.print("Escolha a estratégia de pontuação (1-tempo, 2-dificuldade): ");
        String estrategiaStr = scanner.nextLine();

        var estrategia = estrategiaStr.equals("1")
                ? new desafios.PontuacaoPorTempo()
                : new desafios.PontuacaoPorDificuldade();

        var perguntas = new java.util.ArrayList<String>();
        var respostas = new java.util.ArrayList<String>();
        String continuar;

        do {
            System.out.print("Digite a pergunta: ");
            perguntas.add(scanner.nextLine());

            System.out.print("Digite a resposta correta: ");
            respostas.add(scanner.nextLine());

            System.out.print("Deseja adicionar outra pergunta? (s/n): ");
            continuar = scanner.nextLine().trim().toLowerCase();
        } while (continuar.equals("s"));

        System.out.print("Nível de dificuldade (1 a 5): ");
        int nivelDificuldade = Integer.parseInt(scanner.nextLine());

        desafioService.criarDesafio(titulo, descricao, tipoDesafio, perguntas, respostas, nivelDificuldade, estrategia);

        System.out.println("Desafio criado com sucesso!");
    }
    public void responderDesafio() {
    desafioService.responderDesafio(scanner);
}

}
