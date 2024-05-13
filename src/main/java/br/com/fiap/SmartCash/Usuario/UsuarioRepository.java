package br.com.fiap.SmartCash.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    @Query(value = "SELECT * FROM TB_USUARIO A WHERE A.LOGIN_USUARIO = :loginUsuario", nativeQuery = true)
    Usuario LoginUsuario(@Param("loginUsuario") String loginUsuario);
}
