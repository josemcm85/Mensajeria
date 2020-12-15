/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r6.mensajeria;

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
@NamedQueries(value = {
    @NamedQuery(name = "Bitacora.findByPk", query = "Select t from Bitacora t where t.id = :idParam Order by t.fechaEnvio ASC")
    ,
        @NamedQuery(name = "Bitacora.findAll", query = "Select t from Bitacora t Order by t.fechaEnvio ASC")
    ,
        @NamedQuery(name = "Bitacora.findByUser", query = "Select t from Bitacora t where t.de LIKE :emailParam order by T.fechaEnvio ASC")
    ,
        @NamedQuery(name = "Bitacora.findByFile", 
                query = "Select bit from Bitacora bit INNER JOIN BIT.adjuntosBitacora AS adb  where adb.id = :idParam")
    ,
        @NamedQuery(name = "Bitacora.findByDateRange", query = "Select t from Bitacora t where t.fechaEnvio BETWEEN :fIniParam AND :fFinParam order by T.fechaEnvio ASC")
})
public class Bitacora  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    private Boolean infinito;

    public Bitacora() {
    }

    ;
    
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

    public Boolean getInfinito() {
        return infinito;
    }

    public void setInfinito(Boolean infinito) {
        this.infinito = infinito;
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