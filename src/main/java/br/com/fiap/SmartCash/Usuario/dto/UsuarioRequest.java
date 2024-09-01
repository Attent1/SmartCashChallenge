package br.com.fiap.SmartCash.Usuario.dto;

import br.com.fiap.SmartCash.Usuario.Usuario;

public record UsuarioRequest(String nome, String documento, String email, String senha) {

    public Usuario toModel(){
        return Usuario.builder()
                .NOME(nome)
                .DOCUMENTO(documento)
                .EMAIL(email)
                .SENHA(senha)
                .build();
    }

}
