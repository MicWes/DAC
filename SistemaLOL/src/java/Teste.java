/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.ufpr.tads.dac.hib.HibernateUtil;
import com.ufpr.tads.dac.hib.dao.GenericDao;
import com.ufpr.tads.dac.model.Cidade;
import com.ufpr.tads.dac.model.Cliente;
import com.ufpr.tads.dac.model.Estado;
import com.ufpr.tads.dac.model.Pedido;
import com.ufpr.tads.dac.model.Roupa;
import com.ufpr.tads.dac.model.Status;
import java.sql.Date;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Marcos
 */
public class Teste {

    public static void main(String[] args) {
        Cliente c = new Cliente();
        c.setCpf("78945678912");
        c.setEmail("michelly@abc.com");
        c.setNome("Michelly");
        c.setSenha("123456");
        c.setEndereco("Rua Aquela lá");
        //c.setSexo('F');
        c.setTelefone("41920175515");
        c.setCidade(1);
        
        List<Cliente> cl = GenericDao.getList(Cliente.class, "nome");
        /*Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        
        Roupa c = new Roupa();
        s.load(c, 1);
        int a = 0;*/
        
       // Cliente cget = (Cliente) GenericDao.getBy(Cliente.class, "cl_nome", "José");
        
        System.out.println("a");
        Status st = Status.fromInt(3);
        System.out.println(st.getNum());
        System.out.println(st.toString());
    }
}
