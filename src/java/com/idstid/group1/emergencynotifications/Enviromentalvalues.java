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
@Table(name = "ENVIROMENTALVALUES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Enviromentalvalues.findAll", query = "SELECT e FROM Enviromentalvalues e")
    , @NamedQuery(name = "Enviromentalvalues.findByIdenv", query = "SELECT e FROM Enviromentalvalues e WHERE e.idenv = :idenv")
    , @NamedQuery(name = "Enviromentalvalues.findByDetectiontime", query = "SELECT e FROM Enviromentalvalues e WHERE e.detectiontime = :detectiontime")
    , @NamedQuery(name = "Enviromentalvalues.findByTemperature", query = "SELECT e FROM Enviromentalvalues e WHERE e.temperature = :temperature")
    , @NamedQuery(name = "Enviromentalvalues.findByHumidity", query = "SELECT e FROM Enviromentalvalues e WHERE e.humidity = :humidity")
    , @NamedQuery(name = "Enviromentalvalues.findByAccx", query = "SELECT e FROM Enviromentalvalues e WHERE e.accx = :accx")
    , @NamedQuery(name = "Enviromentalvalues.findByAccy", query = "SELECT e FROM Enviromentalvalues e WHERE e.accy = :accy")
    , @NamedQuery(name = "Enviromentalvalues.findByAccz", query = "SELECT e FROM Enviromentalvalues e WHERE e.accz = :accz")
    , @NamedQuery(name = "Enviromentalvalues.findByGyrx", query = "SELECT e FROM Enviromentalvalues e WHERE e.gyrx = :gyrx")
    , @NamedQuery(name = "Enviromentalvalues.findByGyry", query = "SELECT e FROM Enviromentalvalues e WHERE e.gyry = :gyry")
    , @NamedQuery(name = "Enviromentalvalues.findByGyrz", query = "SELECT e FROM Enviromentalvalues e WHERE e.gyrz = :gyrz")
    , @NamedQuery(name = "Enviromentalvalues.findByMagx", query = "SELECT e FROM Enviromentalvalues e WHERE e.magx = :magx")
    , @NamedQuery(name = "Enviromentalvalues.findByMagy", query = "SELECT e FROM Enviromentalvalues e WHERE e.magy = :magy")
    , @NamedQuery(name = "Enviromentalvalues.findByMagz", query = "SELECT e FROM Enviromentalvalues e WHERE e.magz = :magz")})
public class Enviromentalvalues implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDENV")
    private Integer idenv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DETECTIONTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date detectiontime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TEMPERATURE")
    private Double temperature;
    @Column(name = "HUMIDITY")
    private Double humidity;
    @Column(name = "ACCX")
    private Double accx;
    @Column(name = "ACCY")
    private Double accy;
    @Column(name = "ACCZ")
    private Double accz;
    @Column(name = "GYRX")
    private Double gyrx;
    @Column(name = "GYRY")
    private Double gyry;
    @Column(name = "GYRZ")
    private Double gyrz;
    @Column(name = "MAGX")
    private Double magx;
    @Column(name = "MAGY")
    private Double magy;
    @Column(name = "MAGZ")
    private Double magz;
    @JoinColumn(name = "IDBEACON", referencedColumnName = "IDBEACON")
    @ManyToOne(optional = false)
    private Beacon idbeacon;

    public Enviromentalvalues() {
    }

    public Enviromentalvalues(Integer idenv) {
        this.idenv = idenv;
    }

    public Enviromentalvalues(Integer idenv, Date detectiontime) {
        this.idenv = idenv;
        this.detectiontime = detectiontime;
    }

    public Integer getIdenv() {
        return idenv;
    }

    public void setIdenv(Integer idenv) {
        this.idenv = idenv;
    }

    public Date getDetectiontime() {
        return detectiontime;
    }

    public void setDetectiontime(Date detectiontime) {
        this.detectiontime = detectiontime;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getAccx() {
        return accx;
    }

    public void setAccx(Double accx) {
        this.accx = accx;
    }

    public Double getAccy() {
        return accy;
    }

    public void setAccy(Double accy) {
        this.accy = accy;
    }

    public Double getAccz() {
        return accz;
    }

    public void setAccz(Double accz) {
        this.accz = accz;
    }

    public Double getGyrx() {
        return gyrx;
    }

    public void setGyrx(Double gyrx) {
        this.gyrx = gyrx;
    }

    public Double getGyry() {
        return gyry;
    }

    public void setGyry(Double gyry) {
        this.gyry = gyry;
    }

    public Double getGyrz() {
        return gyrz;
    }

    public void setGyrz(Double gyrz) {
        this.gyrz = gyrz;
    }

    public Double getMagx() {
        return magx;
    }

    public void setMagx(Double magx) {
        this.magx = magx;
    }

    public Double getMagy() {
        return magy;
    }

    public void setMagy(Double magy) {
        this.magy = magy;
    }

    public Double getMagz() {
        return magz;
    }

    public void setMagz(Double magz) {
        this.magz = magz;
    }

    public Beacon getIdbeacon() {
        return idbeacon;
    }

    public void setIdbeacon(Beacon idbeacon) {
        this.idbeacon = idbeacon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idenv != null ? idenv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enviromentalvalues)) {
            return false;
        }
        Enviromentalvalues other = (Enviromentalvalues) object;
        if ((this.idenv == null && other.idenv != null) || (this.idenv != null && !this.idenv.equals(other.idenv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.idstid.group1.emergencynotifications.Enviromentalvalues[ idenv=" + idenv + " ]";
    }
    
}
