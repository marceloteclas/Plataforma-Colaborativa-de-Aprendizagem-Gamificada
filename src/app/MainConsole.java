package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import conquistas_reestruturadas.Conquista;
import conquistas_reestruturadas.ConquistaDesafio1;
import conquistas_reestruturadas.ConquistaPontuacaoAlta;
import conquistas_reestruturadas.ConquistaX10;
import conquistas_reestruturadas.ConquistaX15;
import conquistas_reestruturadas.ConquistaX5;
import conquistas_reestruturadas.GerenciadorConquistas;
import conquistas_reestruturadas.MedalhaSimples;
import desafios.Desafio;
import desafios.PontuacaoStrategy;
import historico.HistoricoDeComandos;
import historico.HistoricoDeConquistas;
import historico.ResponderDesafioCommand;
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
        Map<String, HistoricoDeComandos> historicosPorUsuario = new HashMap<>();
        GerenciadorConquistas gerenciadorConquistas = new GerenciadorConquistas();
        Map<String, Integer> desafiosRespondidosPorUsuario = new HashMap<>();
        Map<String, Set<Conquista>> conquistasPorUsuario = new HashMap<>();
        Map<String, HistoricoDeConquistas> historicosConquistasPorUsuario = new HashMap<>();

        gerenciadorConquistas.adicionarObservador((usuario, conquista) -> {
            System.out.println("🏆 " + usuario + " conquistou: "
                    + conquista.getNome() + " - " + conquista.getDescricao());
        });

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
            System.out.println("7 - Desfazer última ação");
            System.out.println("8 - Ver histórico de ações");
            System.out.println("9 - Ver histórico de conquistas");
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
                    if (sessao.getUsuarioAtual() == null
                            || !sessao.getUsuarioAtual().getTipo().equalsIgnoreCase("Aluno")) {
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
                    int escolha;
                    try {
                        escolha = Integer.parseInt(scanner.nextLine()) - 1;
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
                        System.out.println("Pergunta " + (i + 1) + ": " + desafioSelecionado.getPerguntas().get(i));
                        String resp = scanner.nextLine();
                        if (resp.equalsIgnoreCase(desafioSelecionado.getRespostas().get(i))) {
                            acertos++;
                        }
                    }

                    long tempoFim = System.currentTimeMillis();
                    long tempoGasto = (tempoFim - tempoInicio) / 1000;

                    int pontuacaoBase = desafioSelecionado.getEstrategiaPontuacao()
                            .calcularPontuacao(desafioSelecionado, acertos, tempoGasto);

                    int pontuacaoFinal = aplicarBonusPontuacao(
                            pontuacaoBase,
                            sessao.getUsuarioAtual().getNome(),
                            desafiosRespondidosPorUsuario);

                    String nomeAluno = sessao.getUsuarioAtual().getNome();
                    desafiosRespondidosPorUsuario.put(nomeAluno,
                            desafiosRespondidosPorUsuario.getOrDefault(nomeAluno, 0) + 1);

                    Set<Conquista> conquistasDoUsuario = conquistasPorUsuario.getOrDefault(nomeAluno, new HashSet<>());
                    List<Conquista> novasConquistas = avaliarConquistas(
                            nomeAluno,
                            pontuacaoFinal,
                            acertos,
                            desafioSelecionado.getPerguntas().size(),
                            gerenciadorConquistas,
                            desafiosRespondidosPorUsuario,
                            conquistasPorUsuario,
                            conquistasDoUsuario);

                    conquistasPorUsuario.put(nomeAluno, conquistasDoUsuario);

                    HistoricoDeConquistas historicoConquistas = historicosConquistasPorUsuario
                            .computeIfAbsent(nomeAluno, k -> new HistoricoDeConquistas());
                    for (Conquista conquista : novasConquistas) {
                        historicoConquistas.registrar(conquista);
                    }

                    HistoricoDeComandos historico = historicosPorUsuario
                            .computeIfAbsent(nomeAluno, k -> new HistoricoDeComandos());
                    ResponderDesafioCommand comando = new ResponderDesafioCommand(
                            desafioSelecionado,
                            pontuacaoFinal,
                            sessao);
                    historico.executarComando(comando);

                    System.out.println("Você acertou " + acertos + " de " + desafioSelecionado.getPerguntas().size());
                    System.out.println("Pontuação final: " + pontuacaoFinal);
                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
                    break;

                case 7:
                    limparTela();
                    if (sessao.getUsuarioAtual() != null) {
                        String nomeUsuarioAtual = sessao.getUsuarioAtual().getNome();
                        HistoricoDeComandos historicoAtual = historicosPorUsuario.get(nomeUsuarioAtual);
                        if (historicoAtual != null) {
                            historicoAtual.desfazerUltimo();
                        } else {
                            System.out.println("Nenhuma ação registrada para este usuário.");
                        }
                    } else {
                        System.out.println("Faça login para desfazer ações.");
                    }
                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
                    break;

                case 8:
                    limparTela();
                    if (sessao.getUsuarioAtual() == null) {
                        System.out.println("Você precisa estar logado para ver o histórico.");
                        System.out.println("\nPressione Enter para continuar...");
                        scanner.nextLine();
                        break;
                    }

                    String nomeUsuario2 = sessao.getUsuarioAtual().getNome();
                    HistoricoDeComandos historico2 = historicosPorUsuario.get(nomeUsuario2);

                    if (historico2 != null) {
                        historico2.mostrarHistorico();
                    } else {
                        System.out.println("Nenhuma ação registrada para este usuário.");
                    }

                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
                    break;
                case 9:
                    limparTela();
                    if (sessao.getUsuarioAtual() == null) {
                        System.out.println("Você precisa estar logado para ver o histórico de conquistas.");
                        System.out.println("\nPressione Enter para continuar...");
                        scanner.nextLine();
                        break;
                    }

                    String nomeUsuario3 = sessao.getUsuarioAtual().getNome();
                    HistoricoDeConquistas historicoConquistasUsuario = historicosConquistasPorUsuario
                            .get(nomeUsuario3);

                    if (historicoConquistasUsuario != null) {
                        historicoConquistasUsuario.mostrarHistorico();
                    } else {
                        System.out.println("Nenhuma conquista desbloqueada ainda.");
                    }

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

    private static int aplicarBonusPontuacao(int pontuacaoBase, String nomeUsuario,
            Map<String, Integer> desafiosRespondidos) {
        int pontuacao = pontuacaoBase;
        int totalDesafios = desafiosRespondidos.getOrDefault(nomeUsuario, 0);
        if (totalDesafios % 3 == 0 && totalDesafios > 0) {
            pontuacao = (int) (pontuacao * 1.20);
            System.out.println("Bônus de streak aplicado!");
        }
        if (pontuacaoBase > 50) {
            pontuacao *= 2;
            System.out.println("Double XP ativado!");
        }
        return pontuacao;
    }

    public static List<Conquista> avaliarConquistas(
            String nomeUsuario,
            int pontuacaoFinal,
            int acertos,
            int totalPerguntas,
            GerenciadorConquistas gerenciador,
            Map<String, Integer> desafiosRespondidosPorUsuario,
            Map<String, Set<Conquista>> conquistasPorUsuarioGlobal,
            Set<Conquista> conquistasDoUsuario) {
        int totalRespondidos = desafiosRespondidosPorUsuario.getOrDefault(nomeUsuario, 0);
        List<Conquista> novasConquistas = new ArrayList<>();

        if (totalRespondidos == 1 && !jaPossuiConquista(conquistasDoUsuario, "Desafio 1")) {
            novasConquistas.add(new ConquistaDesafio1());
        }
        if (totalRespondidos == 5 && !jaPossuiConquista(conquistasDoUsuario, "Desafio X5")) {
            novasConquistas.add(new ConquistaX5());
        }
        if (totalRespondidos == 10 && !jaPossuiConquista(conquistasDoUsuario, "Desafio X10")) {
            novasConquistas.add(new ConquistaX10());
        }
        if (totalRespondidos == 15 && !jaPossuiConquista(conquistasDoUsuario, "Desafio X15")) {
            novasConquistas.add(new ConquistaX15());
        }

        // Conquista por pontuação alta
        if (pontuacaoFinal > 100 && !jaPossuiConquista(conquistasDoUsuario, "Pontuação Alta")) {
            novasConquistas.add(new ConquistaPontuacaoAlta());
        }

        if (acertos == totalPerguntas && totalPerguntas > 0 &&
                !jaPossuiConquista(conquistasDoUsuario, "Desafio Perfeito")) {
            novasConquistas.add(new MedalhaSimples("Desafio Perfeito", "Acertou todas as perguntas!"));
        }

        conquistasDoUsuario.addAll(novasConquistas);

        conquistasPorUsuarioGlobal.put(nomeUsuario, conquistasDoUsuario);

        for (Conquista conquista : novasConquistas) {
            gerenciador.registrarConquista(nomeUsuario, conquista);
        }

        return novasConquistas;
    }

    private static boolean jaPossuiConquista(Set<Conquista> conquistas, String nome) {
        for (Conquista c : conquistas) {
            if (c.getNome().equals(nome))
                return true;
        }
        return false;
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
