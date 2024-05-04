package br.com.fiap.SmartCash.UsuarioEmpresa;

import br.com.fiap.SmartCash.Empresa.Empresa;
import br.com.fiap.SmartCash.Usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "TB_USUARIO_EMPRESA")
@Builder
@NoArgsConstructor
@AllArgsConstructor 

public class UsuarioEmpresa {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID_USUARIO_EMPRESA;

    @ManyToOne
    private Empresa empresa;

    @ManyToOne
    private Usuario usuario;
}
