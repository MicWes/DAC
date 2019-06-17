/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.beans;

import com.ufpr.tads.dac.hib.facade.SystemFacade;
import com.ufpr.tads.dac.model.Funcionario;
import com.ufpr.tads.dac.utils.Encriptador;
import com.ufpr.tads.dac.utils.Utils;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Marcos
 */
@ManagedBean(name = "cadFun")
@ViewScoped
public class CadastroFunMB {

    private Funcionario funcionario;

    @PostConstruct
    public void init() {
        funcionario = new Funcionario();
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String cadastrar() {
        funcionario.setSenha(Encriptador.toMD5(funcionario.getSenha()));
        if (SystemFacade.inserir(funcionario)) {
            Utils.message("Info", "Cadastrado com sucesso");
            return "pedidos";
        }
        return "";
    }
}
