package com.proyecto.integrador.api;

import com.proyecto.integrador.model.projection.EstadoDespachoProjection;
import com.proyecto.integrador.model.request.estadoDespacho.DesactivarEstDespachoRequest;
import com.proyecto.integrador.model.request.estadoDespacho.RegistrarEstDespachoRequest;
import com.proyecto.integrador.service.EstadoDespachoService;
import com.proyecto.integrador.util.Constantes;
import com.proyecto.integrador.util.CustomPage;
import com.proyecto.integrador.util.ErrorGenerico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Estado Despacho Controller")
@Validated
@RestController
@RequestMapping("/estado-despacho")
public class EstadoDespachoController {

    @Autowired
    private EstadoDespachoService estadoDespachoService;

    @GetMapping(value = "listar", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Listar estados de despacho",
        description = "Listar estados de despacho",
        responses = {
            @ApiResponse(
                responseCode = Constantes.API_STATUS_200,
                description = Constantes.MSG_API_200,
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema =
                        @Schema(implementation = EstadoDespachoProjection.class)))),
            @ApiResponse(
                responseCode = Constantes.API_STATUS_400,
                description = Constantes.API_STATUS_400,
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorGenerico.class))),
            @ApiResponse(
                responseCode = Constantes.API_STATUS_500,
                description = Constantes.MSF_API_500,
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorGenerico.class)))
        })
    public CustomPage<EstadoDespachoProjection> listarEstDespacho(
        @RequestParam(name = "page", defaultValue = "1") Integer page,
        @RequestParam(name = "size", defaultValue = "10") Integer size
    ) {
        return estadoDespachoService.listarEstDespacho(page, size);
    }

    @GetMapping(value = "obtener", produces = MediaType.APPLICATION_JSON_VALUE)
    public EstadoDespachoProjection obtenerEstDespachoPorId(
        @RequestParam(name = "id_est_despacho") Integer idEstDespacho
    ) {
        return estadoDespachoService.obtenerEstDespachoPorId(idEstDespacho);
    }

    @PostMapping(value = "registrar", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Registrar y editar estados de despacho",
        description = "Registrar y editar estados de despacho",
        responses = {
            @ApiResponse(
                responseCode = Constantes.API_STATUS_200,
                description = Constantes.MSG_API_200,
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(
                responseCode = Constantes.API_STATUS_400,
                description = Constantes.MSG_API_400,
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorGenerico.class))),
            @ApiResponse(
                responseCode = Constantes.API_STATUS_500,
                description = Constantes.MSF_API_500,
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorGenerico.class)))
    })
    public ResponseEntity<Object> registrarEstDespacho(
        @RequestBody RegistrarEstDespachoRequest request
    ) {
        return estadoDespachoService.registrarEstDespacho(request);
    }

    @DeleteMapping(value = "desactivar", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Registrar y editar estados de despacho",
        description = "Registrar y editar estados de despacho",
        responses = {
            @ApiResponse(
                responseCode = Constantes.API_STATUS_200,
                description = Constantes.MSG_API_200,
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(
                responseCode = Constantes.API_STATUS_400,
                description = Constantes.MSG_API_400,
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorGenerico.class))),
            @ApiResponse(
                responseCode = Constantes.API_STATUS_500,
                description = Constantes.MSF_API_500,
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorGenerico.class)))
    })
    public ResponseEntity<Object> desactivarEstDespacho(
        @RequestBody DesactivarEstDespachoRequest request
    ) {
        return estadoDespachoService.desactivarEstDespacho(request);
    }
}
