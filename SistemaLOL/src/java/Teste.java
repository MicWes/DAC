/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.ufpr.tads.dac.hib.HibernateUtil;
import com.ufpr.tads.dac.model.Cidade;
import com.ufpr.tads.dac.model.Cliente;
import com.ufpr.tads.dac.model.Estado;
import com.ufpr.tads.dac.model.Pedido;
import com.ufpr.tads.dac.model.Roupa;
import java.sql.Date;
import org.hibernate.Session;

/**
 *
 * @author Marcos
 */
public class Teste {

    public static void main(String[] args) {
        /*Cliente c = new Cliente();
        c.setCpf("10674027990");
        c.setEmail("marcoshbastos@gmail.com");
        c.setNome("Marcos Bastos");
        c.setSenha("123123");
        c.setEndereco("Rua General Carneiro");
        c.setSexo('M');
        c.setTelefone("41984608890");
        //c.setCidade(1);*/

        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();

        Roupa c = new Roupa();
        s.load(c, 1);
        int a = 0;
    }
}
