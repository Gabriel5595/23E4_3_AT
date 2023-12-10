package org.example.service;

import org.example.dto.UsuarioDTOInput;
import org.example.dto.UsuarioDTOOutput;
import org.example.model.Usuario;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    private static List<Usuario> listaUsuarios = new ArrayList<>();
    private static ModelMapper modelMapper = new ModelMapper();

    public List<UsuarioDTOOutput> listar() {
        List<UsuarioDTOOutput> usuariosDTO = new ArrayList<>();
        for (Usuario usuario : listaUsuarios) {
            usuariosDTO.add(modelMapper.map(usuario, UsuarioDTOOutput.class));
        }
        return usuariosDTO;
    }

    public void inserir(UsuarioDTOInput usuarioDTOInput) {
        Usuario usuario = modelMapper.map(usuarioDTOInput, Usuario.class);
        listaUsuarios.add(usuario);
    }

    public void alterar(UsuarioDTOInput usuarioDTOInput) {
        Usuario usuario = modelMapper.map(usuarioDTOInput, Usuario.class);
        listaUsuarios.removeIf(u -> u.getId() == usuario.getId());
        listaUsuarios.add(usuario);
    }

    public static UsuarioDTOOutput buscar(int id) {
        Usuario usuario = listaUsuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);

        return (usuario != null) ? modelMapper.map(usuario, UsuarioDTOOutput.class) : null;
    }

    public void excluir(int id) {
        listaUsuarios.removeIf(u -> u.getId() == id);
    }

}
