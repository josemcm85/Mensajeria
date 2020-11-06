/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.propuesta;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Nvidi
 */
@Entity
@Table(name = "Tblrecordatorio")
public class Recordatorio implements Serializable {

    @Id
    @GeneratedValue(generator = "native", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "Id_recordatorio")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Id_correo", referencedColumnName = "Id_correo")
    private Correo correo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;

    private Boolean estado;

    public Recordatorio(){};
    
    //<editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Correo getCorreo() {
        return correo;
    }

    public void setCorreo(Correo correo) {
        this.correo = correo;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    //</editor-fold>
}
