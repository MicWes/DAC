/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Michael
 */
@Entity
@Table(name = "t08_message")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "T08Message.findAll", query = "SELECT t FROM T08Message t")
    , @NamedQuery(name = "T08Message.findByMessageId", query = "SELECT t FROM T08Message t WHERE t.messageId = :messageId")
    , @NamedQuery(name = "T08Message.findByMessageDescription", query = "SELECT t FROM T08Message t WHERE t.messageDescription = :messageDescription")})
public class T08Message implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "message_id")
    private Integer messageId;
    @Size(max = 100)
    @Column(name = "message_description")
    private String messageDescription;
    @JoinColumn(name = "message_user", referencedColumnName = "user_id")
    @ManyToOne
    private T05User messageUser;
    @JoinColumn(name = "message_delivery", referencedColumnName = "delivery_id")
    @ManyToOne
    private T06Delivery messageDelivery;

    public T08Message() {
    }

    public T08Message(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageDescription() {
        return messageDescription;
    }

    public void setMessageDescription(String messageDescription) {
        this.messageDescription = messageDescription;
    }

    public T05User getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(T05User messageUser) {
        this.messageUser = messageUser;
    }

    public T06Delivery getMessageDelivery() {
        return messageDelivery;
    }

    public void setMessageDelivery(T06Delivery messageDelivery) {
        this.messageDelivery = messageDelivery;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messageId != null ? messageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof T08Message)) {
            return false;
        }
        T08Message other = (T08Message) object;
        if ((this.messageId == null && other.messageId != null) || (this.messageId != null && !this.messageId.equals(other.messageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.T08Message[ messageId=" + messageId + " ]";
    }
    
}
