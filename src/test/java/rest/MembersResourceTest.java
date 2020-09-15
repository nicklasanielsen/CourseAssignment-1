package rest;

import DTOs.MemberDTO;
import entities.Member;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import java.io.IOException;
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

/**
 *
 * @author Mathias Nielsen
 * @author Nicklas Nielsen
 */
public class MembersResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static List<Member> members;
    private static List<MemberDTO> memberDTOs;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() throws IOException {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        members = new ArrayList();
        memberDTOs = new ArrayList();

        httpServer = startServer();
        httpServer.start();
        while (!httpServer.isStarted()) {
        }
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();

        members.add(new Member("Nicklas", "Alexander", "Nielsen", "cph-nn161", "nicklasanielsen"));
        members.add(new Member("Mathias", "Haugaard", "Nielsen", "cph-mn556", "Haugaard-DK"));
        members.add(new Member("Nikolaj", null, "Larsen", "cph-nl174", "Nearial"));

        members.forEach(member -> {
            memberDTOs.add(new MemberDTO(member));
        });

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Member.deleteAllRows").executeUpdate();
            em.persist(members.get(0));
            em.persist(members.get(1));
            em.persist(members.get(2));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
        members.clear();
        memberDTOs.clear();
    }

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given()
                .when()
                .get("/groupmembers")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetAllMembers_status_code_200() {
        given()
                .contentType(ContentType.JSON)
                .get("/groupmembers/all")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode());
    }

    @Test
    public void testGetAllMembers_size() {
        given()
                .contentType(ContentType.JSON)
                .get("/groupmembers/all")
                .then()
                .assertThat()
                .body("size()", is(memberDTOs.size()));
    }

    @Test
    public void testGetAllMembers_fullname() {
        List<String> fullnames = new ArrayList();

        memberDTOs.forEach(memberDTO -> {
            fullnames.add(memberDTO.getFullname());
        });

        given()
                .contentType(ContentType.JSON)
                .get("/groupmembers/all")
                .then()
                .assertThat()
                .body("fullname", hasItems(fullnames.toArray(new String[0])));
    }

    @Test
    public void testGetAllMembers_StudentID() {
        List<String> studentIDs = new ArrayList();

        memberDTOs.forEach(memberDTO -> {
            studentIDs.add(memberDTO.getStudentID());
        });

        given()
                .contentType(ContentType.JSON)
                .get("/groupmembers/all")
                .then()
                .assertThat()
                .body("studentID", hasItems(studentIDs.toArray(new String[0])));
    }

    @Test
    public void testGetAllMembers_github() {
        List<String> usernames = new ArrayList();

        memberDTOs.forEach(memberDTO -> {
            usernames.add(memberDTO.getGithub());
        });

        given()
                .contentType(ContentType.JSON)
                .get("/groupmembers/all")
                .then()
                .assertThat()
                .body("github", hasItems(usernames.toArray(new String[0])));
    }

    @Test
    public void testResetDB() {
        given()
                .contentType(ContentType.JSON)
                .get("/groupmembers/reset")
                .then()
                .assertThat()
                .body("msg", equalTo("Reset performed"));
    }
}
