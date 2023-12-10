package org.example.Teste;

import org.example.controller.UsuarioController;
import org.example.dto.UsuarioDTOInput;
import org.example.service.UsuarioService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class ServiceTest2 {
    @Test
    public void TesteInsercaoPorApi() throws IOException {
        UsuarioController usuarioController = new UsuarioController();

        UsuarioService usuarioService = new UsuarioService();
        UsuarioDTOInput usuarioDTOInput = new UsuarioDTOInput();

        usuarioDTOInput.setId(1);
        usuarioDTOInput.setNome("Teste");
        usuarioDTOInput.setSenha("123456");
        usuarioService.inserir(usuarioDTOInput);

        URL url = new URL("http://localhost:4567/usuarios");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        assertEquals(200, responseCode);
    }

}
