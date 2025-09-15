package usuarios;

import java.util.Optional;

public interface IUsuarioRepositorio {
    void salvar(Usuarios usuario);

    Optional<Usuarios> buscarPorNome(String nome);
}
