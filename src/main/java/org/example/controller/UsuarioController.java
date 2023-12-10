package org.example.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.UsuarioDTOInput;
import org.example.service.UsuarioService;

import static spark.Spark.*;

public class UsuarioController {
    private UsuarioService usuarioService = new UsuarioService();
    private ObjectMapper objectMapper = new ObjectMapper();

    public UsuarioController() {
        get("/usuarios", (request, response) -> {
            response.type("application/json");
            response.status(200);
            return objectMapper.writeValueAsString(usuarioService.listar());
        });

        get("/usuario/:id", (request, response) -> {
            response.type("application/json");
            String idParam = request.params("id");
            int id = Integer.parseInt(idParam);
            String json = objectMapper.writeValueAsString(UsuarioService.buscar(id));
            response.status(200);
            return json;
        });

        post("/usuario", (request, response) -> {
            UsuarioDTOInput usuarioDTOInput = objectMapper.readValue(request.body(), UsuarioDTOInput.class);
            usuarioService.inserir(usuarioDTOInput);
            response.type("application/json");
            response.status(201);
            return "Produto inserido com sucesso.";
        });

        put("/usuario", (request, response) -> {
            UsuarioDTOInput produtoDTOInput = objectMapper.readValue(request.body(), UsuarioDTOInput.class);
            usuarioService.inserir(produtoDTOInput);
            response.type("application/json");
            response.status(200);
            return "Produto alterado com sucesso.";
        });

        delete("/usuario/:id", (request, response) -> {
            response.type("application/json");
            String idParam = request.params("id");
            int id = Integer.parseInt(idParam);
            usuarioService.excluir(id);
            response.status(200);
            return "Produto excluido com sucesso.";
        });
    }
}
