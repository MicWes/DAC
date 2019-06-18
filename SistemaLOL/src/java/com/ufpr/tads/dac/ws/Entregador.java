/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.ws;

import com.ufpr.tads.dac.model.Pedido;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;

/**
 *
 * @author Marcos
 */
public class Entregador {

    public static int solicitarEntrega(Pedido pedido) {

        Client client = ClientBuilder.newClient();
        JSONObject j = new JSONObject();
        j.put("idPedido", pedido.getId());
        j.put("endereco", pedido.getEndereco());
        j.put("qtdItens", pedido.getQtdItens());
        WebTarget wt = client.target(
                "http://192.168.43.31:8080/TADSdelivery/webresources/delivery");
        Invocation.Builder b = wt.request(MediaType.APPLICATION_JSON);
        Response response = b.post(Entity.json(j.toJSONString()));

        return response.getStatus();
    }
}
