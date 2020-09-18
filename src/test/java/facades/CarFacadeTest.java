package facades;

import DTOs.CarDTO;
import entities.Car;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.EMF_Creator;

/**
 *
 * @author Mathias Nielsen
 */
public class CarFacadeTest {
    
    private static EntityManagerFactory emf;
    private static CarFacade facade;
    private static List<Car> cars;
    private static List<CarDTO> carDTOs;
    
    public CarFacadeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = CarFacade.getCarFacade(emf);
        cars = new ArrayList();
        carDTOs = new ArrayList();
    }
    
    @AfterAll
    public static void tearDownClass() {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Cars.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();

        // Add test data here
        cars.add(new Car(2000, "Skoda", "Superb", 10000.0, "AB12345", "Mathias Haugaard Nielsen"));
        cars.add(new Car(2018, "Audi", "Q8", 100000.0, "ZZ98765", "Nikolaj Larsen"));
        cars.add(new Car(2020, "Mercedes-Benz", "SLS Black Series", 50000.0, "CJ23498", "Lewis Hamilton"));
        cars.add(new Car(1983, "DeLorean Motor Company", "DMC DeLorean", 35000.0, "NN42069", "Nicklas Nielsen"));
        cars.add(new Car(2000, "Aston Martin", "DBS V12", 250000.0, "TI30901", "James Bond"));

        try {
            cars.forEach(car -> {
                em.getTransaction().begin();
                em.persist(car);
                em.getTransaction().commit();
            });
        } finally {
            em.close();
        }

        cars.forEach(car -> {
            carDTOs.add(new CarDTO(car));
        });
    }
    
    @AfterEach
    public void tearDown() {
        cars.clear();
        carDTOs.clear();

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Cars.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testGetAllCars() {
        List<CarDTO> expected = carDTOs;
        
        List<CarDTO> actual = facade.getAllCars();
        
        assertTrue(expected.containsAll(actual));
    }

    @Test
    public void testResetDB() {
        boolean expected = true;
        
        boolean actual = facade.resetDB();
        
        assertEquals(expected, actual);
    }
    
}
