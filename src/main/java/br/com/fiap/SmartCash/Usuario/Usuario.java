package br.com.fiap.SmartCash.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "TB_USUARIO")
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID_USUARIO;

    @NotBlank  @Size(min = 1,max = 100)
    private String NOME;

    @NotBlank @Column(unique = true)
    @Pattern(regexp = "(\\d{2}\\.?\\d{3}\\.?\\d{3}\\/?\\d{4}-?\\d{2})|(\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2})", 
            message = "{usuario.cpf-cpnj.invalido}") 
    private String DOCUMENTO;

    @NotBlank @Size(min=8, max=8)
    private String SENHA;

    private String LOGIN_USUARIO; 
    
    //FK 
    private Long ID_EMPRESA; 
}
