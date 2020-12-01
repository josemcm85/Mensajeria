//#region Imports
package com.r6.mensajeria;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

//Many to One con Usuario
//Many to Many con Correo(Para destinatarios)
@Entity
@Table(name = "Tblcontacto")
@NamedQueries(value = {
    @NamedQuery(
            name = "Contacto.findPk",
            query = "Select t from Contacto t Where t.id = :idParam AND t.usuario.idUsuario = :idUserParam")
    ,
        @NamedQuery(
            name = "Contacto.findAll",
            query = "Select DISTINCT t from Contacto t where t.usuario.idUsuario = :idUserParam ")
    ,
        @NamedQuery(
            name = "Contacto.findSubs",
            query = "Select  t from Contacto t where t.usuario.idUsuario = :idUserParam and t.suscriptor = TRUE  "
    )
    ,
        @NamedQuery(name = "Contacto.maxId",
            query = "Select COALESCE(Max(t.id),0) from Contacto t "),
        
        @NamedQuery(name ="Contacto.findbyCliente",
                query = "Select  t from Contacto t where t.cliente.idCliente = :idClienteParam")
})
public class Contacto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "Id_contacto")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Id_contacto_user", referencedColumnName = "Id_usuario")
    private Usuario usuario;

    @ManyToMany(mappedBy = "destinatarios")
    private Set<Correo> correos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente")
    private Cliente cliente;

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
