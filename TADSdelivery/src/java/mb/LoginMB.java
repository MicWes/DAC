/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Michael
 */
@Named(value = "loginMB")
@ManagedBean(name = "login")
@SessionScoped
public class LoginMB implements Serializable{

    /**
     * Creates a new instance of LoginMB
     */
    public LoginMB() {
    }
    
}
