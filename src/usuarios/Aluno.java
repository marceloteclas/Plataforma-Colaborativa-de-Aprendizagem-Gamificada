package usuarios;

public class Aluno extends Usuarios {
    public Aluno(String nome, String senha) {
        super(nome, senha);
    }

    @Override
    public String getTipo() {
        return "Aluno";
    }

}
