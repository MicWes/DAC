/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import facade.AbstractFacade;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import model.T05User;

/**
 *
 * @author Michael
 */
@Named(value = "loginMB")
@ManagedBean(name = "login")
@SessionScoped
public class LoginMB implements Serializable{
    private String login;
    private String password;
    private T05User gerente;
    private T05User entregador;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public T05User getGerente() {
        return gerente;
    }

    public void setGerente(T05User gerente) {
        this.gerente = gerente;
    }

    public T05User getEntregador() {
        return entregador;
    }

    public void setEntregador(T05User entregador) {
        this.entregador = entregador;
    }


    public boolean isLogado() {
        return (gerente != null || entregador != null);
    }
    
    public String autenticar() {
        //Object obj = AbstractFacade.autenticar(login, password);

        return "index";
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }
    /**
     * Creates a new instance of LoginMB
     */
    public LoginMB() {
    }
    
}
