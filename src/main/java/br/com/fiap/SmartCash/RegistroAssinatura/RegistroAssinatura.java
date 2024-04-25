package br.com.fiap.SmartCash.RegistroAssinatura;

import java.time.LocalDate;

import br.com.fiap.SmartCash.Assinatura.Assinatura;
import br.com.fiap.SmartCash.Empresa.Empresa;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "TB_ASSINATURA_EMPRESA")

public class RegistroAssinatura {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID_ASSINATURA_EMPRESA;

    private LocalDate DATA_AQUISICAO = LocalDate.now();

    private LocalDate DATA_VENCIMENTO = LocalDate.now().plusMonths(3);

    @ManyToOne
    private Empresa empresa;

    @ManyToOne
    private Assinatura assinatura;

    private String TOKEN_EMPRESA;

}
