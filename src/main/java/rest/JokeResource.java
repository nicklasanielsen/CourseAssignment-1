package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.JokeFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 *
 * @author Nicklas Nielsen
 */
@Path("jokes")
public class JokeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final JokeFacade FACADE = JokeFacade.getJokeFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getDefault() {
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllJokes() {
        return GSON.toJson(FACADE.getAllJokes());
    }

    @GET
    @Path("id/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getJokeById(@PathParam("id") long id) {
        return GSON.toJson(FACADE.getJokeById(id));
    }

    @GET
    @Path("random")
    @Produces({MediaType.APPLICATION_JSON})
    public String getRandomJoke() {
        return GSON.toJson(FACADE.getRandomJoke());
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
