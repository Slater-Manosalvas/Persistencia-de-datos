package ec.edu.ups.rally.main;

import javax.persistence.*;
import ec.edu.ups.rally.modelo.*;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("rallyPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Vehiculo v = new Vehiculo();
        v.setMarca("Citroen");
        v.setModelo("Saxo VTS");
        v.setChasis("123ABC");

        Componente c = new Componente();
        c.setNombre("Motor");
        c.setEstado("Nuevo");
        c.setVehiculo(v);

        RegistroMantenimiento r = new RegistroMantenimiento();
        r.setFecha(new Date());
        r.setDescripcion("Cambio aceite");
        r.setVehiculo(v);

        em.persist(v);
        em.persist(c);
        em.persist(r);

        em.getTransaction().commit();

        em.close();
        emf.close();

        System.out.println("FUNCIONA 🚀");
    }
}