package com.proyecto.integrador.repository;

import com.proyecto.integrador.model.entity.EstadoDespachoEntity;
import com.proyecto.integrador.model.entity.EstadoDocumentoEntity;
import com.proyecto.integrador.model.projection.EstadoDocumentoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoDocumentoRepository extends JpaRepository<EstadoDocumentoEntity, Integer> {
    @Query(value = """
            SELECT
                ID_ESTADO_DOCUMENTO                         AS idEstadoDocumento,
                DESCRIPCION,
                ACTIVO,
                DATE_FORMAT(FECHA_CREACION, '%d/%m/%Y')     AS fecCreacion,
                DATE_FORMAT(FECHA_MODIFICACION, '%d/%m/%Y') AS fecModificacion
            FROM ESTADO_DOCUMENTO
            """, nativeQuery = true)
    Page<EstadoDocumentoProjection> listarEstDocumento(
        @PageableDefault(page = 0, size = 10) Pageable pageable
    );

    @Query(value = """
        SELECT
            ID_ESTADO_DOCUMENTO                         AS idEstadoDocumento,
            DESCRIPCION,
            ACTIVO,
            DATE_FORMAT(FECHA_CREACION, '%d/%m/%Y')     AS fecCreacion,
            DATE_FORMAT(FECHA_MODIFICACION, '%d/%m/%Y') AS fecModificacion
        FROM ESTADO_DOCUMENTO
        WHERE ID_ESTADO_DOCUMENTO = :idEstDocumento
        """, nativeQuery = true)
    EstadoDocumentoProjection obtenerEstDocumentoPorId(@Param("idEstDocumento") Integer idEstDocumento);
}
