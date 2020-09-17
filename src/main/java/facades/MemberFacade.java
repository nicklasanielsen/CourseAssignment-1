package facades;

import DTOs.MemberDTO;
import entities.Member;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Nicklas Nielsen
 */
public class MemberFacade {

    private static MemberFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MemberFacade() {
    }

    public static MemberFacade getMemberFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MemberFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<MemberDTO> getAllMembers() {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createNamedQuery("Members.getAll");
            List<Member> members = query.getResultList();

            List<MemberDTO> memberDTOs = new ArrayList();

            members.forEach(member -> {
                memberDTOs.add(new MemberDTO(member));
            });

            return memberDTOs;
        } finally {
            em.close();
        }
    }

    public boolean resetDB() {
        EntityManager em = getEntityManager();
        List<Member> members = new ArrayList();

        // Users to be added
        members.add(new Member("Nicklas", "Alexander", "Nielsen", "cph-nn161", "nicklasanielsen"));
        members.add(new Member("Mathias", "Haugaard", "Nielsen", "cph-mn556", "Haugaard-DK"));
        members.add(new Member("Nikolaj", null, "Larsen", "cph-nl174", "Nearial"));

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Members.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
            members.forEach(member -> {
                em.getTransaction().begin();
                em.persist(member);
                em.getTransaction().commit();
            });
        } finally {
            em.close();
        }

        return true;
    }

}
