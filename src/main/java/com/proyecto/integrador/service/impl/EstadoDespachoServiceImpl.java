package com.proyecto.integrador.service.impl;

import com.proyecto.integrador.config.ConfigProperty;
import com.proyecto.integrador.model.entity.EstadoDespachoEntity;
import com.proyecto.integrador.model.projection.EstadoDespachoProjection;
import com.proyecto.integrador.model.request.estadoDespacho.DesactivarEstDespachoRequest;
import com.proyecto.integrador.model.request.estadoDespacho.RegistrarEstDespachoRequest;
import com.proyecto.integrador.repository.EstadoDespachoRepository;
import com.proyecto.integrador.service.EstadoDespachoService;
import com.proyecto.integrador.util.CustomPage;
import com.proyecto.integrador.util.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class EstadoDespachoServiceImpl implements EstadoDespachoService {
    @Autowired
    private EstadoDespachoRepository estadoDespachoRepository;
    @Autowired
    private ConfigProperty message;

    @Override
    @Transactional(readOnly = true)
    public CustomPage<EstadoDespachoProjection> listarEstDespacho(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return new CustomPage<>(estadoDespachoRepository.listarEstDespacho(pageable));
    }

    @Override
    @Transactional(readOnly = true)
    public EstadoDespachoProjection obtenerEstDespachoPorId(Integer idEstDespacho) {
        return estadoDespachoRepository.obtenerEstDespacho(idEstDespacho);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> registrarEstDespacho(RegistrarEstDespachoRequest request) {
        EstadoDespachoEntity entity;
        if (request.getIdEstDespacho() == 0) {
            var idCorr = estadoDespachoRepository.getIdCorrelativo();
            entity = new EstadoDespachoEntity();
            entity.setIdEstadoDespacho(idCorr);
            entity.setDescripcion(request.getDescripcion());
            entity.setActivo(Boolean.TRUE);
            entity.setFechaCreacion(Date.valueOf(LocalDate.now()));
        } else {
            entity = estadoDespachoRepository.findById(request.getIdEstDespacho()).orElse(null);

            if (entity == null) {
                return MessageResponse.setResponse(Boolean.FALSE, HttpStatus.NOT_FOUND,
                    message.getMessage("mensaje.notFound"));
            }

            entity.setDescripcion(request.getDescripcion());
            entity.setFechaModificacion(Date.valueOf(LocalDate.now()));
        }
        estadoDespachoRepository.save(entity);
        return MessageResponse.setResponse(Boolean.TRUE, HttpStatus.OK,
            message.getMessage("mensaje.api202"));
    }

    @Override
    @Transactional
    public ResponseEntity<Object> desactivarEstDespacho(DesactivarEstDespachoRequest request) {
        EstadoDespachoEntity entity = estadoDespachoRepository.findById(request.getIdEstDespacho()).orElse(null);

        if (entity == null) {
            return MessageResponse.setResponse(Boolean.FALSE, HttpStatus.NOT_FOUND,
                message.getMessage("mensaje.notFound"));
        }

        entity.setActivo(request.getActivar());
        entity.setFechaModificacion(Date.valueOf(LocalDate.now()));
        estadoDespachoRepository.save(entity);
        return MessageResponse.setResponse(Boolean.TRUE, HttpStatus.OK,
            message.getMessage("mensaje.update"));
    }
}
