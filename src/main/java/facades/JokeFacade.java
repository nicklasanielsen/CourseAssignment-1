package facades;

import DTOs.JokeDTO;
import entities.Joke;
import java.util.ArrayList;
import java.util.List;
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
        jokes.add(new Joke("Hvad kalder man en flot pakistaner?\n Asif.", "de-sjove-jokes.dk", "Racistisk joke"));
        jokes.add(new Joke("Dark humour is like food.\n Not everyone gets it.", "Joseph Stalin", "Dark humour"));
        jokes.add(new Joke("Vil I høre en vits?\n SANDWICH!", "de-sjove-jokes.dk", "Dårlig joke"));
        jokes.add(new Joke("Konen beklager sig til sin mand at hendes bryster hænger, hun har appelsinhud og for stor en røv. Om han ikke kunne give hende et kompliment, hvortil han svarer, “dit syn fejler i hvert fald ikke noget!", "de-sjove-jokes.dk", "Dårlig joke"));
        jokes.add(new Joke("Hvad er ligheden mellem en stegepande og en luder?\n – De kan begge få pølsen til at sprøjte..", "de-sjove-jokes.dk", "Sexistisk  joke"));
        jokes.add(new Joke("Hvem er den sjovest mand der nogensinde har levet.\n Hitler! Han tog nemlig gas på 6 millioner jøder.", "de-sjove-jokes.dk", "Jøde joke"));
        jokes.add(new Joke("Hvilken farve er en postkasse inden i?\n Infrarød.", "de-sjove-jokes.dk", "Far joke"));

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
