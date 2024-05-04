package br.com.fiap.SmartCash.Empresa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    @Query(value = "SELECT A.* FROM " +
            "TB_EMPRESA A, " +
            "TB_USUARIO_EMPRESA B, " +
            "TB_USUARIO C " +
            "WHERE A.ID_EMPRESA = B.EMPRESA_ID_EMPRESA AND " +
            "C.ID_USUARIO = B.USUARIO_ID_USUARIO AND " +
            "C.ID_USUARIO = ?1", nativeQuery = true)
    List<Empresa> getEmpresasUsuarioTemAcesso(Long idUsuario);
}
