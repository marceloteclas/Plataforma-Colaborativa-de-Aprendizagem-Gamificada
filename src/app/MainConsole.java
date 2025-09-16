package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import desafios.Desafio;
import desafios.PontuacaoStrategy;
import infra.Sessao;
import usuarios.IUsuarioRepositorio;
import usuarios.UsuarioRepositorioMemoria;
import usuarios.UsuarioService;

public class MainConsole {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        IUsuarioRepositorio repositorio = new UsuarioRepositorioMemoria();
        Sessao sessao = Sessao.getInstancia();
        UsuarioService usuarioService = new UsuarioService(repositorio, sessao);
        desafios.DesafioRepositorioMemoria desafioRepositorio = new desafios.DesafioRepositorioMemoria();
        int opcao;
        do {
            limparTela();
            System.out.println("\n=== Plataforma Gamificada ===");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Login");
            System.out.println("3 - Mostrar usuário logado");
            System.out.println("4 - Logout");
            System.out.println("5 - Criar desafio");
            System.out.println("6 - Responder desafio");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    limparTela();
                    System.out.print("Digite um nome de usuário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o tipo (aluno/professor/visitante): ");
                    String tipo = scanner.nextLine();
                    System.out.print("Digite a senha (min 3 caracteres): ");
                    String senha = scanner.nextLine();
                    usuarioService.cadastrarUsuario(nome, tipo, senha);
                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
                    break;

                case 2:
                    limparTela();
                    System.out.print("Digite seu nome para login: ");
                    String nomeLogin = scanner.nextLine();
                    System.out.print("Digite a senha: ");
                    String senhaLogin = scanner.nextLine();
                    usuarioService.login(nomeLogin, senhaLogin);
                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
                    break;

                case 3:
                    limparTela();
                    usuarioService.mostrarUsuarioLogado();
                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
                    break;

                case 4:
                    limparTela();
                    usuarioService.logout();
                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
                    break;
                case 5:
                    limparTela();
                    if (sessao.getUsuarioAtual() == null ||
                            !sessao.getUsuarioAtual().getTipo().equalsIgnoreCase("Professor")) {
                        System.out.println("Apenas professores podem criar desafios.");
                        System.out.println("\nPressione Enter para continuar...");
                        scanner.nextLine();
                        break;
                    }
                    System.out.print("Título do desafio: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Tipo (quiz/codigo): ");
                    String tipoDesafio = scanner.nextLine();
                    System.out.print("Escolha a estratégia de pontuação (1-tempo, 2-dificuldade): ");
                    String estrategiaStr = scanner.nextLine();
                    PontuacaoStrategy estrategia;
                    if (estrategiaStr.equals("1")) {
                        estrategia = new desafios.PontuacaoPorTempo();
                    } else {
                        estrategia = new desafios.PontuacaoPorDificuldade();
                    }
                    List<String> perguntas = new ArrayList<>();
                    List<String> respostas = new ArrayList<>();
                    String continuar;

                    do {
                        System.out.print("Digite a pergunta: ");
                        String pergunta = scanner.nextLine();
                        perguntas.add(pergunta);

                        System.out.print("Digite a resposta correta: ");
                        String resposta = scanner.nextLine();
                        respostas.add(resposta);

                        System.out.print("Deseja adicionar outra pergunta? (s/n): ");
                        continuar = scanner.nextLine().trim().toLowerCase();
                    } while (continuar.equals("s"));

                    System.out.print("Nível de dificuldade (1 a 5): ");
                    int nivelDificuldade = Integer.parseInt(scanner.nextLine());

                    Desafio desafio = new desafios.Desafio(titulo, descricao, tipoDesafio, perguntas, respostas,
                            nivelDificuldade, estrategia);
                    
                    desafioRepositorio.salvar(desafio);
                    System.out.println("Desafio criado com sucesso!");
                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
                    break;
                case 6:
                    limparTela();
                    if (sessao.getUsuarioAtual() == null ||
                            !sessao.getUsuarioAtual().getTipo().equalsIgnoreCase("Aluno")) {
                        System.out.println("Apenas alunos podem responder desafios.");
                        System.out.println("\nPressione Enter para continuar...");
                        scanner.nextLine();
                        break;
                    }
                    List<Desafio> desafios = desafioRepositorio.listarTodos();
                    if (desafios.isEmpty()) {
                        System.out.println("Nenhum desafio disponível.");
                        System.out.println("\nPressione Enter para continuar...");
                        scanner.nextLine();
                        break;
                    }
                    System.out.println("Desafios disponíveis:");
                    for (int i = 0; i < desafios.size(); i++) {
                        System.out.println((i + 1) + " - " + desafios.get(i).getTitulo());
                    }
                    System.out.print("Escolha o número do desafio: ");
                    String escolhaStr = scanner.nextLine();
                    int escolha;
                    try {
                        escolha = Integer.parseInt(escolhaStr) - 1;
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida.");
                        System.out.println("\nPressione Enter para continuar...");
                        scanner.nextLine();
                        break;
                    }
                    if (escolha < 0 || escolha >= desafios.size()) {
                        System.out.println("Desafio inválido.");
                        System.out.println("\nPressione Enter para continuar...");
                        scanner.nextLine();
                        break;
                    }
                    Desafio desafioSelecionado = desafios.get(escolha);
                    long tempoInicio = System.currentTimeMillis();
                    int acertos = 0;
                    for (int i = 0; i < desafioSelecionado.getPerguntas().size(); i++) {
                        System.out.println("Pergunta: " + desafioSelecionado.getPerguntas().get(i));
                        System.out.print("Sua resposta: ");
                        String respostaAluno = scanner.nextLine();
                        if (respostaAluno.equalsIgnoreCase(desafioSelecionado.getRespostas().get(i))) {
                            acertos++;
                        }
                    }
                    long tempoFim = System.currentTimeMillis();
                    long tempoRespostaSegundos = (tempoFim - tempoInicio) / 1000;

                    int pontuacao = desafioSelecionado.getEstrategiaPontuacao()
                        .calcularPontuacao(desafioSelecionado, acertos, tempoRespostaSegundos);



                    System.out.println("Você acertou " + acertos + " de " + desafioSelecionado.getPerguntas().size()
                            + " perguntas.");
                    System.out.println("Sua pontuação: " + pontuacao);
                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
                    break;

                case 0:
                    limparTela();
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
            }

        } while (opcao != 0);
    }

    public static void limparTela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++)
                System.out.println();
        }
    }
}
