package br.com.fiap.SmartCash.FluxoDeCaixa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FluxoCaixaRepository extends JpaRepository<FluxoCaixa, Long>{

    @Query(value = "SELECT * FROM TB_FLUXO_CAIXA A " +
            "WHERE A.DATA_INCLUSAO >= TRUNC(TO_DATE(SUBSTR(:dataInclusao, 1, 2) || '-' || SUBSTR(:dataInclusao, 4), 'MM/YYYY')) " +
            "AND A.DATA_INCLUSAO < TRUNC(TO_DATE(SUBSTR(:dataInclusao, 1, 2) || '-' || SUBSTR(:dataInclusao, 4), 'MM/YYYY') + INTERVAL '1' MONTH) " +
            "AND A.ID_EMPRESA = :idEmpresa",
            nativeQuery = true)
    Page<FluxoCaixa> findByDataInclusao(String dataInclusao, Long idEmpresa, Pageable pageable);
}
