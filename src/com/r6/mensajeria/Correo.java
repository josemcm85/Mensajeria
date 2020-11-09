//#region Imports
package com.r6.mensajeria;

import java.io.Serializable;
import java.time.LocalDateTime;
//import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

//#endregion 
//Many to Many a   Contacto(Destinatarios)
//Many to Many con Usuarios(para remitente)
//Many to Many con Usuarios(para copias internas)
//One to Many  con Recordatorios
//Many to Many con Adjunto
@Entity
@Table(name = "Tblcorreo")

public class Correo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "Id_correo")
    private Integer id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Tbl_destinatario",
            joinColumns = {
                @JoinColumn(name = "Id_correo", referencedColumnName = "Id_correo")},
            inverseJoinColumns = {
                @JoinColumn(name = "Id_contacto", referencedColumnName = "Id_contacto")})
    private Set<Contacto> destinatarios;

    @ManyToMany(mappedBy = "correos")
    private Set<Usuario> usuarios;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Tbl_usuariosCopiados",
            joinColumns = {
                @JoinColumn(name = "Id_correo", referencedColumnName = "Id_correo")},
            inverseJoinColumns = {
                @JoinColumn(name = "Id_usuario", referencedColumnName = "Id_usuario")})
    private Set<Usuario> usuarioscopiados;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Tbl_adjuntosCorreos",
            joinColumns = {
                @JoinColumn(name = "Id_correo",referencedColumnName = "Id_correo")},
            inverseJoinColumns = {
                @JoinColumn(name = "Id_adjunto", referencedColumnName = "Id_adjunto")})
    private Set<Adjunto> adjuntos;
    
    

    @OneToMany(mappedBy = "correo", cascade = CascadeType.ALL)
    private Set<Recordatorio> recordatorios;

    private String asunto;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;

    private String tipo;

    private String cuerpo;

    private Boolean enviado;

    public Correo() {
    }

    ;
    
    

    //<editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public Set<Contacto> getDestinatarios() {
        return destinatarios;
    }

    public Integer getId() {
        return id;
    }

    public Set<Adjunto> getAdjuntos() {
        return adjuntos;
    }

    public void setAdjuntos(Set<Adjunto> adjuntos) {
        this.adjuntos = adjuntos;
    }

    public Set<Recordatorio> getRecordatorios() {
        return recordatorios;
    }

    public void setRecordatorios(Set<Recordatorio> recordatorios) {
        this.recordatorios = recordatorios;
    }

    public Set<Usuario> getUsuarioscopiados() {
        return usuarioscopiados;
    }

    public void setUsuarioscopiados(Set<Usuario> usuarioscopiados) {
        this.usuarioscopiados = usuarioscopiados;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Set<Contacto> getRemitentes() {
        return destinatarios;
    }

    public void setDestinatarios(Set<Contacto> remitente) {
        this.destinatarios = remitente;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
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
    //</editor-fold>

}
