package rest;

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
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static rest.CarResourceTest.BASE_URI;
import utils.EMF_Creator;

/**
 *
 * @author Nikolaj Larsen
 */
public class CarResourceTest {
    
    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static List<Car> cars;
    private static List<CarDTO> carDTOs;
    
    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;
    
    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }
    
    @BeforeAll
    public void setUpClass() {
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        cars = new ArrayList();
        carDTOs = new ArrayList();

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
            em.createNamedQuery("Cars.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    @BeforeEach
    public void setUp(){
        EntityManager em = emf.createEntityManager();
        
        // Add test data here
        cars.add(new Car(1997, "Skoda", "bil3", 1337, "HHsx725", "Sven"));
        cars.add(new Car(1945, "Volkswagen", "bil1", 2674, "B3H82G", "Egon"));
        cars.add(new Car(1997, "Nissan", "bil2", 4011, "B4J68N", "Vagn"));
        
                try {
            cars.forEach(member -> {
                em.getTransaction().begin();
                em.persist(member);
                em.getTransaction().commit();
            });
        } finally {
            em.close();
        }

        cars.forEach(member -> {
            carDTOs.add(new CarDTO(member));
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
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/cars").then().statusCode(200);
    }
    
    @Test
    public void testGetAllCars_status_code_200() {
        given()
                .contentType(ContentType.JSON)
                .get("/cars/all")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode());
    }
    
    @Test
    public void testGetAllCars_size() {
        given()
                .contentType(ContentType.JSON)
                .get("/cars/all")
                .then()
                .assertThat()
                .body("size()", is(carDTOs.size()));
    }
    
    @Test
    public void testGetAllCars_year() {
        List<Integer> years = new ArrayList();

        carDTOs.forEach(carDTO -> {
            years.add(carDTO.getYear());
        });

        given()
                .contentType(ContentType.JSON)
                .get("cars/all")
                .then()
                .assertThat()
                .body("year", hasItems(years.toArray(new Integer[0])));
    }
    
        @Test
    public void testGetAllCars_make() {
        List<String> makes = new ArrayList();

        carDTOs.forEach(carDTO -> {
            makes.add(carDTO.getMake());
        });

        given()
                .contentType(ContentType.JSON)
                .get("cars/all")
                .then()
                .assertThat()
                .body("make", hasItems(makes.toArray(new String[0])));
    }
    
    @Test
    public void testGetAllCars_model() {
        List<String> model = new ArrayList();

        carDTOs.forEach(carDTO -> {
            model.add(carDTO.getModel());
        });

        given()
                .contentType(ContentType.JSON)
                .get("cars/all")
                .then()
                .assertThat()
                .body("model", hasItems(model.toArray(new String[0])));
    }
    
    @Test
    public void testGetAllCars_price() {
        List<Double> price = new ArrayList();

        carDTOs.forEach(carDTO -> {
            price.add(carDTO.getPrice());
        });

        given()
                .contentType(ContentType.JSON)
                .get("cars/all")
                .then()
                .assertThat()
                .body("price", hasItems(price.toArray(new Double[0])));
    }
    
    @Test
    public void testGetAllCars_licenseplate() {
        List<String> licensePlate = new ArrayList();

        carDTOs.forEach(carDTO -> {
            licensePlate.add(carDTO.getMake());
        });

        given()
                .contentType(ContentType.JSON)
                .get("cars/all")
                .then()
                .assertThat()
                .body("licensePlate", hasItems(licensePlate.toArray(new String[0])));
    }
    
            @Test
    public void testGetAllCars_owner() {
        List<String> owner = new ArrayList();

        carDTOs.forEach(carDTO -> {
            owner.add(carDTO.getOwner());
        });

        given()
                .contentType(ContentType.JSON)
                .get("cars/all")
                .then()
                .assertThat()
                .body("owner", hasItems(owner.toArray(new String[0])));
    }
    
    @Test
    public void testResetDB() {
        given()
                .contentType(ContentType.JSON)
                .get("/cars/reset")
                .then()
                .assertThat()
                .body("msg", equalTo("Reset performed"));
    }
    
}
