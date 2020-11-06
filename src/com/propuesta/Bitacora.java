/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.propuesta;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Nvidi
 */
//many to many con Adjunto
@Entity
@Table(name = "Tblbitacora")
public class Bitacora {

    @Id
    @GeneratedValue(generator = "native", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "Id_bitacora")
    private Integer id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Tbl_adjuntosbitacora",
            joinColumns = {
                @JoinColumn(name = "Id_bitacora", referencedColumnName = "Id_bitacora")},
            inverseJoinColumns = {
                @JoinColumn(name = "Id_adjunto", referencedColumnName = "Id_adjunto")})
    private Set<Adjunto> adjuntosBitacora;
    
    private String para;
    
    private String de;
    
    private String copiados;
    
    private String Asunto;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;
    
    private String tipo;
    
    private String cuerpo;
    
    private Boolean enviado;
    
    public Bitacora(){};
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Adjunto> getAdjuntosBitacora() {
        return adjuntosBitacora;
    }

    public void setAdjuntosBitacora(Set<Adjunto> adjuntosBitacora) {
        this.adjuntosBitacora = adjuntosBitacora;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getCopiados() {
        return copiados;
    }

    public void setCopiados(String copiados) {
        this.copiados = copiados;
    }

    public String getAsunto() {
        return Asunto;
    }

    public void setAsunto(String Asunto) {
        this.Asunto = Asunto;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Boolean getEnviado() {
        return enviado;
    }

    public void setEnviado(Boolean enviado) {
        this.enviado = enviado;
    }

    
    
}
