package com.proyecto.integrador.model.request.estadoDespacho;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrarEstDespachoRequest {
    private Integer idEstDespacho;
    private String descripcion;
}
