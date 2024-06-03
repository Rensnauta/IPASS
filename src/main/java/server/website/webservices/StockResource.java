package server.website.webservices;

import server.website.Model.Product;
import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/stock")
public class StockResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {
        System.out.println("Getting products");
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        JsonObjectBuilder builder = Json.createObjectBuilder();
        ArrayList<Product> products = Product.getProducts();
        for (Product product : products) {
            builder.add("Product", product.getName());
            builder.add("ProductNr", product.getProductNr());
            builder.add("Category", product.getCategory());
            builder.add("ExpirationDate", product.getExpirationDate().toString());
            builder.add("Stock", product.getStock());
            builder.add("Price", product.getPrice());
            arrayBuilder.add(builder);
        }
        return Response.ok(arrayBuilder.build().toString()).build();
    }
}
