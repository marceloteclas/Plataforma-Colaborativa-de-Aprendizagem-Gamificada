package repository;

import java.util.Optional;
import java.util.List;
import model.Usuarios;

public interface IUsuarioRepositorio {
    void salvar(Usuarios usuario);

    Optional<Usuarios> buscarPorNome(String nome);

    List<Usuarios> getAll();
}
