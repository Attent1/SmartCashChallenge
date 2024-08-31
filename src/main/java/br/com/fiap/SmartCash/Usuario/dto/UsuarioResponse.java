package br.com.fiap.SmartCash.Usuario.dto;

import br.com.fiap.SmartCash.Usuario.Usuario;

public record UsuarioResponse (String nome, String documento, String loginUsuario ) {

    public static UsuarioResponse from(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getNOME(),
                usuario.getDOCUMENTO(),
                usuario.getLOGIN_USUARIO());
    }

}