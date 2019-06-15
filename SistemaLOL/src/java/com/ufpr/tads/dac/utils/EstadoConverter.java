/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.utils;

import com.ufpr.tads.dac.hib.dao.GenericDao;
import com.ufpr.tads.dac.model.Estado;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("estadoConverter")
public class EstadoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        return (Estado) GenericDao.getBy(Estado.class, "sigla", (String) value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        return ((Estado) value).getSigla();
    }
    
}

