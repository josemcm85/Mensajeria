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
@Table(name = "individuo")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Individuo.findAll", query = "SELECT i FROM Individuo i")
    , @NamedQuery(name = "Individuo.findByIdIndividuo", query = "SELECT i FROM Individuo i WHERE i.idIndividuo = :idIndividuo")
    , @NamedQuery(name = "Individuo.findByNumeroLic", query = "SELECT i FROM Individuo i WHERE i.numeroLic = :numeroLic")})
public class Individuo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idIndividuo")
    private Integer idIndividuo;
    @Column(name = "numeroLic")
    private String numeroLic;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente")
    private Cliente cliente;

    public Individuo() {
    }

    public Individuo(Integer idIndividuo) {
        this.idIndividuo = idIndividuo;
    }

    public Integer getIdIndividuo() {
        return idIndividuo;
    }

    public void setIdIndividuo(Integer idIndividuo) {
        this.idIndividuo = idIndividuo;
    }

    public String getNumeroLic() {
        return numeroLic;
    }

    public void setNumeroLic(String numeroLic) {
        this.numeroLic = numeroLic;
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
        hash += (idIndividuo != null ? idIndividuo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Individuo)) {
            return false;
        }
        Individuo other = (Individuo) object;
        if ((this.idIndividuo == null && other.idIndividuo != null) || (this.idIndividuo != null && !this.idIndividuo.equals(other.idIndividuo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.r6.mensajeria.Individuo[ idIndividuo=" + idIndividuo + " ]";
    }
    
}
