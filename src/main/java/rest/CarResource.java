package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author Nikolaj Larsen
 */
@Path("cars")
public class CarResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final CarFacade FACADE = CarFacade.getCarFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Retrieves representation of an instance of rest.CarResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getDefault() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCars() {
        return GSON.toJson(FACADE.getAllCars());
    }
    
    @GET
    @Path("reset")
    @Produces(MediaType.APPLICATION_JSON) 
    public String resetDB() {
        boolean resetPerformed = FACADE.resetDB();
        
        if(resetPerformed){
            return "{\"msg\":\"Reset performed\"}";
        }else{
            return "{\"msg\":\"Reset failed\"}";
        }
    }
}
