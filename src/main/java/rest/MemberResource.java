package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.EMF_Creator;
import facades.MemberFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Mathias Nielsen
 * @author Nicklas Nielsen
 */
@Path("groupmembers")
public class MemberResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final MemberFacade FACADE = MemberFacade.getMemberFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getDefault() {
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMembers() {
        return GSON.toJson(FACADE.getAllMembers());
    }

    @GET
    @Path("reset")
    @Produces({MediaType.APPLICATION_JSON})
    public String resetDB() {
        boolean resetPerformed = FACADE.resetDB();

        if (resetPerformed) {
            return "{\"msg\":\"Reset performed\"}";
        } else {
            return "{\"msg\":\"Reset failed\"}";
        }
    }

}
