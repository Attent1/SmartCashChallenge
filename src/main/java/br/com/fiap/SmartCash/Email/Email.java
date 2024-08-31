package br.com.fiap.SmartCash.Email;

import lombok.Data;

@Data
public class Email {

    private String destinatario;

    private String assunto;

    private String conteudo;
}
