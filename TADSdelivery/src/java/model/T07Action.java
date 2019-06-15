/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Michael
 */
@Entity
@Table(name = "t07_action")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "T07Action.findAll", query = "SELECT t FROM T07Action t")
    , @NamedQuery(name = "T07Action.findByActionId", query = "SELECT t FROM T07Action t WHERE t.actionId = :actionId")
    , @NamedQuery(name = "T07Action.findByActionDescription", query = "SELECT t FROM T07Action t WHERE t.actionDescription = :actionDescription")
    , @NamedQuery(name = "T07Action.findByActionDt", query = "SELECT t FROM T07Action t WHERE t.actionDt = :actionDt")})
public class T07Action implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "action_id")
    private Integer actionId;
    @Size(max = 100)
    @Column(name = "action_description")
    private String actionDescription;
    @Column(name = "action_dt")
    @Temporal(TemporalType.DATE)
    private Date actionDt;
    @JoinColumn(name = "action_user", referencedColumnName = "user_id")
    @ManyToOne
    private T05User actionUser;
    @JoinColumn(name = "action_delivery", referencedColumnName = "delivery_id")
    @ManyToOne
    private T06Delivery actionDelivery;

    public T07Action() {
    }

    public T07Action(Integer actionId) {
        this.actionId = actionId;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public String getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
    }

    public Date getActionDt() {
        return actionDt;
    }

    public void setActionDt(Date actionDt) {
        this.actionDt = actionDt;
    }

    public T05User getActionUser() {
        return actionUser;
    }

    public void setActionUser(T05User actionUser) {
        this.actionUser = actionUser;
    }

    public T06Delivery getActionDelivery() {
        return actionDelivery;
    }

    public void setActionDelivery(T06Delivery actionDelivery) {
        this.actionDelivery = actionDelivery;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actionId != null ? actionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof T07Action)) {
            return false;
        }
        T07Action other = (T07Action) object;
        if ((this.actionId == null && other.actionId != null) || (this.actionId != null && !this.actionId.equals(other.actionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.T07Action[ actionId=" + actionId + " ]";
    }
    
}
