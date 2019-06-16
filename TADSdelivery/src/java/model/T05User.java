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
import util.Encriptador;

/**
 *
 * @author Michael
 */
@Entity
@Table(name = "t05_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "T05User.findAll", query = "SELECT t FROM T05User t")
    , @NamedQuery(name = "T05User.findByUserId", query = "SELECT t FROM T05User t WHERE t.userId = :userId")
    , @NamedQuery(name = "T05User.findByUserName", query = "SELECT t FROM T05User t WHERE t.userName = :userName")
    , @NamedQuery(name = "T05User.findByUserCpf", query = "SELECT t FROM T05User t WHERE t.userCpf = :userCpf")
    , @NamedQuery(name = "T05User.findByUserLogin", query = "SELECT t FROM T05User t WHERE t.userLogin = :userLogin")
    , @NamedQuery(name = "T05User.findByUserAdress", query = "SELECT t FROM T05User t WHERE t.userAdress = :userAdress")
    , @NamedQuery(name = "T05User.findByUserPhone", query = "SELECT t FROM T05User t WHERE t.userPhone = :userPhone")
    , @NamedQuery(name = "T05User.findByUserEmail", query = "SELECT t FROM T05User t WHERE t.userEmail = :userEmail")
    , @NamedQuery(name = "T05User.findByUserPassword", query = "SELECT t FROM T05User t WHERE t.userPassword = :userPassword")
    , @NamedQuery(name = "T05User.findByUserLoginPassword", query = "SELECT t FROM T05User t WHERE t.userLogin = :userLogin and t.userPassword = :userPassword")})
public class T05User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Size(max = 100)
    @Column(name = "user_name")
    private String userName;
    @Size(max = 11)
    @Column(name = "user_cpf")
    private String userCpf;
    @Size(max = 30)
    @Column(name = "user_login")
    private String userLogin;
    @Size(max = 30)
    @Column(name = "user_adress")
    private String userAdress;
    @Size(max = 10)
    @Column(name = "user_phone")
    private String userPhone;
    @Size(max = 30)
    @Column(name = "user_email")
    private String userEmail;
    @Size(max = 50)
    @Column(name = "user_password")
    private String userPassword;

    @OneToMany(mappedBy = "deliveryOwner")
    private List<T06Delivery> t06DeliveryList;
    @JoinColumn(name = "user_type", referencedColumnName = "usertype_id")
    @ManyToOne
    private T02UserType userType;
    @JoinColumn(name = "user_state", referencedColumnName = "state_id")
    @ManyToOne
    private T03State userState;
    @JoinColumn(name = "user_city", referencedColumnName = "city_id")
    @ManyToOne
    private T04City userCity;
    @OneToMany(mappedBy = "actionUser")
    private List<T07Action> t07ActionList;
    @OneToMany(mappedBy = "messageUser")
    private List<T08Message> t08MessageList;

    public T05User() {
    }
    
    public T05User(String userLogin, String userPassword) {
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }

    public T05User(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCpf() {
        return userCpf;
    }

    public void setUserCpf(String userCpf) {
        this.userCpf = userCpf;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserAdress() {
        return userAdress;
    }

    public void setUserAdress(String userAdress) {
        this.userAdress = userAdress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        userPassword = Encriptador.toMD5(userPassword);
        this.userPassword = userPassword;
    }

    @XmlTransient
    public List<T06Delivery> getT06DeliveryList() {
        return t06DeliveryList;
    }

    public void setT06DeliveryList(List<T06Delivery> t06DeliveryList) {
        this.t06DeliveryList = t06DeliveryList;
    }

    public T02UserType getUserType() {
        return userType;
    }

    public void setUserType(T02UserType userType) {
        this.userType = userType;
    }

    public T03State getUserState() {
        return userState;
    }

    public void setUserState(T03State userState) {
        this.userState = userState;
    }

    public T04City getUserCity() {
        return userCity;
    }

    public void setUserCity(T04City userCity) {
        this.userCity = userCity;
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
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof T05User)) {
            return false;
        }
        T05User other = (T05User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.T05User[ userId=" + userId + " ]";
    }

}
