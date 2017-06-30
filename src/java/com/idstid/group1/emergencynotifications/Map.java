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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "MAP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Map.findAll", query = "SELECT m FROM Map m")
    , @NamedQuery(name = "Map.findByIdmap", query = "SELECT m FROM Map m WHERE m.idmap = :idmap")
    , @NamedQuery(name = "Map.findByBuilding", query = "SELECT m FROM Map m WHERE m.building = :building")
    , @NamedQuery(name = "Map.findByFloor", query = "SELECT m FROM Map m WHERE m.floor = :floor")
    , @NamedQuery(name = "Map.findByMapname", query = "SELECT m FROM Map m WHERE m.mapname = :mapname")
    , @NamedQuery(name = "Map.findByMapscale", query = "SELECT m FROM Map m WHERE m.mapscale = :mapscale")
    , @NamedQuery(name = "Map.findByXref", query = "SELECT m FROM Map m WHERE m.xref = :xref")
    , @NamedQuery(name = "Map.findByXrefpx", query = "SELECT m FROM Map m WHERE m.xrefpx = :xrefpx")
    , @NamedQuery(name = "Map.findByYref", query = "SELECT m FROM Map m WHERE m.yref = :yref")
    , @NamedQuery(name = "Map.findByYrefpx", query = "SELECT m FROM Map m WHERE m.yrefpx = :yrefpx")})
public class Map implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmap")
    private Collection<Node> nodeCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDMAP")
    private Integer idmap;
    @Size(max = 20)
    @Column(name = "BUILDING")
    private String building;
    @Size(max = 20)
    @Column(name = "FLOOR")
    private String floor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "MAPNAME")
    private String mapname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MAPSCALE")
    private double mapscale;
    @Column(name = "XREF")
    private Integer xref;
    @Column(name = "XREFPX")
    private Integer xrefpx;
    @Column(name = "YREF")
    private Integer yref;
    @Column(name = "YREFPX")
    private Integer yrefpx;
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "idmap")
    private Collection<Node> nodeCollection;*/

    public Map() {
    }

    public Map(Integer idmap) {
        this.idmap = idmap;
    }

    public Map(Integer idmap, String mapname, double mapscale) {
        this.idmap = idmap;
        this.mapname = mapname;
        this.mapscale = mapscale;
    }

    public Integer getIdmap() {
        return idmap;
    }

    public void setIdmap(Integer idmap) {
        this.idmap = idmap;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getMapname() {
        return mapname;
    }

    public void setMapname(String mapname) {
        this.mapname = mapname;
    }

    public double getMapscale() {
        return mapscale;
    }

    public void setMapscale(double mapscale) {
        this.mapscale = mapscale;
    }

    public Integer getXref() {
        return xref;
    }

    public void setXref(Integer xref) {
        this.xref = xref;
    }

    public Integer getXrefpx() {
        return xrefpx;
    }

    public void setXrefpx(Integer xrefpx) {
        this.xrefpx = xrefpx;
    }

    public Integer getYref() {
        return yref;
    }

    public void setYref(Integer yref) {
        this.yref = yref;
    }

    public Integer getYrefpx() {
        return yrefpx;
    }

    public void setYrefpx(Integer yrefpx) {
        this.yrefpx = yrefpx;
    }

    /*@XmlTransient
    public Collection<Node> getNodeCollection() {
        return nodeCollection;
    }

    public void setNodeCollection(Collection<Node> nodeCollection) {
        this.nodeCollection = nodeCollection;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmap != null ? idmap.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Map)) {
            return false;
        }
        Map other = (Map) object;
        if ((this.idmap == null && other.idmap != null) || (this.idmap != null && !this.idmap.equals(other.idmap))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.idstid.group1.emergencynotifications.Map[ idmap=" + idmap + " ]";
    }

    @XmlTransient
    public Collection<Node> getNodeCollection() {
        return nodeCollection;
    }

    public void setNodeCollection(Collection<Node> nodeCollection) {
        this.nodeCollection = nodeCollection;
    }
    
}
