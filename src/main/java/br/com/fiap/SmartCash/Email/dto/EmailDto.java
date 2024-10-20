package br.com.fiap.SmartCash.Email.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailDto {
    private String destinatario;
    private String nome;
}
