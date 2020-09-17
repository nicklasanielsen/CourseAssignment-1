package facades;

import DTOs.CarDTO;
import entities.Car;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Mathias Nielsen
 */
public class CarFacade {
    
    private static CarFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CarFacade() {
    }

    public static CarFacade getCarFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<CarDTO> getAllCars() {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createNamedQuery("Cars.getAll");
            List<Car> cars = query.getResultList();

            List<CarDTO> carDTOs = new ArrayList();

            cars.forEach(car -> {
                carDTOs.add(new CarDTO(car));
            });

            return carDTOs;
        } finally {
            em.close();
        }
    }
    
    public boolean resetDB() {
        EntityManager em = getEntityManager();
        List<Car> cars = new ArrayList();

        // Cars to be added
        cars.add(new Car(2000, "Skoda", "Superb", 10000.0, "AB12345", "Mathias Haugaard Nielsen"));
        cars.add(new Car(2020, "Audi", "Q8", 100000.0, "ZZ98765", "Nikolaj Larsen"));
        cars.add(new Car(2019, "Mercedes-Benz", "SLS Black Series", 50000.0, "CJ23498", "Lewis Hamilton"));
        cars.add(new Car(1983, "DeLorean Motor Company", "DMC DeLorean", 35000.0, "NN42069", "Nicklas Nielsen"));
        cars.add(new Car(2000, "Aston Martin", "DBS V12", 250000.0, "TI30901", "James Bond"));

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Cars.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
            cars.forEach(car -> {
                em.getTransaction().begin();
                em.persist(car);
                em.getTransaction().commit();
            });
        } finally {
            em.close();
        }

        return true;
    }

}
