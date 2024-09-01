package br.com.fiap.SmartCash.Usuario.dto;

import br.com.fiap.SmartCash.Usuario.Usuario;

public record UsuarioResponse (String nome, String documento, String email ) {

    public static UsuarioResponse from(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getNOME(),
                usuario.getDOCUMENTO(),
                usuario.getEMAIL());
    }

}
