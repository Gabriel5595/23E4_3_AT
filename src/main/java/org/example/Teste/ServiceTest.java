package org.example.Teste;
import org.example.dto.UsuarioDTOInput;
import org.example.service.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {

    @Test
    public void testInsercaoUsuario() {
        UsuarioService usuarioService = new UsuarioService();
        UsuarioDTOInput usuarioDTOInput = new UsuarioDTOInput();
        usuarioDTOInput.setId(1);
        usuarioDTOInput.setNome("Teste");
        usuarioDTOInput.setSenha("123456");

        usuarioService.inserir(usuarioDTOInput);

        System.out.println(usuarioService.listar());

        Assertions.assertEquals(1, usuarioService.listar().size());
    }
}