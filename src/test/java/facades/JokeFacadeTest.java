package facades;

import DTOs.JokeDTO;
import entities.Joke;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

/**
 *
 * @author Nicklas Nielsen
 */
public class JokeFacadeTest {

    private static EntityManagerFactory emf;
    private static JokeFacade facade;
    private static List<Joke> jokes;
    private static List<JokeDTO> jokeDTOs;

    public JokeFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = JokeFacade.getJokeFacade(emf);
        jokes = new ArrayList();
        jokeDTOs = new ArrayList();
    }

    @AfterAll
    public static void tearDownClass() {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();

        // Add test data here
        
        
        try {
            em.getTransaction().begin();
            jokes.forEach(member -> {
                em.persist(member);
            });
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        jokes.forEach(joke -> {
            jokeDTOs.add(new JokeDTO(joke));
        });
    }
    
    @AfterEach
    public void tearDown() {
        jokes.clear();
        jokeDTOs.clear();

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    @Test
    public void testGetAllJoke(){
        // Arrange
        List<JokeDTO> expected = jokeDTOs;
        
        // Act
        List<JokeDTO> actual = facade.getAllJokes();
        
        // Assert
        assertTrue(expected.containsAll(actual));
    }
    
    @Test
    public void testGetJokeById_found(){
        // Arrange
        Joke expected = jokes.get(0);
        
        // Act
        Joke actual = facade.getJokeById(expected.getId());
        
        // Assert
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetJokeById_not_found(){
        // Arange
        long id = jokes.size() + 1;
        
        // Act
        Joke actual = facade.getJokeById(id);
        
        // Assert
        assertNull(actual);
    }
    
    @Test
    public void testGetRandomJoke(){
        // Arrange
        List<Joke> list = jokes;
        
        // Act
        Joke actual = facade.getRandomJoke();
        
        // Assert
        assertTrue(list.contains(actual));
    }
    
    @Test
    public void testResetDB(){
        // Arrange
        boolean expected = true;
        
        // Act
        boolean actual = facade.resetDB();
        
        // Assert
        assertEquals(expected, actual);
    }
}
