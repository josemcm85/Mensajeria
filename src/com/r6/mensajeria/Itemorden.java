/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.r6.mensajeria;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "itemorden")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itemorden.findAll", query = "SELECT i FROM Itemorden i")
    , @NamedQuery(name = "Itemorden.findByIdItem", query = "SELECT i FROM Itemorden i WHERE i.idItem = :idItem")
    , @NamedQuery(name = "Itemorden.findByNumLinea", query = "SELECT i FROM Itemorden i WHERE i.numLinea = :numLinea")
    , @NamedQuery(name = "Itemorden.findByCantidad", query = "SELECT i FROM Itemorden i WHERE i.cantidad = :cantidad")
    , @NamedQuery(name = "Itemorden.findByImpuesto", query = "SELECT i FROM Itemorden i WHERE i.impuesto = :impuesto")
    , @NamedQuery(name = "Itemorden.findByPrecioUnitario", query = "SELECT i FROM Itemorden i WHERE i.precioUnitario = :precioUnitario")
    , @NamedQuery(name = "Itemorden.findByTotalItem", query = "SELECT i FROM Itemorden i WHERE i.totalItem = :totalItem")})
public class Itemorden implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idItem")
    private Integer idItem;
    @Column(name = "numLinea")
    private Integer numLinea;
    @Column(name = "cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "impuesto")
    private Double impuesto;
    @Column(name = "precioUnitario")
    private Double precioUnitario;
    @Column(name = "totalItem")
    private Double totalItem;
    
    @JoinColumn(name = "orden", referencedColumnName = "idOrden")
    @ManyToOne(optional = false)
    private Orden orden;

    @JoinColumn(name = "producto", referencedColumnName = "idProducto")
    @ManyToOne(optional = false)
    private Producto producto;

    public Itemorden() {
    }

    public Itemorden(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getNumLinea() {
        return numLinea;
    }

    public void setNumLinea(Integer numLinea) {
        this.numLinea = numLinea;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Double impuesto) {
        this.impuesto = impuesto;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Double totalItem) {
        this.totalItem = totalItem;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
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
        hash += (idItem != null ? idItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itemorden)) {
            return false;
        }
        Itemorden other = (Itemorden) object;
        if ((this.idItem == null && other.idItem != null) || (this.idItem != null && !this.idItem.equals(other.idItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.r6.mensajeria.Itemorden[ idItem=" + idItem + " ]";
    }
    
}
