package br.com.fiap.SmartCash.UsuarioEmpresa;

import br.com.fiap.SmartCash.Empresa.Empresa;
import br.com.fiap.SmartCash.Usuario.Usuario;
import jakarta.persistence.*;
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
    @JoinColumn(name = "ID_EMPRESA")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;
}
