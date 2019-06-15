package controller;

import model.T02UserType;
import controller.util.ControllerUtil;
import controller.util.ControllerUtil.PersistAction;
import facade.T02UserTypeFacade;

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

@Named("t02UserTypeController")
@SessionScoped
public class T02UserTypeController implements Serializable {

    @EJB
    private facade.T02UserTypeFacade ejbFacade;
    private List<T02UserType> items = null;
    private T02UserType selected;

    public T02UserTypeController() {
    }

    public T02UserType getSelected() {
        return selected;
    }

    public void setSelected(T02UserType selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private T02UserTypeFacade getFacade() {
        return ejbFacade;
    }

    public T02UserType prepareCreate() {
        selected = new T02UserType();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("T02UserTypeCreated"));
        if (!ControllerUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("T02UserTypeUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("T02UserTypeDeleted"));
        if (!ControllerUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<T02UserType> getItems() {
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

    public T02UserType getT02UserType(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<T02UserType> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<T02UserType> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = T02UserType.class)
    public static class T02UserTypeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            T02UserTypeController controller = (T02UserTypeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "t02UserTypeController");
            return controller.getT02UserType(getKey(value));
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
            if (object instanceof T02UserType) {
                T02UserType o = (T02UserType) object;
                return getStringKey(o.getUsertypeId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), T02UserType.class.getName()});
                return null;
            }
        }

    }

}
