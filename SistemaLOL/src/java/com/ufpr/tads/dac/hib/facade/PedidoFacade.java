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
import com.ufpr.tads.dac.model.Status;
import com.ufpr.tads.dac.ws.Entregador;
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
        List<ItemPedido> itens = (List<ItemPedido>) GenericDao.getListByInt(ItemPedido.class, pedido, "idPedido", null);
        List<Roupa> roupas = (List<Roupa>) GenericDao.getList(Roupa.class, "id");
        Map<Integer, Roupa> mapRoupas = new HashMap<>();
        for (Roupa r : roupas) {
            mapRoupas.put(r.getId(), r);
        }
        Map<Roupa, Integer> map = new HashMap<>();
        for (ItemPedido item : itens) {
            map.put(mapRoupas.get(item.getIdRoupa()), item.getQtd());
        }
        p.setItens(map);
        return p;
    }

    public static List<Pedido> getListaPedidos() {
        return (List<Pedido>) GenericDao.getList(Pedido.class, "horaPedido");
    }

    public static List<Pedido> getPedidosCliente(int id) {
        return (List<Pedido>) GenericDao.getListByInt(Pedido.class, id, "idCli", "horaPedido");
    }

    public static boolean inserirPedido(Pedido pedido) {
        int idPedido = GenericDao.inserir(pedido);
        if (idPedido > 0) {
            for (ItemPedido ip : pedido.getItensPedido()) {
                ip.setIdPedido(idPedido);
                int ok = GenericDao.inserir(ip);
            }
            return true;
        }
        return false;
    }

    public static void atualizaPedido(int idPedido, boolean entregue) {
        Pedido p = (Pedido) GenericDao.getByInt(Pedido.class, "id", idPedido);
        if (entregue) {
            p.setStatus(Status.CONCLU.getNum());
            GenericDao.alterar(p);
        } else {
            p.setStatus(Status.PROBLE.getNum());
            GenericDao.alterar(p);
        }
    }

    public static String solicitarEntrega(Pedido pedido) {
        String endereco = SystemFacade.getEndereco(pedido.getIdCli());
        if (!"".equals(endereco)) {
            pedido.setEndereco(endereco);
            int result = Entregador.solicitarEntrega(pedido);
            if (result == 200) {
                pedido.setStatus(Status.ENT_SOL.getNum());
                int v = GenericDao.alterar(pedido);
                if (v == 1) {
                    return "Pedido de entrega solicitado";
                }
            } else {
                return "Falha ao solicitar entrega";
            }
        } else {
            return "Endereço inválido, entre em contato com o cliente";
        }
        return "Falha ao solicitar entrega";
    }
}
