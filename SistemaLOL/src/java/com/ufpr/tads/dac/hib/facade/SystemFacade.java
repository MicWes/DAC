/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.hib.facade;

import com.ufpr.tads.dac.hib.dao.GenericDao;
import com.ufpr.tads.dac.hib.dao.LoginDao;
import com.ufpr.tads.dac.model.Cidade;
import com.ufpr.tads.dac.model.Cliente;
import com.ufpr.tads.dac.model.Estado;
import com.ufpr.tads.dac.model.Funcionario;
import com.ufpr.tads.dac.model.Roupa;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class SystemFacade {

    public static void inserir(Object obj) {
        GenericDao.inserir(obj);
    }

    public static void alterar(Object obj) {
        GenericDao.alterar(obj);
    }

    public static void excluir(Object obj) {
        GenericDao.excluir(obj);
    }

    public static List<Cliente> getListaClientes() {
        return (List<Cliente>) GenericDao.getList(Cliente.class, "nome");
    }

    public static List<Funcionario> getListaFuncs() {
        return (List<Funcionario>) GenericDao.getList(Funcionario.class, "nome");
    }

    public static List<Roupa> getListaRoupas() {
        return (List<Roupa>) GenericDao.getList(Roupa.class);
    }

    public static List<Estado> getListaEstados() {
        return (List<Estado>) GenericDao.getList(Estado.class);
    }

    public static Cidade getCidade(int idCid) {
        return (Cidade) GenericDao.getByInt(Cidade.class, "id", idCid);
    }

    public static Object autenticar(String usuario, String senha) {
        LoginDao ld = new LoginDao();
        return ld.auth(usuario, senha);
    }

    public static String getEndereco(int idCli) {
        String end = "";
        Cliente c = (Cliente) GenericDao.getByInt(Cliente.class, "id", idCli);
        if (c != null) {
            end = c.getEndereco();
            Cidade cid = getCidade(c.getCidade());
            if (cid != null) {
                end += ", " + cid.getNome() + " - " + cid.getEstado().getSigla();
            }
            return end;
        }
        return end;
    }
}
