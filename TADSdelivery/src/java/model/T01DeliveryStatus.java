/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Michael
 */
@Entity
@Table(name = "t01_delivery_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "T01DeliveryStatus.findAll", query = "SELECT t FROM T01DeliveryStatus t")
    , @NamedQuery(name = "T01DeliveryStatus.findByStatusId", query = "SELECT t FROM T01DeliveryStatus t WHERE t.statusId = :statusId")
    , @NamedQuery(name = "T01DeliveryStatus.findByNameId", query = "SELECT t FROM T01DeliveryStatus t WHERE t.nameId = :nameId")})
public class T01DeliveryStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "status_id")
    private Integer statusId;
    @Size(max = 20)
    @Column(name = "name_id")
    private String nameId;
    @OneToMany(mappedBy = "deliveryStatus")
    private List<T06Delivery> t06DeliveryList;

    public T01DeliveryStatus() {
    }

    public T01DeliveryStatus(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getNameId() {
        return nameId;
    }

    public void setNameId(String nameId) {
        this.nameId = nameId;
    }

    @XmlTransient
    public List<T06Delivery> getT06DeliveryList() {
        return t06DeliveryList;
    }

    public void setT06DeliveryList(List<T06Delivery> t06DeliveryList) {
        this.t06DeliveryList = t06DeliveryList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (statusId != null ? statusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof T01DeliveryStatus)) {
            return false;
        }
        T01DeliveryStatus other = (T01DeliveryStatus) object;
        if ((this.statusId == null && other.statusId != null) || (this.statusId != null && !this.statusId.equals(other.statusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.T01DeliveryStatus[ statusId=" + statusId + " ]";
    }
    
}
