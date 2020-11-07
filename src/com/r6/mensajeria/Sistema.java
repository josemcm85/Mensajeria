//#region Imports
package com.r6.mensajeria;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

//#endregion 

//One con Usuario
@Entity
@Table(name="Tblsistema")
@NamedQueries(value = {
		@NamedQuery(
			name="Sistema.find",
			query="SELECT s FROM Sistema s WHERE s.idSistema = :idParam"),
		@NamedQuery(
			name="Sistema.findAll",
			query="SELECT s FROM Sistema s ORDER BY s.idSistema")
	
})


public class Sistema implements Serializable {

    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name ="native",strategy = "native")
    @Column(name ="Id_sistema")
    private Integer idSistema;
    
    @Column(name="sistema")
    private String nombre;

    @OneToMany(mappedBy="sistema",cascade = CascadeType.ALL)
    private Set<Usuario> usuarios;
    
    private Boolean activo;
    
    public Sistema(){};
    
    //<editor-fold  defaultstate="collapsed" desc="Getters y Setters">
    
    
    public Integer getIdSistema(){
        return idSistema;
    }

    public void setIdSistema(Integer idSistema) {
        this.idSistema = idSistema;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActivo() {
        return activo;
    }

    //<editor-fold  defaultstate="collapsed" desc="Getters y Setters">
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Integer getId() {
        return this.idSistema;
    }

    public void setId(Integer idSistema){
        this.idSistema =  idSistema;
    }

    public String getNombreSistema(){
        return this.nombre;
    }

    public void setNombreSistema(String nombre){
         this.nombre  = nombre;
    }

    public Set<Usuario> getUsuarios(){
        return this.usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios){
        this.usuarios = usuarios;
    }
//</editor-fold>
}