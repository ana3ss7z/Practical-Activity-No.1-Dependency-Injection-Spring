package net.anass.pres;

import net.anass.dao.DaoImpl;
import net.anass.metier.MetierImpl;

public class Pres1 {
    public static void main(String[] args) {
        DaoImpl dao = new DaoImpl();
        MetierImpl metier = new MetierImpl();
        metier.setDao(dao);
        System.out.print("Res : "+metier.Calcul());
    }
}
