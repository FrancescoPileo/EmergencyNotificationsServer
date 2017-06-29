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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kekko
 */
@Entity
@Table(name = "CHANGELOG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Changelog.findAll", query = "SELECT c FROM Changelog c")
    , @NamedQuery(name = "Changelog.findByTablename", query = "SELECT c FROM Changelog c WHERE c.tablename = :tablename")
    , @NamedQuery(name = "Changelog.findByLastupdate", query = "SELECT c FROM Changelog c WHERE c.lastupdate = :lastupdate")})
public class Changelog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "TABLENAME")
    private String tablename;
    @Column(name = "LASTUPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastupdate;

    public Changelog() {
    }

    public Changelog(String tablename) {
        this.tablename = tablename;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tablename != null ? tablename.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Changelog)) {
            return false;
        }
        Changelog other = (Changelog) object;
        if ((this.tablename == null && other.tablename != null) || (this.tablename != null && !this.tablename.equals(other.tablename))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.idstid.group1.emergencynotifications.Changelog[ tablename=" + tablename + " ]";
    }
    
}
