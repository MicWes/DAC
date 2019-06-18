/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import model.T06Delivery;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * REST Web Service
 *
 * @author Michael
 */
@Path("delivery")
public class DeliveryResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DeliveryResource
     */
    public DeliveryResource() {
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String recebePedido(String pedido){
        if (pedido != null) {
            JSONParser parser = new JSONParser();
            JSONObject j;
            try {
                j = (JSONObject) parser.parse(pedido);
                T06Delivery p = new T06Delivery();
                p.setDeliveryDestiny((String) j.get("endereco"));
                p.setDeliveryId(((Long) j.get("idPedido")).intValue());

                return "Pedido recebido!";
            } catch (ParseException ex) {
                return "Não conseguimos interpretar seu request";
            }
        } else {
            return "Não recebemos nada em seu request";
        }
    }
}
