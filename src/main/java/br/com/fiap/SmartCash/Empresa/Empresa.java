package br.com.fiap.SmartCash.Empresa;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "TB_EMPRESA")
@Builder
@NoArgsConstructor
@AllArgsConstructor 
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPRESA_SEQ")
    @SequenceGenerator(name = "EMPRESA_SEQ", sequenceName = "TB_EMPRESA_SEQ", allocationSize = 1)
    private Long ID_EMPRESA;

    @NotBlank @Column(unique = true) @Size(min = 3,max = 100)
    private String NOME;

    @CNPJ
    private String CNPJ;

    @Email
    private String EMAIL;

    @NotBlank @Size(min = 3,max = 50)
    private String RAMO;

}
