package com.proyecto.integrador.api;

import com.proyecto.integrador.model.projection.EstadoDocumentoProjection;
import com.proyecto.integrador.model.request.estadoDocumento.DesactivarEstDocumentoRequest;
import com.proyecto.integrador.model.request.estadoDocumento.RegistrarEstDocumentoRequest;
import com.proyecto.integrador.service.EstadoDocumentoService;
import com.proyecto.integrador.util.CustomPage;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Estado Documento Controller")
@Validated
@RestController
@RequestMapping("/estado-documento")
public class EstadoDocumentoController {

    @Autowired
    private EstadoDocumentoService estadoDocumentoService;

    @GetMapping(value = "listar", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomPage<EstadoDocumentoProjection> listarEstDocumento(
        @RequestParam(name = "page", defaultValue = "1") Integer page,
        @RequestParam(name = "size", defaultValue = "10") Integer size
    ) {
        return estadoDocumentoService.listarEstDocumento(page, size);
    }

    @GetMapping(value = "obtener", produces = MediaType.APPLICATION_JSON_VALUE)
    public EstadoDocumentoProjection obtenerEstDocumento(
        @RequestParam(name = "id_est_documento") Integer idEstDocumento
    ) {
        return estadoDocumentoService.obtenerEstDocumento(idEstDocumento);
    }

    @PostMapping(value = "registrar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registrarEstDocumento(
        @RequestBody RegistrarEstDocumentoRequest request
    ) {
        return estadoDocumentoService.registrarEstDocumento(request);
    }

    @DeleteMapping(value = "desactivar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> desactivarEstDocumento(
        @RequestBody DesactivarEstDocumentoRequest request
    ) {
        return estadoDocumentoService.desactivarEstDocumento(request);
    }
}
