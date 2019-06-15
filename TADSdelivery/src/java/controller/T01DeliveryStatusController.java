package controller;

import model.T01DeliveryStatus;
import controller.util.ControllerUtil;
import controller.util.ControllerUtil.PersistAction;
import facade.T01DeliveryStatusFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("t01DeliveryStatusController")
@SessionScoped
public class T01DeliveryStatusController implements Serializable {

    @EJB
    private facade.T01DeliveryStatusFacade ejbFacade;
    private List<T01DeliveryStatus> items = null;
    private T01DeliveryStatus selected;

    public T01DeliveryStatusController() {
    }

    public T01DeliveryStatus getSelected() {
        return selected;
    }

    public void setSelected(T01DeliveryStatus selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private T01DeliveryStatusFacade getFacade() {
        return ejbFacade;
    }

    public T01DeliveryStatus prepareCreate() {
        selected = new T01DeliveryStatus();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("T01DeliveryStatusCreated"));
        if (!ControllerUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("T01DeliveryStatusUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("T01DeliveryStatusDeleted"));
        if (!ControllerUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<T01DeliveryStatus> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                ControllerUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    ControllerUtil.addErrorMessage(msg);
                } else {
                    ControllerUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                ControllerUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public T01DeliveryStatus getT01DeliveryStatus(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<T01DeliveryStatus> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<T01DeliveryStatus> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = T01DeliveryStatus.class)
    public static class T01DeliveryStatusControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            T01DeliveryStatusController controller = (T01DeliveryStatusController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "t01DeliveryStatusController");
            return controller.getT01DeliveryStatus(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof T01DeliveryStatus) {
                T01DeliveryStatus o = (T01DeliveryStatus) object;
                return getStringKey(o.getStatusId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), T01DeliveryStatus.class.getName()});
                return null;
            }
        }

    }

}
