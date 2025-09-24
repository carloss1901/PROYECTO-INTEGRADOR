package com.proyecto.integrador.model.request.estadoDocumento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesactivarEstDocumentoRequest {
    private Integer idEstDocumento;
    private Boolean activar;
}
