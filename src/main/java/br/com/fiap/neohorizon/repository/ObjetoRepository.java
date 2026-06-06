package br.com.fiap.neohorizon.repository;

import br.com.fiap.neohorizon.model.SpaceObjeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ObjetoRepository extends JpaRepository<SpaceObjeto, Long> {

    @Query(
        """
        SELECT o FROM SpaceObjeto o
        join o.tags t where t = :tag
        """
    )
    List<SpaceObjeto> findByTag(@Param("tag") String tag);
}
