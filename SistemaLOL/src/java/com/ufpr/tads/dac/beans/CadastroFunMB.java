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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private String datanasc;

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

    public String getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(String datanasc) {
        this.datanasc = datanasc;
    }

    public String cadastrar() {
        funcionario.setSenha(Encriptador.toMD5(funcionario.getSenha()));
        Date data = null;
        try {
            data = new SimpleDateFormat("dd/MM/yyyy").parse(datanasc);
        } catch (ParseException ex) {
            Utils.message("Informe uma data válida!");
        }
        if (data != null) {
            funcionario.setDataNasc(data);
            if (SystemFacade.inserirFuncionario(funcionario)) {
                return "pedidos";
            }
        }
        return "";
    }
}
