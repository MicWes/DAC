/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.hib.facade;

import com.ufpr.tads.dac.hib.dao.GenericDao;
import com.ufpr.tads.dac.model.ItemPedido;
import com.ufpr.tads.dac.model.Pedido;
import com.ufpr.tads.dac.model.Roupa;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Marcos
 */
public class PedidoFacade {

    public static Pedido getPedido(int pedido) {
        Pedido p = (Pedido) GenericDao.getByInt(Pedido.class, "id", pedido);
        List<ItemPedido> itens = (List<ItemPedido>) 
                GenericDao.getListByInt(ItemPedido.class, pedido, "idPedido", null);
        List<Roupa> roupas = (List<Roupa>) GenericDao.getList(Roupa.class, "id"); //ordena pelo id pra poder pegar pelo indice no for
        Map<Roupa, Integer> map = new HashMap<>();
        for (ItemPedido item : itens) {
            map.put(roupas.get(item.getIdRoupa()-1), item.getQtd());
        }
        p.setItens(map);
        return p;
    }

    public static List<Pedido> getListaPedidos() {
        return (List<Pedido>) GenericDao.getList(Pedido.class);
    }

    public static List<Pedido> getPedidosCliente(int id) {
        return (List<Pedido>) GenericDao.getListByInt(Pedido.class, id, "idCli", "horaPedido");
    }

}
