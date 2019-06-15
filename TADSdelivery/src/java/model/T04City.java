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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "t04_city")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "T04City.findAll", query = "SELECT t FROM T04City t")
    , @NamedQuery(name = "T04City.findByCityId", query = "SELECT t FROM T04City t WHERE t.cityId = :cityId")
    , @NamedQuery(name = "T04City.findByCityName", query = "SELECT t FROM T04City t WHERE t.cityName = :cityName")
    , @NamedQuery(name = "T04City.findByCityUf", query = "SELECT t FROM T04City t WHERE t.cityUf = :cityUf")})
public class T04City implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "city_id")
    private Integer cityId;
    @Size(max = 30)
    @Column(name = "city_name")
    private String cityName;
    @Column(name = "city_uf")
    private Integer cityUf;
    @OneToMany(mappedBy = "userCity")
    private List<T05User> t05UserList;
    @JoinColumn(name = "city_state", referencedColumnName = "state_id")
    @ManyToOne
    private T03State cityState;

    public T04City() {
    }

    public T04City(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getCityUf() {
        return cityUf;
    }

    public void setCityUf(Integer cityUf) {
        this.cityUf = cityUf;
    }

    @XmlTransient
    public List<T05User> getT05UserList() {
        return t05UserList;
    }

    public void setT05UserList(List<T05User> t05UserList) {
        this.t05UserList = t05UserList;
    }

    public T03State getCityState() {
        return cityState;
    }

    public void setCityState(T03State cityState) {
        this.cityState = cityState;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cityId != null ? cityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof T04City)) {
            return false;
        }
        T04City other = (T04City) object;
        if ((this.cityId == null && other.cityId != null) || (this.cityId != null && !this.cityId.equals(other.cityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.T04City[ cityId=" + cityId + " ]";
    }
    
}
