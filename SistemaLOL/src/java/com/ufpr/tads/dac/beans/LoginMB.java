/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.beans;

import com.ufpr.tads.dac.hib.facade.SystemFacade;
import com.ufpr.tads.dac.model.Cliente;
import com.ufpr.tads.dac.model.Funcionario;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Marcos
 */
@ManagedBean(name = "login")
@SessionScoped
public class LoginMB implements Serializable {

    private String usuario = "";
    private String senha = "";
    private Funcionario funcionario;
    private Cliente cliente;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public Funcionario getFuncionario() {
        return funcionario;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public boolean isLogado() {
        return (funcionario != null || cliente != null);
    }
    
    public String autenticar() {
        Object obj = SystemFacade.autenticar(usuario, senha);
        if (obj instanceof Funcionario) {
            funcionario = (Funcionario) obj;
            return "pedidos_func";
        } else if (obj instanceof Cliente) {
            cliente = (Cliente) obj;
            return "pedidos_cli";
        }
        return "index";
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }
}
