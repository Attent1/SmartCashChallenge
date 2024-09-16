package br.com.fiap.SmartCash.UsuarioEmpresa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioEmpresaRepository extends JpaRepository<UsuarioEmpresa, Long>{
    @Query(value = "SELECT A.* FROM TB_USUARIO_EMPRESA A " +
            "JOIN TB_USUARIO B ON A.ID_USUARIO = B.ID_USUARIO " +
            "WHERE B.EMAIL = ?1",
            nativeQuery = true)
    List<UsuarioEmpresa> getUsuarioEmpresaByUsuarioEmail(String email);
}
