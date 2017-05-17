/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idstid.group1.emergencynotifications;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kekko
 */
@Entity
@Table(name = "BEACON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Beacon.findAll", query = "SELECT b FROM Beacon b")
    , @NamedQuery(name = "Beacon.findByIdbeacon", query = "SELECT b FROM Beacon b WHERE b.idbeacon = :idbeacon")})
public class Beacon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "IDBEACON")
    private String idbeacon;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbeacon")
    private Collection<Enviromentalvalues> enviromentalvaluesCollection;
    @JoinColumn(name = "IDNODE", referencedColumnName = "IDNODE")
    @ManyToOne(optional = false)
    private Node idnode;

    public Beacon() {
    }

    public Beacon(String idbeacon) {
        this.idbeacon = idbeacon;
    }

    public String getIdbeacon() {
        return idbeacon;
    }

    public void setIdbeacon(String idbeacon) {
        this.idbeacon = idbeacon;
    }

    @XmlTransient
    public Collection<Enviromentalvalues> getEnviromentalvaluesCollection() {
        return enviromentalvaluesCollection;
    }

    public void setEnviromentalvaluesCollection(Collection<Enviromentalvalues> enviromentalvaluesCollection) {
        this.enviromentalvaluesCollection = enviromentalvaluesCollection;
    }

    public Node getIdnode() {
        return idnode;
    }

    public void setIdnode(Node idnode) {
        this.idnode = idnode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbeacon != null ? idbeacon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Beacon)) {
            return false;
        }
        Beacon other = (Beacon) object;
        if ((this.idbeacon == null && other.idbeacon != null) || (this.idbeacon != null && !this.idbeacon.equals(other.idbeacon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.idstid.group1.emergencynotifications.Beacon[ idbeacon=" + idbeacon + " ]";
    }
    
}
