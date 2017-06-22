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
@Table(name = "NODE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Node.findAll", query = "SELECT n FROM Node n")
    , @NamedQuery(name = "Node.findByIdnode", query = "SELECT n FROM Node n WHERE n.idnode = :idnode")
    , @NamedQuery(name = "Node.findByNodename", query = "SELECT n FROM Node n WHERE n.nodename = :nodename")
    , @NamedQuery(name = "Node.findByX", query = "SELECT n FROM Node n WHERE n.x = :x")
    , @NamedQuery(name = "Node.deleteAll", query = "DELETE FROM Node WHERE 1=1")
    , @NamedQuery(name = "Node.findByY", query = "SELECT n FROM Node n WHERE n.y = :y")})
public class Node implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDNODE")
    private Integer idnode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "NODENAME")
    private String nodename;
    @Basic(optional = false)
    @NotNull
    @Column(name = "X")
    private int x;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Y")
    private int y;
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "idnode")
    private Collection<Userposition> userpositionCollection;*/
    @JoinColumn(name = "IDMAP", referencedColumnName = "IDMAP")
    @ManyToOne(optional = false)
    private Map idmap;
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "idnode")
    private Collection<Beacon> beaconCollection;*/

    public Node() {
    }

    public Node(Integer idnode) {
        this.idnode = idnode;
    }

    public Node(Integer idnode, String nodename, int x, int y) {
        this.idnode = idnode;
        this.nodename = nodename;
        this.x = x;
        this.y = y;
    }

    public Integer getIdnode() {
        return idnode;
    }

    public void setIdnode(Integer idnode) {
        this.idnode = idnode;
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /*@XmlTransient
    public Collection<Userposition> getUserpositionCollection() {
        return userpositionCollection;
    }

    public void setUserpositionCollection(Collection<Userposition> userpositionCollection) {
        this.userpositionCollection = userpositionCollection;
    }*/

    public Map getIdmap() {
        return idmap;
    }

    public void setIdmap(Map idmap) {
        this.idmap = idmap;
    }

    /*@XmlTransient
    public Collection<Beacon> getBeaconCollection() {
        return beaconCollection;
    }

    public void setBeaconCollection(Collection<Beacon> beaconCollection) {
        this.beaconCollection = beaconCollection;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnode != null ? idnode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Node)) {
            return false;
        }
        Node other = (Node) object;
        if ((this.idnode == null && other.idnode != null) || (this.idnode != null && !this.idnode.equals(other.idnode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Node[ idnode=" + idnode
                + ", name=" + nodename 
                + ", x=" +  x  
                + ", y=" + y 
                + ", map= " + idmap.getMapname()  + " ]";
    }
    
}
