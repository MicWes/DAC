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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Marcos
 */
@ManagedBean(name = "cadCli")
@ViewScoped
public class CadastroCliMB implements Serializable {
    
    private Cliente cliente;
    private Estado estSelect;
    private Cidade cidSelect;
    private List<Estado> estados;
    private List<Cidade> cidades;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Estado getEstSelect() {
        return estSelect;
    }

    public void setEstSelect(Estado estSelect) {
        this.estSelect = estSelect;
    }

    public Cidade getCidSelect() {
        return cidSelect;
    }

    public void setCidSelect(Cidade cidSelect) {
        this.cidSelect = cidSelect;
    }
    
    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }
    
    @PostConstruct
    public void init() {
        cliente = new Cliente();
        estados = SystemFacade.getListaEstados();
        estSelect = estados.get(0);
        cidades = SystemFacade.getListaCidades(0);
    }
    
    public void carregaCidade() {
        cidades = SystemFacade.getListaCidades(estSelect.getId());
    }

    
    public String cadastrar() {
        cliente.setSenha(Encriptador.toMD5(cliente.getSenha()));
        cliente.setCidade(cidSelect.getId());
        SystemFacade.inserir(cliente);
        return "index";
    }
    
}
