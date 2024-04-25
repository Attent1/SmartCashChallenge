package br.com.fiap.SmartCash.FluxoDeCaixa;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.fiap.SmartCash.Empresa.Empresa;
import br.com.fiap.SmartCash.FluxoDeCaixa.validation.Tipo;
import br.com.fiap.SmartCash.Usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "TB_FLUXO_CAIXA")

public class FluxoCaixa {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID_FLUXO;

    @Tipo
    private String TIPO;

    @Positive
    private BigDecimal VALOR;

    @NotBlank @Size(min = 3, max = 250)
    private String DESCRICAO;
    
    private LocalDate DATA_INCLUSAO = LocalDate.now();

    @ManyToOne
    private Empresa empresa;

    @ManyToOne
    private Usuario usuario;

    
}
