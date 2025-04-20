package net.anass.pres;

import net.anass.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PresSpringAnnotation {
    public static void main(String[] args) {
        ApplicationContext annotationContext =
                new AnnotationConfigApplicationContext("net.anass");
        IMetier metier = (IMetier) annotationContext.getBean("metier");
        System.out.print("RES= "+ metier.Calcul());
    }
}
