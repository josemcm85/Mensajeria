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
@NamedQueries(value = {
    @NamedQuery(
            name = "Correo.findAll",
            query = "SELECT c FROM Correo c"),
    @NamedQuery(
            name = "Correo.findByUserAndSys",
            query = "SELECT DISTINCT AC from Adjunto a INNER JOIN A.correos AS AC INNER JOIN AC.usuarios as ACU INNER JOIN ACU.sistema as ACUA  WHERE ACU.correo LIKE :mailUserParam AND ACUA.idSistema LIKE :idSysParam")

})

public class Correo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "Id_correo")
    private Integer id;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade
            = {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
            })
    @JoinTable(name = "Tbl_destinatario",
            joinColumns = {
                @JoinColumn(name = "Id_correo", referencedColumnName = "Id_correo")},
            inverseJoinColumns = {
                @JoinColumn(name = "Id_contacto", referencedColumnName = "Id_contacto")})
    private Set<Contacto> destinatarios;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade
            = {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
            })
    @JoinTable(name = "Tbl_remitente",
            joinColumns = {
                @JoinColumn(name = "Id_correo", referencedColumnName = "Id_correo")},
            inverseJoinColumns = {
                @JoinColumn(name = "Id_usuario", referencedColumnName = "Id_usuario")})
    private Set<Usuario> usuarios;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade
            = {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
            })
    @JoinTable(name = "Tbl_usuariosCopiados",
            joinColumns = {
                @JoinColumn(name = "Id_correo", referencedColumnName = "Id_correo")},
            inverseJoinColumns = {
                @JoinColumn(name = "Id_usuario", referencedColumnName = "Id_usuario")})
    private Set<Usuario> usuarioscopiados;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade
            = {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
            })
    @JoinTable(name = "Tbl_adjuntosCorreos",
            joinColumns = {
                @JoinColumn(name = "Id_correo", referencedColumnName = "Id_correo")},
            inverseJoinColumns = {
                @JoinColumn(name = "Id_adjunto", referencedColumnName = "Id_adjunto")})
    private Set<Adjunto> adjuntos;

    @OneToMany(mappedBy = "correo", cascade = CascadeType.ALL)
    private Set<Recordatorio> recordatorios;

    private String asunto;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;

    private int intervalo;

    private String tipo;

    private String cuerpo;
    
    private int meses;
    
    private int veces;
    
    private int frecuencia;

    private Boolean inifinito;

    public Correo() {
    }

    ;
    
    //<editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public Set<Contacto> getDestinatarios() {
        return destinatarios;
    }

    public int getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }

    public Boolean getInifinito() {
        return inifinito;
    }

    public void setInifinito(Boolean inifinito) {
        this.inifinito = inifinito;
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

       public int getMeses() {
        return meses;
    }

    public void setMeses(int meses) {
        this.meses = meses;
    }

    public int getVeces() {
        return veces;
    }

    public void setVeces(int veces) {
        this.veces = veces;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }
     
    //</editor-fold>

 
}
