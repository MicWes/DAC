/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Michael
 */
@Entity
@Table(name = "t06_delivery")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "T06Delivery.findAll", query = "SELECT t FROM T06Delivery t")
    , @NamedQuery(name = "T06Delivery.findByDeliveryId", query = "SELECT t FROM T06Delivery t WHERE t.deliveryId = :deliveryId")
    , @NamedQuery(name = "T06Delivery.findByDeliveryDescription", query = "SELECT t FROM T06Delivery t WHERE t.deliveryDescription = :deliveryDescription")
    , @NamedQuery(name = "T06Delivery.findByDeliveryLocal", query = "SELECT t FROM T06Delivery t WHERE t.deliveryLocal = :deliveryLocal")
    , @NamedQuery(name = "T06Delivery.findByDeliveryDestiny", query = "SELECT t FROM T06Delivery t WHERE t.deliveryDestiny = :deliveryDestiny")
    , @NamedQuery(name = "T06Delivery.findByDeliveryDtcad", query = "SELECT t FROM T06Delivery t WHERE t.deliveryDtcad = :deliveryDtcad")})
public class T06Delivery implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "delivery_id")
    private Integer deliveryId;
    @Size(max = 100)
    @Column(name = "delivery_description")
    private String deliveryDescription;
    @Size(max = 50)
    @Column(name = "delivery_local")
    private String deliveryLocal;
    @Size(max = 50)
    @Column(name = "delivery_destiny")
    private String deliveryDestiny;
    @Column(name = "delivery_dtcad")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDtcad;
    @JoinColumn(name = "delivery_status", referencedColumnName = "status_id")
    @ManyToOne
    private T01DeliveryStatus deliveryStatus;
    @JoinColumn(name = "delivery_owner", referencedColumnName = "user_id")
    @ManyToOne
    private T05User deliveryOwner;
    @OneToMany(mappedBy = "actionDelivery")
    private List<T07Action> t07ActionList;
    @OneToMany(mappedBy = "messageDelivery")
    private List<T08Message> t08MessageList;

    public T06Delivery() {
    }

    public T06Delivery(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Integer getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDeliveryDescription() {
        return deliveryDescription;
    }

    public void setDeliveryDescription(String deliveryDescription) {
        this.deliveryDescription = deliveryDescription;
    }

    public String getDeliveryLocal() {
        return deliveryLocal;
    }

    public void setDeliveryLocal(String deliveryLocal) {
        this.deliveryLocal = deliveryLocal;
    }

    public String getDeliveryDestiny() {
        return deliveryDestiny;
    }

    public void setDeliveryDestiny(String deliveryDestiny) {
        this.deliveryDestiny = deliveryDestiny;
    }

    public Date getDeliveryDtcad() {
        return deliveryDtcad;
    }

    public void setDeliveryDtcad(Date deliveryDtcad) {
        this.deliveryDtcad = deliveryDtcad;
    }

    public T01DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(T01DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public T05User getDeliveryOwner() {
        return deliveryOwner;
    }

    public void setDeliveryOwner(T05User deliveryOwner) {
        this.deliveryOwner = deliveryOwner;
    }

    @XmlTransient
    public List<T07Action> getT07ActionList() {
        return t07ActionList;
    }

    public void setT07ActionList(List<T07Action> t07ActionList) {
        this.t07ActionList = t07ActionList;
    }

    @XmlTransient
    public List<T08Message> getT08MessageList() {
        return t08MessageList;
    }

    public void setT08MessageList(List<T08Message> t08MessageList) {
        this.t08MessageList = t08MessageList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deliveryId != null ? deliveryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof T06Delivery)) {
            return false;
        }
        T06Delivery other = (T06Delivery) object;
        if ((this.deliveryId == null && other.deliveryId != null) || (this.deliveryId != null && !this.deliveryId.equals(other.deliveryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.T06Delivery[ deliveryId=" + deliveryId + " ]";
    }
    
}
