package ma.emsi.abkari.tp3abkari.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import java.util.List;

@Path("/guide")
public class GuideTouristiquResource {
    @GET
    @Path("lieu/{ville_ou_pays}")
    public List<String> getLieu(@PathParam("ville_ou_pays") String lieu) {
        return List.of(lieu);
    }
}
