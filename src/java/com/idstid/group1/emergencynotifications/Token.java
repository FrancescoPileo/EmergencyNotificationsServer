/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idstid.group1.emergencynotifications;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kekko
 */
@Entity
@Table(name = "TOKEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Token.findAll", query = "SELECT t FROM Token t")
    , @NamedQuery(name = "Token.findByIdtoken", query = "SELECT t FROM Token t WHERE t.idtoken = :idtoken")
    , @NamedQuery(name = "Token.findByTokenvalue", query = "SELECT t FROM Token t WHERE t.tokenvalue = :tokenvalue")})
public class Token implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTOKEN")
    private Integer idtoken;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "TOKENVALUE")
    private String tokenvalue;

    public Token() {
    }

    public Token(Integer idtoken) {
        this.idtoken = idtoken;
    }

    public Token(Integer idtoken, String tokenvalue) {
        this.idtoken = idtoken;
        this.tokenvalue = tokenvalue;
    }

    public Integer getIdtoken() {
        return idtoken;
    }

    public void setIdtoken(Integer idtoken) {
        this.idtoken = idtoken;
    }

    public String getTokenvalue() {
        return tokenvalue;
    }

    public void setTokenvalue(String tokenvalue) {
        this.tokenvalue = tokenvalue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtoken != null ? idtoken.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Token)) {
            return false;
        }
        Token other = (Token) object;
        if ((this.idtoken == null && other.idtoken != null) || (this.idtoken != null && !this.idtoken.equals(other.idtoken))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.idstid.group1.emergencynotifications.Token[ idtoken=" + idtoken + " ]";
    }
    
}
