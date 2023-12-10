package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDTOInput {
    @JsonProperty("value")
    private int id;
    @JsonProperty("first")
    private String nome;
    @JsonProperty("password")
    private String senha;
}
