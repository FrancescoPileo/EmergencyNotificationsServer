/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idstid.group1.emergencynotifications;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kekko
 */
@Entity
@Table(name = "USERPOSITION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userposition.findAll", query = "SELECT u FROM Userposition u")
    , @NamedQuery(name = "Userposition.findByIdposition", query = "SELECT u FROM Userposition u WHERE u.idposition = :idposition")
    , @NamedQuery(name = "Userposition.findByDetectiontime", query = "SELECT u FROM Userposition u WHERE u.detectiontime = :detectiontime")})
public class Userposition implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPOSITION")
    private Integer idposition;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DETECTIONTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date detectiontime;
    @JoinColumn(name = "IDUSER", referencedColumnName = "IDUSER")
    @ManyToOne(optional = false)
    private Appuser iduser;
    @JoinColumn(name = "IDNODE", referencedColumnName = "IDNODE")
    @ManyToOne(optional = false)
    private Node idnode;

    public Userposition() {
    }

    public Userposition(Integer idposition) {
        this.idposition = idposition;
    }

    public Userposition(Integer idposition, Date detectiontime) {
        this.idposition = idposition;
        this.detectiontime = detectiontime;
    }

    public Integer getIdposition() {
        return idposition;
    }

    public void setIdposition(Integer idposition) {
        this.idposition = idposition;
    }

    public Date getDetectiontime() {
        return detectiontime;
    }

    public void setDetectiontime(Date detectiontime) {
        this.detectiontime = detectiontime;
    }

    public Appuser getIduser() {
        return iduser;
    }

    public void setIduser(Appuser iduser) {
        this.iduser = iduser;
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
        hash += (idposition != null ? idposition.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userposition)) {
            return false;
        }
        Userposition other = (Userposition) object;
        if ((this.idposition == null && other.idposition != null) || (this.idposition != null && !this.idposition.equals(other.idposition))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.idstid.group1.emergencynotifications.Userposition[ idposition=" + idposition + " ]";
    }
    
}
