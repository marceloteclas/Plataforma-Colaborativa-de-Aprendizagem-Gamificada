package model;

public class UsuarioFactory {

    public static Usuarios criarUsuario(String tipo, String nome, String senha) {
        switch (tipo.toLowerCase()) {
            case "aluno":
                return new Aluno(nome, senha);
            case "professor":
                return new Professor(nome, senha);
            case "visitante":
                return new Visitante(nome, senha);
            default:
                throw new IllegalArgumentException("Tipo de usuário inválido: " + tipo);
        }
    }
}
