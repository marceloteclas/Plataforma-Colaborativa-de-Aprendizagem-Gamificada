package controller;

import service.UsuarioService;
import relatorios.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import infra.Sessao;
import model.Aluno;

public class RelatorioController {

    private final RelatorioSistema relatorioSistema;
    private final Scanner scanner;

    private RelatorioFacade gerador = new RelatorioFacade();
    private final Sessao sessao;
    private int opcao;

    public RelatorioController(RelatorioSistema relatorioSistema, Scanner scanner, Sessao sessao) {
        this.relatorioSistema = relatorioSistema;
        this.scanner = scanner;
        this.sessao = sessao;
        this.opcao = -1;
    }

    public void exibirMenuRelatorios() {
        do {
            System.out.println("\n=== Menu de Relatórios ===");
            System.out.println("1 - Informações do usuário logado");
            System.out.println("2 - Relatório de usuários cadastrados");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());
            List<String> dados = new ArrayList<>();

            switch (opcao) {
                case 1:
                    if (sessao.getUsuarioAtual() == null) {
                        System.out.println("Nenhum usuário está logado.");
                    } else {
                        System.out.println("Escolha o formato do arquivo (CSV/JSON/PDF):");
                        String tipo = scanner.nextLine();
                        System.out.println("Digite o nome do arquivo: ");
                        String nome = scanner.nextLine();
                        if (sessao.getUsuarioAtual().getTipo().equalsIgnoreCase("Aluno")){
                            dados = ((Aluno) sessao.getUsuarioAtual()).exportarDadosComConquistas();
                        }
                        else {
                            dados.add(sessao.getUsuarioAtual().exportarDados());
                        }
                        gerador.gerar(tipo, dados, nome);
                    }
                    break;

                case 2:
                    if (sessao.getUsuarioAtual() == null) {
                        System.out.println("Nenhum usuário está logado.");
                    } else if (!sessao.getUsuarioAtual().getTipo().equalsIgnoreCase("Professor")) {
                        System.out.println("Apenas professores podem acessar esta função.");
                    } else {
                        System.out.println("Escolha o formato do arquivo (CSV/JSON/PDF):");
                        String tipo = scanner.nextLine();
                        System.out.println("Digite o nome do arquivo: ");
                        String nome = scanner.nextLine();
                        dados = relatorioSistema.listarUsuarios();
                        gerador.gerar(tipo, dados, nome);
                    }
                    break;

                case 0:
                    System.out.println("Retornando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
}
