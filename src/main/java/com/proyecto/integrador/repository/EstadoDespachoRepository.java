package com.proyecto.integrador.repository;

import com.proyecto.integrador.model.entity.EstadoDespachoEntity;
import com.proyecto.integrador.model.projection.EstadoDespachoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoDespachoRepository extends JpaRepository<EstadoDespachoEntity, Integer> {

    @Query(value = """
        SELECT COALESCE(MAX(ID_ESTADO_DESPACHO), 0) + 1
        FROM ESTADO_DESPACHO
        """, nativeQuery = true)
    Integer getIdCorrelativo();

    @Query(value = """
        SELECT
            ID_ESTADO_DESPACHO	                        AS idEstadoDespacho,
            DESCRIPCION,
            ACTIVO,
            DATE_FORMAT(FECHA_CREACION, '%d/%m/%Y')	    AS fecCreacion,
            DATE_FORMAT(FECHA_MODIFICACION, '%d/%m/%Y') AS fecModificacion
        FROM ESTADO_DESPACHO
        """,  nativeQuery = true)
    Page<EstadoDespachoProjection> listarEstDespacho(
        @PageableDefault(page = 0, size = 10) Pageable pageable
    );

    @Query(value = """
        SELECT
            ID_ESTADO_DESPACHO	                        AS idEstadoDespacho,
            DESCRIPCION,
            ACTIVO,
            DATE_FORMAT(FECHA_CREACION, '%d/%m/%Y')	    AS fecCreacion,
            DATE_FORMAT(FECHA_MODIFICACION, '%d/%m/%Y') AS fecModificacion
        FROM ESTADO_DESPACHO
        WHERE ID_ESTADO_DESPACHO = :idEstDespacho
        """, nativeQuery = true)
    EstadoDespachoProjection obtenerEstDespacho(@Param("idEstDespacho") Integer idEstDespacho);
}
