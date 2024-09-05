package br.com.fiap.SmartCash.RegistroAssinatura;

import java.time.LocalDate;

import br.com.fiap.SmartCash.Assinatura.Assinatura;
import br.com.fiap.SmartCash.Empresa.Empresa;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "TB_ASSINATURA_EMPRESA")
@Builder
@NoArgsConstructor
@AllArgsConstructor 

public class RegistroAssinatura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ASSINATURA_EMPRESA_SEQ")
    @SequenceGenerator(name = "ASSINATURA_EMPRESA_SEQ", sequenceName = "TB_ASSINATURA_EMPRESA_SEQ", allocationSize = 1)
    private Long ID_ASSINATURA_EMPRESA;
        
    @Builder.Default
    private LocalDate DATA_AQUISICAO = LocalDate.now();

    @Builder.Default
    private LocalDate DATA_VENCIMENTO = LocalDate.now().plusMonths(3);

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "ID_ASSINATURA")
    private Assinatura assinatura;

    @Size(max=10)
    private String TOKEN_EMPRESA;

}
