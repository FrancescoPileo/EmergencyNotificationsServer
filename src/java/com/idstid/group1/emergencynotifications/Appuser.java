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
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kekko
 */
@Entity
@Table(name = "APPUSER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appuser.findAll", query = "SELECT a FROM Appuser a")
    , @NamedQuery(name = "Appuser.findByIduser", query = "SELECT a FROM Appuser a WHERE a.iduser = :iduser")
    , @NamedQuery(name = "Appuser.findByName", query = "SELECT a FROM Appuser a WHERE a.name = :name")
    , @NamedQuery(name = "Appuser.findBySurname", query = "SELECT a FROM Appuser a WHERE a.surname = :surname")
    , @NamedQuery(name = "Appuser.findByUsername", query = "SELECT a FROM Appuser a WHERE a.username = :username")
    , @NamedQuery(name = "Appuser.findByAge", query = "SELECT a FROM Appuser a WHERE a.age = :age")
    , @NamedQuery(name = "Appuser.findByMobilephone", query = "SELECT a FROM Appuser a WHERE a.mobilephone = :mobilephone")
    , @NamedQuery(name = "Appuser.findByEmail", query = "SELECT a FROM Appuser a WHERE a.email = :email")
    , @NamedQuery(name = "Appuser.findByPassword", query = "SELECT a FROM Appuser a WHERE a.password = :password")
    , @NamedQuery(name = "Appuser.findByIsguest", query = "SELECT a FROM Appuser a WHERE a.isguest = :isguest")})
public class Appuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDUSER")
    private Integer iduser;
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    @Size(max = 50)
    @Column(name = "SURNAME")
    private String surname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "AGE")
    private Integer age;
    @Size(max = 20)
    @Column(name = "MOBILEPHONE")
    private String mobilephone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 20)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ISGUEST")
    private Boolean isguest;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iduser")
    private Collection<Userposition> userpositionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "username")
    private Collection<Appsession> appsessionCollection;

    public Appuser() {
    }

    public Appuser(Integer iduser) {
        this.iduser = iduser;
    }

    public Appuser(Integer iduser, String username, Boolean isguest) {
        this.iduser = iduser;
        this.username = username;
        this.isguest = isguest;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsguest() {
        return isguest;
    }

    public void setIsguest(Boolean isguest) {
        this.isguest = isguest;
    }

    @XmlTransient
    public Collection<Userposition> getUserpositionCollection() {
        return userpositionCollection;
    }

    public void setUserpositionCollection(Collection<Userposition> userpositionCollection) {
        this.userpositionCollection = userpositionCollection;
    }

    @XmlTransient
    public Collection<Appsession> getAppsessionCollection() {
        return appsessionCollection;
    }

    public void setAppsessionCollection(Collection<Appsession> appsessionCollection) {
        this.appsessionCollection = appsessionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iduser != null ? iduser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appuser)) {
            return false;
        }
        Appuser other = (Appuser) object;
        if ((this.iduser == null && other.iduser != null) || (this.iduser != null && !this.iduser.equals(other.iduser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.idstid.group1.emergencynotifications.Appuser[ iduser=" + iduser + " ]";
    }
    
}
