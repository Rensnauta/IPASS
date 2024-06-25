package server.website.webservices;

import server.website.Model.Category;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/categories")
public class CategoryService {
    @GET
    @Produces("application/json")
    public Response getCategories() {
        JsonArrayBuilder jab = Json.createArrayBuilder();
        JsonObjectBuilder job = Json.createObjectBuilder();
        ArrayList<Category> categories = Category.getCategories();
        for (Category category : categories) {
            job.add("id", category.getId());
            job.add("name", category.getName());
            jab.add(job);
        }
        return Response.ok(jab.build().toString()).build();
    }
}
