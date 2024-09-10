package br.com.fiap.SmartCash.RegistroAssinatura;

import br.com.fiap.SmartCash.Empresa.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegistroAssinaturaRepository extends JpaRepository<RegistroAssinatura, Long>{
    @Query(value = "SELECT TOKEN_EMPRESA FROM TB_ASSINATURA_EMPRESA A " +
                   "WHERE A.ID_EMPRESA = (SELECT AA.ID_EMPRESA " +
                   "FROM TB_EMPRESA AA " +
                   "WHERE AA.EMAIL = ?1)",
            nativeQuery = true)
    String getTokenByEmailEmpresa(String email);

    @Query(value = "SELECT A.* FROM TB_ASSINATURA_EMPRESA A " +
                   "WHERE A.TOKEN_EMPRESA = ?1",
            nativeQuery = true)
    RegistroAssinatura getRegistroAssinaturaByToken(String token);

}
