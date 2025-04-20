package net.anass.dao;

import org.springframework.stereotype.Component;

@Component("d")
public class DaoImpl implements Idao{
    @Override
    public double getData() {
        System.out.println("Version base des données");
        double t = 43;
        return t;
    }
}
