package rest;

import DTOs.JokeDTO;
import entities.Joke;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isIn;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

/**
 *
 * @author Nicklas Nielsen
 */
public class JokeResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static List<Joke> jokes;
    private static List<JokeDTO> jokeDTOs;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        jokes = new ArrayList();
        jokeDTOs = new ArrayList();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
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
        jokes.add(new Joke("Joke1", "Ref1", "Type1"));
        jokes.add(new Joke("Joke2", "Ref2", "Type2"));
        jokes.add(new Joke("Joke3", "Ref3", "Type3"));

        try {
            jokes.forEach(joke -> {
                em.getTransaction().begin();
                em.persist(joke);
                em.getTransaction().commit();
            });
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
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/jokes").then().statusCode(200);
    }

    @Test
    public void testGetAllJokes_status_code_200() {
        given()
                .contentType(ContentType.JSON)
                .get("/jokes/all")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode());
    }

    @Test
    public void testGetAllJokes_joke() {
        List<String> jokeResults = new ArrayList();

        jokeDTOs.forEach(jokeDTO -> {
            jokeResults.add(jokeDTO.getJoke());
        });

        given()
                .contentType(ContentType.JSON)
                .get("jokes/all")
                .then()
                .assertThat()
                .body("joke", hasItems(jokeResults.toArray(new String[0])));
    }

    @Test
    public void testGetAllJokes_reference() {
        List<String> references = new ArrayList();

        jokeDTOs.forEach(jokeDTO -> {
            references.add(jokeDTO.getReference());
        });

        given()
                .contentType(ContentType.JSON)
                .get("jokes/all")
                .then()
                .assertThat()
                .body("reference", hasItems(references.toArray(new String[0])));
    }

    @Test
    public void testGetAllJokes_type() {
        List<String> types = new ArrayList();

        jokeDTOs.forEach(jokeDTO -> {
            types.add(jokeDTO.getType());
        });

        given()
                .contentType(ContentType.JSON)
                .get("jokes/all")
                .then()
                .assertThat()
                .body("type", hasItems(types.toArray(new String[0])));
    }

    @Test
    public void testGetJokeById_status_code_200() {
        given()
                .contentType(ContentType.JSON)
                .get("/jokes/id/" + jokes.get(0).getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode());
    }

    @Test
    public void testGetJokeById_id() {
        int id = jokes.get(0).getId().intValue();
        List<Integer> ids = new ArrayList();

        jokes.forEach(joke -> {
            ids.add(joke.getId().intValue());
        });

        given()
                .contentType(ContentType.JSON)
                .get("/jokes/id/" + id)
                .then()
                .assertThat()
                .body("id", isIn(ids));
    }

    @Test
    public void testGetJokeById_joke() {
        int id = jokes.get(0).getId().intValue();
        String jokeResult = jokes.get(0).getJoke();

        given()
                .contentType(ContentType.JSON)
                .get("/jokes/id/" + id)
                .then()
                .assertThat()
                .body("joke", is(jokeResult));
    }

    @Test
    public void testGetJokeById_reference() {
        int id = jokes.get(0).getId().intValue();
        String ref = jokes.get(0).getReference();

        given()
                .contentType(ContentType.JSON)
                .get("/jokes/id/" + id)
                .then()
                .assertThat()
                .body("reference", is(ref));
    }

    @Test
    public void testGetJokeById_type() {
        int id = jokes.get(0).getId().intValue();
        String type = jokes.get(0).getType();

        given()
                .contentType(ContentType.JSON)
                .get("/jokes/id/" + id)
                .then()
                .assertThat()
                .body("type", is(type));
    }

    @Test
    public void testGetRandomJoke_status_code_200() {
        given()
                .contentType(ContentType.JSON)
                .get("/jokes/random")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode());
    }

    @Test
    @RepeatedTest(30)
    public void testGetRandomJoke_id() {
        List<Integer> ids = new ArrayList();

        jokes.forEach(joke -> {
            ids.add(joke.getId().intValue());
        });

        given()
                .contentType(ContentType.JSON)
                .get("/jokes/random")
                .then()
                .assertThat()
                .body("id", isIn(ids));
    }

    @Test
    @RepeatedTest(30)
    public void testGetRandomJoke_joke() {
        List<String> jokesResult = new ArrayList();

        jokes.forEach(joke -> {
            jokesResult.add(joke.getJoke());
        });

        given()
                .contentType(ContentType.JSON)
                .get("/jokes/random")
                .then()
                .assertThat()
                .body("joke", isIn(jokesResult));
    }

    @Test
    @RepeatedTest(30)
    public void testGetRandomJoke_reference() {
        List<String> refs = new ArrayList();

        jokes.forEach(joke -> {
            refs.add(joke.getReference());
        });

        given()
                .contentType(ContentType.JSON)
                .get("/jokes/random")
                .then()
                .assertThat()
                .body("reference", isIn(refs));
    }

    @Test
    @RepeatedTest(30)
    public void testGetRandomJoke_type() {
        List<String> types = new ArrayList();

        jokes.forEach(joke -> {
            types.add(joke.getType());
        });

        given()
                .contentType(ContentType.JSON)
                .get("/jokes/random")
                .then()
                .assertThat()
                .body("type", isIn(types));
    }

    @Test
    public void testResetDB() {
        given()
                .contentType(ContentType.JSON)
                .get("/jokes/reset")
                .then()
                .assertThat()
                .body("msg", equalTo("Reset performed"));
    }

}
