package facades;

import DTOs.MemberDTO;
import utils.EMF_Creator;
import entities.Member;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Nicklas Nielsen
 */
public class MemberFacadeTest {

    private static EntityManagerFactory emf;
    private static MemberFacade facade;
    private static List<Member> members;
    private static List<MemberDTO> memberDTOs;

    public MemberFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = MemberFacade.getMemberFacade(emf);
        members = new ArrayList();
        memberDTOs = new ArrayList();
    }

    @AfterAll
    public static void tearDownClass() {
        emf.close();
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
    public void testGetAllMembers() {
        // Arrange
        List<MemberDTO> expected = memberDTOs;

        // Act
        List<MemberDTO> actual = facade.getAllMembers();

        // Assert
        assertTrue(actual.containsAll(expected));
    }

}
