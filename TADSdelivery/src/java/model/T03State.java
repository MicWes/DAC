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
@Table(name = "t03_state")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "T03State.findAll", query = "SELECT t FROM T03State t")
    , @NamedQuery(name = "T03State.findByStateId", query = "SELECT t FROM T03State t WHERE t.stateId = :stateId")
    , @NamedQuery(name = "T03State.findByStateName", query = "SELECT t FROM T03State t WHERE t.stateName = :stateName")
    , @NamedQuery(name = "T03State.findByStateUf", query = "SELECT t FROM T03State t WHERE t.stateUf = :stateUf")})
public class T03State implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "state_id")
    private Integer stateId;
    @Size(max = 30)
    @Column(name = "state_name")
    private String stateName;
    @Size(max = 2)
    @Column(name = "state_uf")
    private String stateUf;
    @OneToMany(mappedBy = "userState")
    private List<T05User> t05UserList;
    @OneToMany(mappedBy = "cityState")
    private List<T04City> t04CityList;

    public T03State() {
    }

    public T03State(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateUf() {
        return stateUf;
    }

    public void setStateUf(String stateUf) {
        this.stateUf = stateUf;
    }

    @XmlTransient
    public List<T05User> getT05UserList() {
        return t05UserList;
    }

    public void setT05UserList(List<T05User> t05UserList) {
        this.t05UserList = t05UserList;
    }

    @XmlTransient
    public List<T04City> getT04CityList() {
        return t04CityList;
    }

    public void setT04CityList(List<T04City> t04CityList) {
        this.t04CityList = t04CityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stateId != null ? stateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof T03State)) {
            return false;
        }
        T03State other = (T03State) object;
        if ((this.stateId == null && other.stateId != null) || (this.stateId != null && !this.stateId.equals(other.stateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.T03State[ stateId=" + stateId + " ]";
    }
    
}
