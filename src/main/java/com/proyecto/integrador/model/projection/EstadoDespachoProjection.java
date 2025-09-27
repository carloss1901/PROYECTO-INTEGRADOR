package com.proyecto.integrador.model.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
    "idEstadoDespacho", "descripcion", "activo", "estadoDsc", "fecCreacion", "fecModificacion"
})
public interface EstadoDespachoProjection {
    Integer getIdEstadoDespacho();
    String getDescripcion();
    Boolean getActivo();
    String getEstadoDsc();
    String getFecCreacion();
    String getFecModificacion();
}
