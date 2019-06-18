/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.ws;

import com.ufpr.tads.dac.hib.facade.PedidoFacade;
import com.ufpr.tads.dac.utils.Utils;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.primefaces.json.JSONObject;

/**
 * REST Web Service
 *
 * @author Marcos
 */
@Path("status")
public class StatusResource {

    @Context
    private UriInfo context;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String atualizaStatus(String mensagem) {
        if (mensagem != null) {
            JSONParser parser = new JSONParser();
            JSONObject j;
            try {
                j = (JSONObject) parser.parse(mensagem);
                int idPedido = ((Long) j.get("idPedido")).intValue();
                String info = (String) j.get("info");
                Utils.message("Info", "TADS Delivery diz: Pedido Nº" + idPedido + " " + info);
                if ("Entregue".equals(info)) {
                    PedidoFacade.atualizaPedido(idPedido, true);
                } else {
                    PedidoFacade.atualizaPedido(idPedido, false);
                }
                return "Pedido recebido!";
            } catch (ParseException ex) {
                return "Não conseguimos interpretar seu request";
            }
        } else {
            return "Não recebemos nada em seu request";
        }
    }
}
