package com.proyecto.integrador.model.request.estadoDocumento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrarEstDocumentoRequest {
    private Integer idEstDocumento;
    private String descripcion;
}
