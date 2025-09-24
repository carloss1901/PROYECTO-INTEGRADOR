package com.proyecto.integrador.model.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
    "idEstadoDespacho", "descripcion", "activo", "fecCreacion", "fecModificacion"
})
public interface EstadoDespachoProjection {
    Integer getIdEstadoDespacho();
    String getDescripcion();
    Boolean getActivo();
    String getFecCreacion();
    String getFecModificacion();
}
