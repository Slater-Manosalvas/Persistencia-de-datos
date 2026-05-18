package ec.edu.ups.rally.main;

import ec.edu.ups.rally.modelo.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("rallyPU");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // =========================
        // VEHICULO
        // =========================
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setMarca("Citroen");
        vehiculo.setModelo("Saxo VTS");
        vehiculo.setChasis("ABC123");

        // =========================
        // COMPONENTES
        // =========================
        Componente motor = new Componente();
        motor.setNombre("Motor");
        motor.setEstado("Excelente");
        motor.setVehiculo(vehiculo);

        Componente suspension = new Componente();
        suspension.setNombre("Suspensión");
        suspension.setEstado("Buena");
        suspension.setVehiculo(vehiculo);

        List<Componente> componentes = new ArrayList<>();
        componentes.add(motor);
        componentes.add(suspension);

        vehiculo.setComponentes(componentes);

        // =========================
        // MANTENIMIENTO
        // =========================
        Mantenimiento mantenimiento = new Mantenimiento();
        mantenimiento.setFecha(new Date());
        mantenimiento.setDescripcion("Cambio de aceite");
        mantenimiento.setVehiculo(vehiculo);

        List<Mantenimiento> mantenimientos = new ArrayList<>();
        mantenimientos.add(mantenimiento);

        vehiculo.setMantenimientos(mantenimientos);

        // =========================
        // PILOTO
        // =========================
        Piloto piloto = new Piloto();
        piloto.setNombre("Carlos Sainz");
        piloto.setNacionalidad("España");
        piloto.setEdad(40);
        piloto.setVehiculo(vehiculo);

        vehiculo.setPiloto(piloto);

        // =========================
        // CARRERAS
        // =========================
        Carrera carrera1 = new Carrera();
        carrera1.setNombre("Rally Dakar");
        carrera1.setUbicacion("Arabia Saudita");
        carrera1.setFecha(new Date());

        Carrera carrera2 = new Carrera();
        carrera2.setNombre("Rally Monte Carlo");
        carrera2.setUbicacion("Mónaco");
        carrera2.setFecha(new Date());

        List<Carrera> carreras = new ArrayList<>();
        carreras.add(carrera1);
        carreras.add(carrera2);

        vehiculo.setCarreras(carreras);

        // =========================
        // PERSISTENCIA
        // =========================
        em.persist(carrera1);
        em.persist(carrera2);

        em.persist(vehiculo);

        em.getTransaction().commit();

        // ====================================
        // CONSULTA 1 - FILTRO
        // ====================================

        TypedQuery<Vehiculo> queryVehiculos =
                em.createQuery(
                        "SELECT v FROM Vehiculo v WHERE v.marca = :marca",
                        Vehiculo.class
                );

        queryVehiculos.setParameter("marca", "Citroen");

        List<Vehiculo> vehiculos = queryVehiculos.getResultList();

        System.out.println("=== VEHICULOS FILTRADOS ===");

        for (Vehiculo v : vehiculos) {
            System.out.println(v.getMarca() + " " + v.getModelo());
        }

        // ====================================
        // CONSULTA 2 - ORDENAMIENTO
        // ====================================

        TypedQuery<Piloto> queryPilotos =
                em.createQuery(
                        "SELECT p FROM Piloto p ORDER BY p.edad DESC",
                        Piloto.class
                );

        List<Piloto> pilotos = queryPilotos.getResultList();

        System.out.println("=== PILOTOS ORDENADOS ===");

        for (Piloto p : pilotos) {
            System.out.println(p.getNombre() + " - " + p.getEdad());
        }

        // ====================================
        // CONSULTA 3 - PAGINACION
        // ====================================

        TypedQuery<Carrera> queryCarreras =
                em.createQuery(
                        "SELECT c FROM Carrera c",
                        Carrera.class
                );

        queryCarreras.setFirstResult(0);
        queryCarreras.setMaxResults(5);

        List<Carrera> carrerasPaginadas =
                queryCarreras.getResultList();

        System.out.println("=== CARRERAS PAGINADAS ===");

        for (Carrera c : carrerasPaginadas) {
            System.out.println(c.getNombre());
        }

        em.close();
        emf.close();

        System.out.println("Sistema Rally funcionando correctamente");
    }
}