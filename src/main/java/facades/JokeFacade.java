package facades;

import DTOs.JokeDTO;
import entities.Joke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Nicklas Nielsen
 */
public class JokeFacade {

    private static JokeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private JokeFacade() {
    }

    public static JokeFacade getJokeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<JokeDTO> getAllJokes() {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createNamedQuery("Joke.getAll");
            List<Joke> jokes = query.getResultList();

            List<JokeDTO> jokeDTOs = new ArrayList();

            jokes.forEach(joke -> {
                jokeDTOs.add(new JokeDTO(joke));
            });

            return jokeDTOs;
        } finally {
            em.close();
        }
    }

    public Joke getJokeById(long id) {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createNamedQuery("Joke.getById");
            query.setParameter("id", id);

            return (Joke) query.getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    private long getRandomId(long start, long end) {
        long id = start + (long) (Math.random() * (end - start));
        return id;
    }

    public Joke getRandomJoke() {
        EntityManager em = getEntityManager();

        try {
            long firstIndex = (long) em.createNamedQuery("Joke.getFirstIndex").getSingleResult();
            long lastIndex = (long) em.createNamedQuery("Joke.getLastIndex").getSingleResult();

            Joke joke = getJokeById(getRandomId(firstIndex, lastIndex));

            return joke;
        } finally {
            em.close();
        }
    }

    public boolean resetDB() {
        EntityManager em = getEntityManager();
        List<Joke> jokes = new ArrayList();

        // Jokes to be added
        jokes.add(new Joke("Joke1", "Ref1", "Type1"));
        jokes.add(new Joke("Joke2", "Ref2", "Type2"));
        jokes.add(new Joke("Joke3", "Ref3", "Type3"));

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            jokes.forEach(joke -> {
                em.persist(joke);
            });
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return true;
    }

}
