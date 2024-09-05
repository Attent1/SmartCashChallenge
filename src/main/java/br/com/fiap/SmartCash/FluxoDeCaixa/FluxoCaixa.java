package br.com.fiap.SmartCash.FluxoDeCaixa;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import org.springframework.hateoas.EntityModel;

import br.com.fiap.SmartCash.Empresa.Empresa;
import br.com.fiap.SmartCash.FluxoDeCaixa.validation.Tipo;
import br.com.fiap.SmartCash.Usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "TB_FLUXO_CAIXA")
@Builder
@NoArgsConstructor
@AllArgsConstructor 
public class FluxoCaixa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FLUXO_CAIXA_SEQ")
    @SequenceGenerator(name = "FLUXO_CAIXA_SEQ", sequenceName = "TB_FLUXO_CAIXA_SEQ", allocationSize = 1)
    private Long ID_FLUXO;

    @Tipo
    private String TIPO;

    @Positive
    private BigDecimal VALOR;

    @NotBlank @Size(min = 3, max = 250)
    private String DESCRICAO;
    
    @Builder.Default
    private LocalDate DATA_INCLUSAO = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    public EntityModel<FluxoCaixa> toEntityModel() {
        return EntityModel.of(
                this,
                linkTo(methodOn(FluxoCaixaController.class).readItem(ID_FLUXO)).withSelfRel(),
                linkTo(methodOn(FluxoCaixaController.class).delete(ID_FLUXO)).withRel("delete"),
                linkTo(methodOn(FluxoCaixaController.class).readAll(null)).withRel("contents")
            );
    }    
    
}
