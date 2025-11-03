package ma.emsi.abkari.tp3abkari.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import ma.emsi.abkari.tp3abkari.llm.GuideTouristique;
import ma.emsi.abkari.tp3abkari.llm.LlmClient;

@Path("/guide")
public class GuideTouristiquResource {
    private LlmClient llmClient;

    public GuideTouristiquResource() {
        this.llmClient = new LlmClient();
    }

    @GET
    @Path("lieu/{ville_ou_pays}")
    public Response villeOuPays(@PathParam("ville_ou_pays") String lieu, @DefaultValue("2") @QueryParam("nb") int nbreEndroits) {
        String answerJson = llmClient.envoyerRequete(lieu, nbreEndroits);
        return Response.ok(answerJson).build();
    }
}
