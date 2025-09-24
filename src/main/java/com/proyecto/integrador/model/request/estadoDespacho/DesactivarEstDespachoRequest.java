package com.proyecto.integrador.model.request.estadoDespacho;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesactivarEstDespachoRequest {
    private Integer idEstDespacho;
    private Boolean activar;
}
