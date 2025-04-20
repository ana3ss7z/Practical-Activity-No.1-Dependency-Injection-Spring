package net.anass.metier;

import net.anass.dao.Idao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("metier")
public class MetierImpl implements IMetier {
@Autowired
@Qualifier("d")
    private Idao dao;
    public MetierImpl(Idao dao) {
        this.dao = dao;
    }

    @Override
    public double Calcul() {
        double t = dao.getData();
        double res = t * t /5* Math.random();
        return res;
    }

    public void setDao(Idao dao) {
        this.dao = dao;
    }
}
