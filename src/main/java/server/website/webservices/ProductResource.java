package server.website.webservices;

import org.json.JSONObject;
import server.website.DAO.ProductDAO;
import server.website.Model.Product;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Path("/products")
public class ProductResource {
    @Path("{productNr}")
    @GET
    @Produces("application/json")
    public Response getProductByProductNr(@PathParam("productNr") int productNr) {
        ProductDAO.retrieveProducts();
        Product product = Product.getProductByProductNr(productNr);
        if (product != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("productName", product.getName());
            jsonObject.put("productNr", product.getProductNr());
            jsonObject.put("category", product.getCategory());
            jsonObject.put("expirationDate", product.getExpirationDate().toString());
            jsonObject.put("stock", product.getStock());
            jsonObject.put("price", product.getPrice());
            return Response.ok(jsonObject.toString()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("update")
    @PUT
    @Consumes("application/json")
    public Response updateProduct(String jsonString) {
        try (JsonReader jsonReader = Json.createReader(new StringReader(jsonString))) {
            JsonObject jsonObject = jsonReader.readObject();
            System.out.println(jsonObject.toString());
            int productNr = Integer.parseInt(jsonObject.getString("productNr"));
            Product product = Product.getProductByProductNr(productNr);
            if (product != null) {
                product.setName(jsonObject.getString("productName"));
                product.setStock(Integer.parseInt(jsonObject.getString("stock")));
                product.setPrice(Double.parseDouble(jsonObject.getString("price")));
                String dateString = jsonObject.getString("expirationDate");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(dateString);
                product.setExpirationDate(date);
                ProductDAO.updateProduct(product);
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
