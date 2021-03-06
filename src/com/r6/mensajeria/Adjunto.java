/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r6.mensajeria;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.r6.service.Servicio;

@Entity
@Table(name = "Tbladjunto")
@NamedQueries(value = {
    @NamedQuery(
            name = "Adjunto.find",
            query = "SELECT a FROM Adjunto a WHERE a.id = :idParam")
    ,
		@NamedQuery(
            name = "Adjunto.findAll",
            query = "SELECT a FROM Adjunto a ORDER BY a.id")
    ,
                @NamedQuery(
            name = "Adjunto.findByMail",
            query = "SELECT A from Adjunto a INNER JOIN A.correos AS AC WHERE AC.id = :idMailParam"
    )
    ,
                @NamedQuery(name = "Adjunto.findByBit", query = "Select A from Adjunto A INNER JOIN A.bitacora as AB where AB.id = :idBitParam")

})
public class Adjunto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "Id_adjunto")
    private Integer id;

    @ManyToMany(mappedBy = "adjuntos")
    private Set<Correo> correos;

    @ManyToMany(mappedBy = "adjuntosBitacora")
    private Set<Bitacora> bitacora;

    @Type(type = "org.hibernate.type.MaterializedBlobType")
    private byte[] archivo;

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

    
    
    
    public String getMIMETypeFromByteArray() {
        byte[] topOfStream = new byte[32];
        System.arraycopy(this.archivo, 0, topOfStream, 0, topOfStream.length);
        return guessMimeType(topOfStream);
    }
    
    
    public String guessMimeType(byte[] topOfStream) {

        String mimeType = null;
        Properties magicmimes = new Properties();
        FileInputStream in = null;

        try {
            in = new FileInputStream(Servicio.getUbicacionMimes());
            magicmimes.load(in);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Enumeration keys = magicmimes.keys(); keys.hasMoreElements();) {
            String key = (String) keys.nextElement();
            byte[] sample = new byte[key.length()];
            System.arraycopy(topOfStream, 0, sample, 0, sample.length);
            if (key.equals(new String(sample))) {
                mimeType = magicmimes.getProperty(key);
                System.out.println("Mime Found! " + mimeType);
                break;
            } else {
                System.out.println("trying " + key + " == " + new String(sample));
            }
        }

        return mimeType;
    }
}
