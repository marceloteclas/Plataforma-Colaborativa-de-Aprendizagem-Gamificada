package relatorios;

import java.util.ArrayList;
import java.util.List;

import usuarios.UsuarioService;

public class RelatorioSistema {
    private final UsuarioService service;

    public RelatorioSistema (UsuarioService service) {
        this.service = service;
    }

    public List<String> listarUsuarios() {
        List<String> lista = new ArrayList<String>(); 

        lista.add(" Usuários Cadastrados: \n");

        for (String l : service.exportarLista()){
            lista.add(l);
        }

        return lista;
    } 
}
