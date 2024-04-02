package br.com.fiap.SmartCash.Usuario;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "TB_USUARIO")
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID_USUARIO;

    @NotBlank @Column(unique = true) @Size(min = 3,max = 100)
    private String NOME;

    @NotBlank @CPF @CNPJ
    private String DOCUMENTO;

    @NotBlank @Size(min=8, max=8)
    private String SENHA;

    private String LOGIN_USUARIO = NOME.charAt(0) + DOCUMENTO.length() == 10 ? DOCUMENTO.substring(7) : DOCUMENTO.substring(9); 

    //FK 
    private Long ID_EMPRESA; 
}
