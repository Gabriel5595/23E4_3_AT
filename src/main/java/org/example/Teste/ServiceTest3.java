package org.example.Teste;
import org.example.controller.UsuarioController;
import org.example.dto.UsuarioDTOInput;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceTest3 {
    @Test
    public void testInsercaoUsuario() throws IOException {
        UsuarioController usuarioController = new UsuarioController();

        UsuarioDTOInput usuarioDTOInput = obterUsuarioAleatorioDaAPI();

        URL url = new URL("http://localhost:4567/usuario");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInputString = objectMapper.writeValueAsString(usuarioDTOInput);

        connection.getOutputStream().write(jsonInputString.getBytes());
        connection.getOutputStream().flush();
        connection.getOutputStream().close();

        int responseCode1 = connection.getResponseCode();
        assertEquals(201, responseCode1);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder responseStringBuilder = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            responseStringBuilder.append(inputLine);
        }
        in.close();

        String respostaDaAPI = responseStringBuilder.toString();
        System.out.println("Resposta da API: " + respostaDaAPI);
    }

    private UsuarioDTOInput obterUsuarioAleatorioDaAPI() throws IOException {
        URL url = new URL("https://randomuser.me/api/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        assertEquals(200, responseCode);

        ObjectMapper objectMapper = new ObjectMapper();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        UsuarioDTOInput usuarioDTOInput = objectMapper.readValue(in, UsuarioDTOInput.class);

        in.close();
        return usuarioDTOInput;
    }
}
