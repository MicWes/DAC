/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Michael
 */
@Entity
@Table(name="state_04")
@SequenceGenerator(name="sequencia", sequenceName="state_id")
public class State {
    private long id;
    private String name;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequencia")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
