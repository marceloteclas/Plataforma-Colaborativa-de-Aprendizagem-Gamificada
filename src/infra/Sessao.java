package infra;

import usuarios.Usuarios;

/**
 * Singleton: controla a sessão global do usuário logado
 */
public class Sessao {
    private static Sessao instancia;
    private Usuarios usuarioAtual;

    private Sessao() {
    }

    public static Sessao getInstancia() {
        if (instancia == null) {
            instancia = new Sessao();
        }
        return instancia;
    }

    public void login(Usuarios usuario) {
        this.usuarioAtual = usuario;
    }

    public void logout() {
        this.usuarioAtual = null;
    }

    public Usuarios getUsuarioAtual() {
        return usuarioAtual;
    }
}
