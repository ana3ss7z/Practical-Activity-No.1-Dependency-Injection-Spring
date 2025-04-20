package net.anass.pres;

import net.anass.dao.Idao;
import net.anass.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PresSpringXML {
    public static void main(String[] args) {

    ApplicationContext springContext =
            new ClassPathXmlApplicationContext("config.xml");
    IMetier metier = (IMetier) springContext.getBean("metier");
        System.out.println("RES = "+metier.Calcul());

        Idao dao = (Idao) springContext.getBean("d");
        System.out.print("RES 2  = "+dao.getData());
    }
}
