package com.proyecto.integrador.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "ESTADO_DOCUMENTO")
public class EstadoDocumentoEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ESTADO_DOCUMENTO")
    private Integer idEstadoDocumento;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "ACTIVO")
    private Boolean activo;

    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;

    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;
}
