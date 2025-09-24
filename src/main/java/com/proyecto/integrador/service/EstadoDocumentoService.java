package com.proyecto.integrador.service;

import com.proyecto.integrador.model.projection.EstadoDocumentoProjection;
import com.proyecto.integrador.model.request.estadoDocumento.DesactivarEstDocumentoRequest;
import com.proyecto.integrador.model.request.estadoDocumento.RegistrarEstDocumentoRequest;
import com.proyecto.integrador.util.CustomPage;
import org.springframework.http.ResponseEntity;

public interface EstadoDocumentoService {
    CustomPage<EstadoDocumentoProjection> listarEstDocumento(Integer page, Integer size);
    EstadoDocumentoProjection obtenerEstDocumento(Integer idEstDocumento);
    ResponseEntity<Object> registrarEstDocumento(RegistrarEstDocumentoRequest request);
    ResponseEntity<Object> desactivarEstDocumento(DesactivarEstDocumentoRequest request);
}
