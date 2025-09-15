package usuarios;

public class Professor extends Usuarios {
    public Professor(String nome, String senha) {
        super(nome, senha);
    }

    @Override
    public String getTipo() {
        return "Professor";
    }
}
