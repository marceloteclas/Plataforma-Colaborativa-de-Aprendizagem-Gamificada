package service;

import java.util.List;
import java.util.ArrayList;
import infra.Sessao;
import model.UsuarioFactory;
import model.Usuarios;
import repository.IUsuarioRepositorio;

import java.util.Optional;

/**
 * Serviço responsável por lógica de usuários
 * Padrões aplicados: Singleton (sessão), Factory Method (criação de usuários)
 */
public class UsuarioService {
    private final IUsuarioRepositorio repositorio;
    private final Sessao sessao;

    public UsuarioService(IUsuarioRepositorio repositorio, Sessao sessao) {
        this.repositorio = repositorio;
        this.sessao = sessao;
    }

    public void cadastrarUsuario(String nome, String tipo, String senha) {
        // Validações
        if (nome == null || nome.isBlank()) {
            System.out.println("Nome de usuário não pode ser vazio!");
            return;
        }
        if (senha == null || senha.length() < 3) {
            System.out.println("Senha deve ter pelo menos 3 caracteres!");
            return;
        }
        if (repositorio.buscarPorNome(nome).isPresent()) {
            System.out.println("Usuário já existe! Escolha outro nome.");
            return;
        }

        Usuarios usuario = UsuarioFactory.criarUsuario(tipo, nome, senha);
        repositorio.salvar(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    public void login(String nome, String senha) {
        if (sessao.getUsuarioAtual() != null) {
            System.out.println("Já existe um usuário logado. Faça logout primeiro.");
            return;
        }

        Optional<Usuarios> usuario = repositorio.buscarPorNome(nome);
        if (usuario.isPresent()) {
            if (usuario.get().getSenha().equals(senha)) {
                sessao.login(usuario.get());
                System.out.println("Login realizado com sucesso! Bem-vindo, "
                        + usuario.get().getNome() + " (" + usuario.get().getTipo() + ")");
            } else {
                System.out.println("Senha incorreta!");
            }
        } else {
            System.out.println("Usuário não encontrado. Cadastre-se primeiro.");
        }
    }
    public List<String> exportarLista(){
        List<String> lista = new ArrayList<String>();

        for (Usuarios u : repositorio.getAll()) {
            lista.add(u.toString());
        }

        return lista;
    }

    public List<String> exportarLista(){
        List<String> lista = new ArrayList<String>();

        for (Usuarios u : repositorio.getAll()) {
            lista.add(u.toString());
        }

        return lista;
    }

    public void mostrarUsuarioLogado() {
        Usuarios usuario = sessao.getUsuarioAtual();
        if (usuario != null) {
            System.out.println("Usuário logado: " + usuario.getNome() + " (" + usuario.getTipo() + ")");
        } else {
            System.out.println("Nenhum usuário logado no momento.");
        }
    }

    public void logout() {
        sessao.logout();
        System.out.println("Logout realizado com sucesso.");
    }
}
