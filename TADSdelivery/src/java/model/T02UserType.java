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
@Table(name = "t02_user_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "T02UserType.findAll", query = "SELECT t FROM T02UserType t")
    , @NamedQuery(name = "T02UserType.findByUsertypeId", query = "SELECT t FROM T02UserType t WHERE t.usertypeId = :usertypeId")
    , @NamedQuery(name = "T02UserType.findByUsertypeName", query = "SELECT t FROM T02UserType t WHERE t.usertypeName = :usertypeName")})
public class T02UserType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usertype_id")
    private Integer usertypeId;
    @Size(max = 10)
    @Column(name = "usertype_name")
    private String usertypeName;
    @OneToMany(mappedBy = "userType")
    private List<T05User> t05UserList;

    public T02UserType() {
    }

    public T02UserType(Integer usertypeId) {
        this.usertypeId = usertypeId;
    }

    public Integer getUsertypeId() {
        return usertypeId;
    }

    public void setUsertypeId(Integer usertypeId) {
        this.usertypeId = usertypeId;
    }

    public String getUsertypeName() {
        return usertypeName;
    }

    public void setUsertypeName(String usertypeName) {
        this.usertypeName = usertypeName;
    }

    @XmlTransient
    public List<T05User> getT05UserList() {
        return t05UserList;
    }

    public void setT05UserList(List<T05User> t05UserList) {
        this.t05UserList = t05UserList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usertypeId != null ? usertypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof T02UserType)) {
            return false;
        }
        T02UserType other = (T02UserType) object;
        if ((this.usertypeId == null && other.usertypeId != null) || (this.usertypeId != null && !this.usertypeId.equals(other.usertypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.T02UserType[ usertypeId=" + usertypeId + " ]";
    }
    
}
