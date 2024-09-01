package br.com.fiap.SmartCash.Usuario;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "TB_USUARIO")
@Builder
@NoArgsConstructor
@AllArgsConstructor 

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQ")
    @SequenceGenerator(name = "USUARIO_SEQ", sequenceName = "TB_USUARIO_SEQ", allocationSize = 1)
    private Long ID_USUARIO;

    @NotBlank  @Size(min = 1,max = 100)
    private String NOME;

    @NotBlank @Column(unique = true)
    @Pattern(regexp = "(\\d{2}\\.?\\d{3}\\.?\\d{3}\\/?\\d{4}-?\\d{2})|(\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2})", 
            message = "{usuario.cpf-cpnj.invalido}") 
    private String DOCUMENTO;

    @Email
    private String EMAIL;

    @NotBlank @Size(min=8, max=100)
    private String SENHA;
    
}
