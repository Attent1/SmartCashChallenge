package br.com.fiap.SmartCash.FluxoDeCaixa;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.hateoas.EntityModel;

import br.com.fiap.SmartCash.Assinatura.AssinaturaController;
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

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
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
    private Empresa empresa;

    @ManyToOne
    private Usuario usuario;

    public EntityModel<FluxoCaixa> toEntityModel() {
        return EntityModel.of(
                this,
                linkTo(methodOn(AssinaturaController.class).readItem(ID_FLUXO)).withSelfRel(),
                linkTo(methodOn(AssinaturaController.class).delete(ID_FLUXO)).withRel("delete"),
                linkTo(methodOn(AssinaturaController.class).readAll(null)).withRel("contents")
            );
    }    
    
}
