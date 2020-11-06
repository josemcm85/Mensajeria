//#region Imports
package com.propuesta;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

//#endregion 
//Many to One con Sistema
//One to many con contactos
//Many to Many con Correos(Para remitente)
@Entity
@Table(name = "Tblusuario")
public class Usuario implements Serializable {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "Id_usuario")
    private Integer idUsuario;

    @ManyToOne
    @JoinColumn(name = "Id_user_sys", referencedColumnName = "Id_sistema")
    private Sistema sistema;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Set<Contacto> contactos;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Tbl_remitente",
            joinColumns = {
                @JoinColumn(name = "Id_usuario", referencedColumnName = "Id_usuario")},
            inverseJoinColumns = {
                @JoinColumn(name = "Id_correo", referencedColumnName = "Id_correo")})
    private Set<Correo> correos;

    @ManyToMany(mappedBy = "usuarioscopiados")
    private Set<Correo> correoscopiados;

    private String correo;

    private String contrasenia;

    public Usuario(){};
    
    //<editor-fold defaultstate="collapsed" desc="Getters y Setters">

    public Set<Correo> getCorreos() {
        return correos;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public void setCorreos(Set<Correo> correos) {
        this.correos = correos;
    }

    public Set<Correo> getCorreoscopiados() {
        return correoscopiados;
    }

    public Set<Correo> getRemitentesCorreo() {
        return correos;
    }

    public void setRemitentesCorreo(Set<Correo> remitentesCorreo) {
        this.correos = remitentesCorreo;
    }


    
    

    public void setCorreoscopiados(Set<Correo> correoscopiados) {
        this.correoscopiados = correoscopiados;
    }

    public Integer getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Sistema getSistema() {
        return this.sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return this.contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Set<Contacto> getContactos() {
        return this.contactos;
    }

    public void setContactos(Set<Contacto> contactos) {
        this.contactos = contactos;
    }

    //</editor-fold>
}
