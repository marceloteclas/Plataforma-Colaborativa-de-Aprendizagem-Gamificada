package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.List;

import model.Usuarios;

public class UsuarioRepositorioMemoria implements IUsuarioRepositorio {
    private Map<String, Usuarios> usuarios = new HashMap<>();

    @Override
    public void salvar(Usuarios usuario) {
        usuarios.put(usuario.getNome(), usuario);
    }

    @Override
    public Optional<Usuarios> buscarPorNome(String nome) {
        return Optional.ofNullable(usuarios.get(nome));
    }
    @Override
    public List<Usuarios> getAll() {
        return new ArrayList<>(usuarios.values()); 
}
}