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

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
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

        List<Member> members;
        List<MemberDTO> memberDTOs = new ArrayList();

        try {
            Query query = em.createNamedQuery("Member.getAll");

            members = query.getResultList();

            members.forEach(member -> {
                memberDTOs.add(new MemberDTO(member));
            });

            return memberDTOs;
        } finally {
            em.close();
        }
    }

}
