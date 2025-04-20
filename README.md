Voici un **petit rapport synthétique** pour le TP demandé :

---

## 📝 Rapport du TP — Injection de dépendances et couplage faible en Java

### 🎯 Objectif :
Mettre en œuvre le principe de **couplage faible** en Java en utilisant des interfaces, des implémentations concrètes, et différentes méthodes d’injection de dépendances (manuelles et via Spring).

---

### ✅ Partie 1 : Étapes de réalisation

#### 1. Création de l’interface `IDao`
Nous avons défini une interface `IDao` contenant la méthode suivante :
```java
public interface Idao {
    double getData();
}
```
Cette interface représente une source de données abstraite.

#### 2. Implémentation de l’interface `IDao`
Une classe `DaoImpl` a été créée pour implémenter l’interface :
```java
public class DaoImpl implements Idao{
    @Override
    public double getData() {
        System.out.println("Version base des données");
        double t = 43;
        return t;
    }
}
```

#### 3. Création de l’interface `IMetier`
Une deuxième interface, `IMetier`, a été définie avec une méthode `calcul()` :
```java
package net.anass.metier;

public interface IMetier {
    double Calcul();
}
```


#### 4. Implémentation de `IMetier` avec couplage faible
Une classe `MetierImpl` a été développée, avec un attribut de type `IDao` injecté via une méthode `setDao()` :
```java
public class MetierImpl implements IMetier {
    private Idao dao;
    public MetierImpl(Idao dao) {
        this.dao = dao;
    }
    public MetierImpl() {
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

```
Cela permet un **couplage faible**, car `MetierImpl` dépend de l’interface `IDao` et non de sa classe concrète.

---

### 🔧 Partie 2 : Méthodes d'injection de dépendances

#### a. Injection statique (manuelle)
Dans une classe `Pres1`, les dépendances sont créées et injectées manuellement :
```java
public class Pres1 {
    public static void main(String[] args) {
        DaoImpl dao = new DaoImpl();
        MetierImpl metier = new MetierImpl();
        metier.setDao(dao);
        System.out.print("Res : "+metier.Calcul());
    }
}
```

#### b. Injection dynamique (par réflexion)
La classe `Pres2` lit le nom de la classe DAO depuis un fichier `config.txt`, la charge dynamiquement via `Class.forName()` :
```java
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

```
Cette méthode permet de **changer la dépendance sans modifier le code source**.

#### c. Injection avec le Framework Spring

##### ➤ Version XML
Définition des beans dans un fichier `applicationContext.xml` :
```xml
    <bean id="d" class="net.anass.dao.DaoImpl"></bean>
    <bean id="metier" class="net.anass.metier.MetierImpl">
        <property name="dao" ref="d"></property>
    </bean>
```
Chargement du contexte dans une classe `PresSpringXml` :
```java
    ApplicationContext springContext =
            new ClassPathXmlApplicationContext("config.xml");
    IMetier metier = (IMetier) springContext.getBean("metier");
        System.out.println("RES = "+metier.Calcul());
```

##### ➤ Version Annotations
Utilisation des annotations `@Component`, `@Autowired` :
```java
@Component
public class DaoImpl implements IDao { ... }


@Component("metier")
public class MetierImpl implements IMetier {
@Autowired
@Qualifier("d")
    private Idao dao;
```
Avec scan des packages dans `applicationContext.xml` ou via `@SpringBootApplication`.

---
```java
 ApplicationContext annotationContext =
                new AnnotationConfigApplicationContext("net.anass");
        IMetier metier = (IMetier) annotationContext.getBean("metier");
        System.out.print("RES= "+ metier.Calcul());
```

### ✅ Conclusion

Ce TP a permis de comprendre :
- La différence entre couplage fort et couplage faible
- L’intérêt d’utiliser des interfaces pour séparer les responsabilités
- Les différentes méthodes d’injection de dépendances, notamment via **le framework Spring**, pour rendre l’application plus souple et maintenable.

---

