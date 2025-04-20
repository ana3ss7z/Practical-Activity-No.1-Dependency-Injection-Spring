package net.anass.pres;

import net.anass.dao.Idao; // ✅ import correct selon le nom réel
import net.anass.metier.IMetier;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Pres2 {
    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Scanner sc = new Scanner(new File("config.txt"));
        String daoClassName = sc.nextLine(); // ex: net.anass.dao.DaoImpl
        Class cDao = Class.forName(daoClassName);
        Idao dao = (Idao) cDao.newInstance(); // ✅ utiliser Idao avec le bon nom
        System.out.println(dao.getData());

        String metierClassName= sc.nextLine();
        Class cMetier = Class.forName(metierClassName);
        IMetier metier = (IMetier) cMetier.getConstructor(Idao.class).newInstance(dao);

        System.xout.print(metier.Calcul());

    }
}
