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
@Table(name = "hardware")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hardware.findAll", query = "SELECT h FROM Hardware h")
    , @NamedQuery(name = "Hardware.findByIdHardware", query = "SELECT h FROM Hardware h WHERE h.idHardware = :idHardware")
    , @NamedQuery(name = "Hardware.findByPeriodoGarantia", query = "SELECT h FROM Hardware h WHERE h.periodoGarantia = :periodoGarantia")
    , @NamedQuery(name = "Hardware.findByImpuesto", query = "SELECT h FROM Hardware h WHERE h.impuesto = :impuesto")})
public class Hardware implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idHardware")
    private Integer idHardware;
    @Column(name = "periodoGarantia")
    private Integer periodoGarantia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "impuesto")
    private Double impuesto;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "producto")
    private Producto producto;

    public Hardware() {
    }

    public Hardware(Integer idHardware) {
        this.idHardware = idHardware;
    }

    public Integer getIdHardware() {
        return idHardware;
    }

    public void setIdHardware(Integer idHardware) {
        this.idHardware = idHardware;
    }

    public Integer getPeriodoGarantia() {
        return periodoGarantia;
    }

    public void setPeriodoGarantia(Integer periodoGarantia) {
        this.periodoGarantia = periodoGarantia;
    }

    public Double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Double impuesto) {
        this.impuesto = impuesto;
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
        hash += (idHardware != null ? idHardware.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hardware)) {
            return false;
        }
        Hardware other = (Hardware) object;
        if ((this.idHardware == null && other.idHardware != null) || (this.idHardware != null && !this.idHardware.equals(other.idHardware))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.r6.mensajeria.Hardware[ idHardware=" + idHardware + " ]";
    }
    
}
