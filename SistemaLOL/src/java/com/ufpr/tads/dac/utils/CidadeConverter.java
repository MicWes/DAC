/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.utils;

import com.ufpr.tads.dac.hib.dao.GenericDao;
import com.ufpr.tads.dac.model.Cidade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("cidadeConverter")
public class CidadeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        return (Cidade) GenericDao.getByInt(Cidade.class, "id", Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        //Cidade c = (Cidade) GenericDao.getBy(Cidade.class, "id", (int) value);
        return String.valueOf(((Cidade) value).getId());
    }
}
