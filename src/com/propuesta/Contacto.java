//#region Imports
package com.propuesta;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

//#endregion 
//Many to One con Usuario
//Many to Many con Correo(Para destinatarios)
@Entity
@Table(name = "Tblcontacto")
public class Contacto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "Id_contacto")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Id_contacto_user", referencedColumnName = "Id_usuario")
    private Usuario usuario;

    @ManyToMany(mappedBy = "destinatarios")
    private Set<Correo> correos;

    private String correo;

    private String nombre;

    private String apellido;

    private Boolean suscriptor;

    public Contacto() {
    }

    ;
    

    //<editor-fold defaultstate="collapsed" desc="Getters y Setters-">
        public Set<Correo> getCorreos() {
        return correos;
    }

    public void setCorreos(Set<Correo> correos) {
        this.correos = correos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Boolean getSuscriptor() {
        return this.suscriptor;
    }

    public void setSuscriptor(Boolean suscriptor) {
        this.suscriptor = suscriptor;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    //</editor-fold>

}
