package model;

public class Visitante extends Usuarios {
    public Visitante(String nome, String senha) {
        super(nome, senha);
    }

    @Override
    public String getTipo() {
        return "Visitante";
    }
}
