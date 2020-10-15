/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbbeans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author serge
 */
@Entity
@Table(name = "prieteni")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prieteni.findAll", query = "SELECT p FROM Prieteni p"),
    @NamedQuery(name = "Prieteni.findById", query = "SELECT p FROM Prieteni p WHERE p.id = :id"),
    @NamedQuery(name = "Prieteni.findByNume", query = "SELECT p FROM Prieteni p WHERE p.nume = :nume"),
    @NamedQuery(name = "Prieteni.findByUser", query = "SELECT p FROM Prieteni p WHERE p.user = :user")})
public class Prieteni implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Lob
    @Column(name = "nume")
    private String nume;
    @Basic(optional = false)
    @Column(name = "user")
    private int user;

    public Prieteni() {
    }

    public Prieteni(Integer id) {
        this.id = id;
    }

    public Prieteni(Integer id, String nume, int user) {
        this.id = id;
        this.nume = nume;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prieteni)) {
            return false;
        }
        Prieteni other = (Prieteni) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dbbeans.Prieteni[ id=" + id + " ]";
    }
    
}
