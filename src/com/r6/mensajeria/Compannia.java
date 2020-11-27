/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r6.mensajeria;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
//import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "compannia")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compannia.findAll", query = "SELECT c FROM Compannia c")
    , @NamedQuery(name = "Compannia.findByIdCompannia", query = "SELECT c FROM Compannia c WHERE c.idCompannia = :idCompannia")
    , @NamedQuery(name = "Compannia.findByDescuento", query = "SELECT c FROM Compannia c WHERE c.descuento = :descuento")
    , @NamedQuery(name = "Compannia.findByContacto", query = "SELECT c FROM Compannia c WHERE c.contacto = :contacto")})
public class Compannia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCompannia")
    private Integer idCompannia;
    @Column(name = "descuento")
    private Integer descuento;
    @Column(name = "Contacto")
    private String contacto;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente")
    private Cliente cliente;

    public Compannia() {
    }

    public Compannia(Integer idCompannia) {
        this.idCompannia = idCompannia;
    }

    public Integer getIdCompannia() {
        return idCompannia;
    }

    public void setIdCompannia(Integer idCompannia) {
        this.idCompannia = idCompannia;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompannia != null ? idCompannia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compannia)) {
            return false;
        }
        Compannia other = (Compannia) object;
        if ((this.idCompannia == null && other.idCompannia != null) || (this.idCompannia != null && !this.idCompannia.equals(other.idCompannia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.r6.mensajeria.Compannia[ idCompannia=" + idCompannia + " ]";
    }
    
}
