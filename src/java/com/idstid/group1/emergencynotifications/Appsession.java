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
@Table(name = "APPSESSION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appsession.findAll", query = "SELECT a FROM Appsession a")
    , @NamedQuery(name = "Appsession.findByIdsession", query = "SELECT a FROM Appsession a WHERE a.idsession = :idsession")
    , @NamedQuery(name = "Appsession.findBySessiontimestart", query = "SELECT a FROM Appsession a WHERE a.sessiontimestart = :sessiontimestart")
    , @NamedQuery(name = "Appsession.findBySessiontimestop", query = "SELECT a FROM Appsession a WHERE a.sessiontimestop = :sessiontimestop")})
public class Appsession implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDSESSION")
    private Integer idsession;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SESSIONTIMESTART")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sessiontimestart;
    @Column(name = "SESSIONTIMESTOP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sessiontimestop;
    @JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME")
    @ManyToOne(optional = false)
    private Appuser username;

    public Appsession() {
    }

    public Appsession(Integer idsession) {
        this.idsession = idsession;
    }

    public Appsession(Integer idsession, Date sessiontimestart) {
        this.idsession = idsession;
        this.sessiontimestart = sessiontimestart;
    }

    public Integer getIdsession() {
        return idsession;
    }

    public void setIdsession(Integer idsession) {
        this.idsession = idsession;
    }

    public Date getSessiontimestart() {
        return sessiontimestart;
    }

    public void setSessiontimestart(Date sessiontimestart) {
        this.sessiontimestart = sessiontimestart;
    }

    public Date getSessiontimestop() {
        return sessiontimestop;
    }

    public void setSessiontimestop(Date sessiontimestop) {
        this.sessiontimestop = sessiontimestop;
    }

    public Appuser getUsername() {
        return username;
    }

    public void setUsername(Appuser username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsession != null ? idsession.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appsession)) {
            return false;
        }
        Appsession other = (Appsession) object;
        if ((this.idsession == null && other.idsession != null) || (this.idsession != null && !this.idsession.equals(other.idsession))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.idstid.group1.emergencynotifications.Appsession[ idsession=" + idsession + " ]";
    }
    
}
