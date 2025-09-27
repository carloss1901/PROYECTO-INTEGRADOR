package com.proyecto.integrador.service.impl;

import com.proyecto.integrador.config.ConfigProperty;
import com.proyecto.integrador.model.entity.EstadoDocumentoEntity;
import com.proyecto.integrador.model.projection.EstadoDocumentoProjection;
import com.proyecto.integrador.model.request.estadoDocumento.DesactivarEstDocumentoRequest;
import com.proyecto.integrador.model.request.estadoDocumento.RegistrarEstDocumentoRequest;
import com.proyecto.integrador.repository.EstadoDocumentoRepository;
import com.proyecto.integrador.service.EstadoDocumentoService;
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
public class EstadoDocumentoServiceImpl implements EstadoDocumentoService {
    @Autowired
    private EstadoDocumentoRepository estadoDocumentoRepository;
    @Autowired
    private ConfigProperty message;

    @Override
    @Transactional(readOnly = true)
    public CustomPage<EstadoDocumentoProjection> listarEstDocumento(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return new CustomPage<>(estadoDocumentoRepository.listarEstDocumento(pageable));
    }

    @Override
    @Transactional(readOnly = true)
    public EstadoDocumentoProjection obtenerEstDocumento(Integer idEstDocumento) {
        return estadoDocumentoRepository.obtenerEstDocumentoPorId(idEstDocumento);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> registrarEstDocumento(RegistrarEstDocumentoRequest request) {
        EstadoDocumentoEntity entity;
        if (request.getIdEstDocumento() == 0) {
            entity = new EstadoDocumentoEntity();
            entity.setDescripcion(request.getDescripcion());
            entity.setActivo(Boolean.TRUE);
            entity.setFechaCreacion(Date.valueOf(LocalDate.now()));
        } else {
            entity = estadoDocumentoRepository.findById(request.getIdEstDocumento()).orElse(null);

            if (entity == null) {
                return MessageResponse.setResponse(Boolean.FALSE, HttpStatus.NOT_FOUND,
                    message.getMessage("mensaje.notFound"));
            }

            entity.setDescripcion(request.getDescripcion());
            entity.setFechaModificacion(Date.valueOf(LocalDate.now()));
        }
        estadoDocumentoRepository.save(entity);
        return MessageResponse.setResponse(Boolean.TRUE, HttpStatus.OK,
            message.getMessage("mensaje.api202"));
    }

    @Override
    @Transactional
    public ResponseEntity<Object> desactivarEstDocumento(DesactivarEstDocumentoRequest request) {
        EstadoDocumentoEntity entity = estadoDocumentoRepository.findById(request.getIdEstDocumento()).orElse(null);

        if (entity == null) {
            return MessageResponse.setResponse(Boolean.FALSE, HttpStatus.NOT_FOUND,
                message.getMessage("mensaje.notFound"));
        }

        entity.setActivo(request.getActivar());
        entity.setFechaModificacion(Date.valueOf(LocalDate.now()));
        estadoDocumentoRepository.save(entity);
        return MessageResponse.setResponse(Boolean.TRUE, HttpStatus.OK,
            message.getMessage("mensaje.update"));
    }
}
