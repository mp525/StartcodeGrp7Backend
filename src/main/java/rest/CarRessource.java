package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CarDTO;
import facades.CarFacade;
import utils.EMF_Creator;
import facades.FacadeExample;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("car")
public class CarRessource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final CarFacade FACADE =  CarFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll() {
        return GSON.toJson(FACADE.getAllCars());
        
    }
    @Path("/id/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getID(@PathParam("id") int id) {
        return GSON.toJson(FACADE.findCar(id));
    }
    @Path("/delete/{id}")
    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    public String getDelete(@PathParam("id") int id) throws Exception {
        return GSON.toJson(FACADE.deleteCar(id));
    }
    @Path("/add")
    @Consumes({MediaType.APPLICATION_JSON})
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public String create(String car) throws Exception {
        CarDTO dto=GSON.fromJson(car, CarDTO.class);
        FACADE.addCar(dto);
        return GSON.toJson(dto);
    }
    
    @Path("/edit/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    public String update(@PathParam("id") int id,String car) throws Exception {
        CarDTO dto=GSON.fromJson(car, CarDTO.class);
        FACADE.updateCar(dto, id);
        return GSON.toJson(dto);
    }
    
    
}
