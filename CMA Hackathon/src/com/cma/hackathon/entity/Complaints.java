/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cma.hackathon.entity;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yogamani Balusamy
 */
@Entity
@Table(name = "complaints")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Complaints.findAll", query = "SELECT c FROM Complaints c"),
    @NamedQuery(name = "Complaints.findById", query = "SELECT c FROM Complaints c WHERE c.id = :id"),
    @NamedQuery(name = "Complaints.findByTitle", query = "SELECT c FROM Complaints c WHERE c.title = :title"),
    @NamedQuery(name = "Complaints.findByDetails", query = "SELECT c FROM Complaints c WHERE c.details = :details"),
    @NamedQuery(name = "Complaints.findByArea", query = "SELECT c FROM Complaints c WHERE c.area = :area"),
    @NamedQuery(name = "Complaints.findByLocality", query = "SELECT c FROM Complaints c WHERE c.locality = :locality"),
    @NamedQuery(name = "Complaints.findByStreet", query = "SELECT c FROM Complaints c WHERE c.street = :street"),
    @NamedQuery(name = "Complaints.findByLandmark", query = "SELECT c FROM Complaints c WHERE c.landmark = :landmark"),
    @NamedQuery(name = "Complaints.findByMobile", query = "SELECT c FROM Complaints c WHERE c.mobile = :mobile"),
    @NamedQuery(name = "Complaints.findByName", query = "SELECT c FROM Complaints c WHERE c.name = :name"),
    @NamedQuery(name = "Complaints.findByAddress", query = "SELECT c FROM Complaints c WHERE c.address = :address"),
    @NamedQuery(name = "Complaints.findByPincode", query = "SELECT c FROM Complaints c WHERE c.pincode = :pincode"),
    @NamedQuery(name = "Complaints.findByEmail", query = "SELECT c FROM Complaints c WHERE c.email = :email"),
    @NamedQuery(name = "Complaints.findByFilename", query = "SELECT c FROM Complaints c WHERE c.filename = :filename")})
public class Complaints implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Column(name = "details")
    private String details;
    @Column(name = "area")
    private String area;
    @Column(name = "locality")
    private String locality;
    @Column(name = "street")
    private String street;
    @Column(name = "landmark")
    private String landmark;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "pincode")
    private String pincode;
    @Column(name = "email")
    private String email;
    @Column(name = "filename")
    private String filename;

    public Complaints() {
    }

    public Complaints(Integer id) {
        this.id = id;
    }

    public Complaints(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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
        if (!(object instanceof Complaints)) {
            return false;
        }
        Complaints other = (Complaints) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cma.hackathon.entity.Complaints[ id=" + id + " ]";
    }
    
}
