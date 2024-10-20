package br.com.fiap.SmartCash.Email;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Email {

    private String destinatario;

    private String assunto;

    private String conteudo;
}
