/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r6.mensajeria;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name ="Tbladjunto")
public class Adjunto implements Serializable {
    
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @GenericGenerator(name = "native",strategy = "native")
   @Column(name = "Id_adjunto")
   private Integer id;

   @ManyToMany(mappedBy="adjuntos")
   private Set<Correo> correos;
   
   @ManyToMany(mappedBy = "adjuntosBitacora")
   private Set<Bitacora> bitacora;
   
   private byte[] archivo;
   
   public Adjunto(){};
   
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Correo> getCorreos() {
        return correos;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }



    
    
    public void setCorreos(Set<Correo> correos) {
        this.correos = correos;
    }

    public Set<Bitacora> getBitacora() {
        return bitacora;
    }

    public void setBitacora(Set<Bitacora> bitacora) {
        this.bitacora = bitacora;
    }
    
   
    
   
   
}
