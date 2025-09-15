package app;

import usuarios.*;
import infra.Sessao;
import java.util.Scanner;

public class MainConsole {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        IUsuarioRepositorio repositorio = new UsuarioRepositorioMemoria();
        Sessao sessao = Sessao.getInstancia();
        UsuarioService usuarioService = new UsuarioService(repositorio, sessao);

        int opcao;
        do {
            limparTela();
            System.out.println("\n=== Plataforma Gamificada ===");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Login");
            System.out.println("3 - Mostrar usuário logado");
            System.out.println("4 - Logout");
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
