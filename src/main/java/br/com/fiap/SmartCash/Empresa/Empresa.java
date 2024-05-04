package br.com.fiap.SmartCash.Empresa;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID_EMPRESA;

    @NotBlank @Column(unique = true) @Size(min = 3,max = 100)
    private String NOME;

    @CNPJ
    private String CNPJ;

    @NotBlank @Size(min = 3,max = 50)
    private String RAMO;

}
