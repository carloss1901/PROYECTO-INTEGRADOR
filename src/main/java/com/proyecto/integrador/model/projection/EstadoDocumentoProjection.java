package com.proyecto.integrador.model.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
    "idEstadoDocumento", "descripcion", "activo", "fecCreacion", "fecModificacion"
})
public interface EstadoDocumentoProjection {
    Integer getIdEstadoDocumento();
    String getDescripcion();
    Boolean getActivo();
    String getFecCreacion();
    String getFecModificacion();
}
