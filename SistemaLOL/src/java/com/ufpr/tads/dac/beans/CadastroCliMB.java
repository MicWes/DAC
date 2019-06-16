/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.beans;

import com.ufpr.tads.dac.hib.facade.SystemFacade;
import com.ufpr.tads.dac.model.Cidade;
import com.ufpr.tads.dac.model.Cliente;
import com.ufpr.tads.dac.model.Estado;
import com.ufpr.tads.dac.utils.Encriptador;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Marcos
 */
@ManagedBean(name = "cadCli")
@ViewScoped
public class CadastroCliMB implements Serializable {

    private Cliente cliente;
    private Map<String, Map<String, String>> data = new HashMap<>();
    private String estado;
    private String cidade;
    private Map<String, String> estados;
    private Map<String, String> cidades;
    private Map<String, Estado> estSelect;
    private Map<String, Cidade> cidSelect;
    
    @PostConstruct
    public void init() {
        cliente = new Cliente();
        estados = new HashMap<>();
        estSelect = new HashMap<>();
        cidSelect = new HashMap<>();
        List<Estado> listEst = SystemFacade.getListaEstados();
        for (Estado e : listEst) {
            estados.put(e.getDescricao(), e.getDescricao());
            estSelect.put(e.getDescricao(), e);
            Map<String, String> cidMap = new HashMap<>();
            for (Cidade c : e.getCidades()) {
                cidMap.put(c.getNome(), c.getNome());
                cidSelect.put(c.getNome(), c);
            }
            data.put(e.getDescricao(), cidMap);
        }
    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Map<String, String> getEstados() {
        return estados;
    }

    public Map<String, String> getCidades() {
        return cidades;
    }

    public void onCountryChange() {
        if (estado != null && !estado.equals("")) {
            cidades = data.get(estado);
        } else {
            cidades = new HashMap<String, String>();
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String cadastrar() {
        cliente.setSenha(Encriptador.toMD5(cliente.getSenha()));
        cliente.setCidade(cidSelect.get(cidade).getId());
        SystemFacade.inserir(cliente);
        FacesMessage msg = new FacesMessage("msg", "msg");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "index";
    }

}
