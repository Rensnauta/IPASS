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
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        JsonObjectBuilder builder = Json.createObjectBuilder();
        ArrayList<Product> products = Product.getProducts();
        for (Product product : products) {
            builder.add("productName", product.getName());
            builder.add("productNr", product.getProductNr());
            builder.add("category", product.getCategory());
            builder.add("expirationDate", product.getExpirationDate().toString());
            builder.add("stock", product.getStock());
            builder.add("price", product.getPrice());
            arrayBuilder.add(builder);
        }
        return Response.ok(arrayBuilder.build().toString()).build();
    }
}
