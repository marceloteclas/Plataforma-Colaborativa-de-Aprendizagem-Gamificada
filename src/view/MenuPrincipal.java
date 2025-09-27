package view;

import java.util.Scanner;

public class MenuPrincipal {
    private Scanner scanner = new Scanner(System.in);

    public int exibirMenu() {
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
            System.out.println("10 - Relatórios");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
        return Integer.parseInt(scanner.nextLine());
    }

    
}
