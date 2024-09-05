package br.com.fiap.SmartCash.Assinatura;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.math.BigDecimal;

import jakarta.persistence.*;
import org.springframework.hateoas.EntityModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "TB_ASSINATURA")
@Builder
@NoArgsConstructor
@AllArgsConstructor 

public class Assinatura {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ASSINATURA_SEQ")
    @SequenceGenerator(name = "ASSINATURA_SEQ", sequenceName = "TB_ASSINATURA_SEQ", allocationSize = 1)
    private Long ID_ASSINATURA;

    @NotBlank @Column(unique =true) @Size(max = 15)
    private String TIPO;

    @Positive
    private BigDecimal VALOR;

    public EntityModel<Assinatura> toEntityModel() {
        return EntityModel.of(
                this,
                linkTo(methodOn(AssinaturaController.class).readItem(ID_ASSINATURA)).withSelfRel(),
                linkTo(methodOn(AssinaturaController.class).delete(ID_ASSINATURA)).withRel("delete"),
                linkTo(methodOn(AssinaturaController.class).readAll(null)).withRel("contents")
            );
    }    
}
