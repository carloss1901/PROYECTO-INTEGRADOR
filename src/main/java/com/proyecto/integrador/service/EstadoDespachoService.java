package com.proyecto.integrador.service;

import com.proyecto.integrador.model.projection.EstadoDespachoProjection;
import com.proyecto.integrador.model.request.estadoDespacho.DesactivarEstDespachoRequest;
import com.proyecto.integrador.model.request.estadoDespacho.RegistrarEstDespachoRequest;
import com.proyecto.integrador.util.CustomPage;
import org.springframework.http.ResponseEntity;

public interface EstadoDespachoService {
    CustomPage<EstadoDespachoProjection> listarEstDespacho(Integer page, Integer size);
    EstadoDespachoProjection obtenerEstDespachoPorId(Integer idEstDespacho);
    ResponseEntity<Object> registrarEstDespacho(RegistrarEstDespachoRequest request);
    ResponseEntity<Object> desactivarEstDespacho(DesactivarEstDespachoRequest request);
}
