package com.proyecto.integrador.api;

import com.proyecto.integrador.model.projection.EstadoDespachoProjection;
import com.proyecto.integrador.model.request.estadoDespacho.DesactivarEstDespachoRequest;
import com.proyecto.integrador.model.request.estadoDespacho.RegistrarEstDespachoRequest;
import com.proyecto.integrador.service.EstadoDespachoService;
import com.proyecto.integrador.util.CustomPage;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Estado Despacho Controller")
@Validated
@Controller
@RequestMapping("/estado-despacho")
public class EstadoDespachoController {

    @Autowired
    private EstadoDespachoService estadoDespachoService;

    @GetMapping(value = "listar")
    public String listarEstDespacho(
        @RequestParam(name = "page", defaultValue = "1") Integer page,
        @RequestParam(name = "size", defaultValue = "10") Integer size,
        Model model
    ) {
        CustomPage<EstadoDespachoProjection> estadosPage = estadoDespachoService.listarEstDespacho(page, size);

        model.addAttribute("data", estadosPage.getData());
        model.addAttribute("paginado", estadosPage.getPageable());
        return "estadoDespacho";
    }

    @GetMapping(value = "obtener", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public EstadoDespachoProjection obtenerEstDespachoPorId(
        @RequestParam(name = "id_est_despacho") Integer idEstDespacho
    ) {
        return estadoDespachoService.obtenerEstDespachoPorId(idEstDespacho);
    }

    @PostMapping(value = "registrar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registrarEstDespacho(
        @RequestBody RegistrarEstDespachoRequest request
    ) {
        return estadoDespachoService.registrarEstDespacho(request);
    }

    @DeleteMapping(value = "desactivar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> desactivarEstDespacho(
        @RequestBody DesactivarEstDespachoRequest request
    ) {
        return estadoDespachoService.desactivarEstDespacho(request);
    }
}
