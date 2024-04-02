package br.com.fiap.SmartCash.Assinatura;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "TB_ASSINATURA")

public class Assinatura {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID_ASSINATURA;

    @NotBlank @Column(unique =true) @Size(max = 15)
    private String TIPO;

    @NotBlank @Positive
    private BigDecimal VALOR;

}
