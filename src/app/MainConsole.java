package app;

import conquistas_reestruturadas.*;
import controller.*;
import desafios.*;
import historico.*;
import infra.*;
import model.*;
import repository.*;
import relatorios.*;
import service.*;
import service.ConquistaService;
import view.MenuPrincipal;

import java.util.*;

public class MainConsole {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        IUsuarioRepositorio usuarioRepo = new UsuarioRepositorioMemoria();
        DesafioRepositorioMemoria desafioRepo = new DesafioRepositorioMemoria();
        Sessao sessao = Sessao.getInstancia();
        UsuarioService usuarioService = new UsuarioService(usuarioRepo, sessao);
        RelatorioSistema relatorioSistema = new RelatorioSistema(usuarioService);

        Map<String, HistoricoDeComandos> historicosPorUsuario = new HashMap<>();
        Map<String, Integer> desafiosRespondidosPorUsuario = new HashMap<>();
        Map<String, Set<Conquista>> conquistasPorUsuario = new HashMap<>();
        Map<String, HistoricoDeConquistas> historicosConquistasPorUsuario = new HashMap<>();
        GerenciadorConquistas gerenciadorConquistas = new GerenciadorConquistas();
        RelatorioController relatorioController = new RelatorioController(relatorioSistema, scanner, sessao);

        gerenciadorConquistas.adicionarObservador((usuario, conquista) -> {
            System.out.println("🏆 " + usuario + " conquistou: " + conquista.getNome() + " - " + conquista.getDescricao());
        });
        DesafioService desafioService = new DesafioService(
                desafioRepo, sessao, historicosPorUsuario,
                desafiosRespondidosPorUsuario, conquistasPorUsuario,
                gerenciadorConquistas, historicosConquistasPorUsuario
        );
        ConquistaService conquistaService = new ConquistaService(sessao, conquistasPorUsuario);
        DesafioController desafioController = new DesafioController(desafioService, scanner);
        HistoricoController historicoController = new HistoricoController(sessao, historicosPorUsuario, historicosConquistasPorUsuario);
        MainController mainController = new MainController();
        mainController.registrarAcao(1, () -> {
            System.out.print("Digite um nome de usuário: ");
            String nome = scanner.nextLine();
            System.out.print("Digite o tipo (aluno/professor/visitante): ");
            String tipo = scanner.nextLine();
            System.out.print("Digite a senha (min 3 caracteres): ");
            String senha = scanner.nextLine();
            usuarioService.cadastrarUsuario(nome, tipo, senha);
        });
        mainController.registrarAcao(2, () -> {
            System.out.print("Digite seu nome para login: ");
            String nome = scanner.nextLine();
            System.out.print("Digite a senha: ");
            String senha = scanner.nextLine();
            usuarioService.login(nome, senha);
        });

        mainController.registrarAcao(5, () -> {
            if (sessao.getUsuarioAtual() != null &&
                sessao.getUsuarioAtual().getTipo().equalsIgnoreCase("Professor")) {
                desafioController.criarDesafio();
            } else {
                System.out.println("Apenas professores podem criar desafios.");
            }
        });
        mainController.registrarAcao(6, () -> {
            if (sessao.getUsuarioAtual() != null &&
                sessao.getUsuarioAtual().getTipo().equalsIgnoreCase("Aluno")) {
                desafioController.responderDesafio();
            } else {
                System.out.println("Apenas alunos podem responder desafios.");
            }
        });
        mainController.registrarAcao(9, () -> conquistaService.listarConquistas());
        int opcao;
        do {
            limparTela();
            MenuPrincipal menu = new MenuPrincipal();
            opcao = menu.exibirMenu();
            limparTela();
            switch (opcao) {
                case 3 -> usuarioService.mostrarUsuarioLogado();
                case 4 -> usuarioService.logout();
                case 7 -> historicoController.desfazerUltimaAcao();
                case 8 -> historicoController.mostrarHistoricoDeAcoes();
                case 10 -> relatorioController.exibirMenuRelatorios();
                case 0 -> System.out.println("Encerrando...");
                default -> mainController.executar(opcao);
            }

            System.out.println("\nPressione Enter para continuar...");
            scanner.nextLine();
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
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }
}
