package model;

public class Professor extends Usuarios {
    public Professor(String nome, String senha) {
        super(nome, senha);
    }

    @Override
    public String getTipo() {
        return "Professor";
    }

    @Override
    public String exportarDados() {
        return "Nome: " + nome + ", Tipo: " + getTipo();
    }
}
