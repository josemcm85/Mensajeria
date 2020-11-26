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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "manual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Manual.findAll", query = "SELECT m FROM Manual m")
    , @NamedQuery(name = "Manual.findByIdManual", query = "SELECT m FROM Manual m WHERE m.idManual = :idManual")
    , @NamedQuery(name = "Manual.findByAutor", query = "SELECT m FROM Manual m WHERE m.autor = :autor")})
public class Manual implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idManual")
    private Integer idManual;
    @Column(name = "Autor")
    private String autor;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "producto")
    private Producto producto;

    public Manual() {
    }

    public Manual(Integer idManual) {
        this.idManual = idManual;
    }

    public Integer getIdManual() {
        return idManual;
    }

    public void setIdManual(Integer idManual) {
        this.idManual = idManual;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idManual != null ? idManual.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Manual)) {
            return false;
        }
        Manual other = (Manual) object;
        if ((this.idManual == null && other.idManual != null) || (this.idManual != null && !this.idManual.equals(other.idManual))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.r6.mensajeria.Manual[ idManual=" + idManual + " ]";
    }
    
}
