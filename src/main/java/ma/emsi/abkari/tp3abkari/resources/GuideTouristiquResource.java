package ma.emsi.abkari.tp3abkari.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
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
    public Response villeOuPays(@PathParam("ville_ou_pays") String lieu) {
        String answerJson = llmClient.envoyerRequete(lieu);
        return Response.ok(answerJson).build();
    }
}
