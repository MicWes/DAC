/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.hib.facade;

import com.ufpr.tads.dac.hib.dao.GenericDao;
import com.ufpr.tads.dac.model.Cliente;
import com.ufpr.tads.dac.model.Funcionario;
import com.ufpr.tads.dac.model.Pedido;
import com.ufpr.tads.dac.model.Roupa;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class SystemFacade {
 
    public void inserir (Object obj) {
        GenericDao.inserir(obj);
    }
    
    public void alterar (Object obj) {
        GenericDao.alterar(obj);
    }
    
    public void excluir (Object obj) {
        GenericDao.excluir(obj);
    }
    
    public List<Cliente> getListaClientes() {
        return (List<Cliente>) GenericDao.getList(Cliente.class, "nome");
    }
    
    public List<Funcionario> getListaFuncs() {
        return (List<Funcionario>) GenericDao.getList(Funcionario.class, "nome");
    }
    
    public List<Roupa> getListaRoupas() {
        return (List<Roupa>) GenericDao.getList(Roupa.class, "tipo");
    }
    
    public List<Pedido> getListaPedidos() {
        return (List<Pedido>) GenericDao.getList(Pedido.class);
    }
}
