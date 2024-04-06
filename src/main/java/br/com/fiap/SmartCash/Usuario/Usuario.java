package br.com.fiap.SmartCash.Usuario;

import br.com.fiap.SmartCash.Usuario.validation.CPFOrCNPJ;
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

    @NotBlank  @Size(min = 1,max = 100)
    private String NOME;

    @NotBlank @CPFOrCNPJ
    private String DOCUMENTO;

    @NotBlank @Size(min=8, max=8)
    private String SENHA;

    private String LOGIN_USUARIO; 
    
    //FK 
    private Long ID_EMPRESA; 
}
