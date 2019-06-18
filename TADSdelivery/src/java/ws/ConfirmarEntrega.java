/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.T06Delivery;
import org.json.simple.JSONObject;

/**
 *
 * @author Michael
 */

//puxa dados do LOL
public class ConfirmarEntrega {
    public static int confirmar(T06Delivery pedido){
       Client client = ClientBuilder.newClient();
       JSONObject j = new JSONObject();
       j.put("idPedido", pedido.getDeliveryId());
       j.put("info", pedido.getDeliveryStatus());
       WebTarget wt = client.target(
        "htt://172.20.118.180:8080/SistemaLOL/status");
       Invocation.Builder b = wt.request(MediaType.APPLICATION_JSON);
       Response resp = b.post(Entity.json(j.toJSONString()));
       return resp.getStatus();
    
    }
}