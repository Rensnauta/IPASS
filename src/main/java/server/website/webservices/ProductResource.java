package server.website.webservices;

import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONArray;
import org.json.JSONObject;
import server.website.DAO.ProductDAO;
import server.website.Model.Product;

import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static server.website.DAO.ProductDAO.getExpiredOrExpiringProducts;

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

    @Path("expiring")
    @GET
    @Produces("application/json")
    public Response getExpiringProducts() {
        List<Product> expiringProducts = ProductDAO.getExpiredOrExpiringProducts();
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (Product product : expiringProducts) {
            builder.add("productName", product.getName());
            builder.add("productNr", product.getProductNr());
            builder.add("category", product.getCategory());
            builder.add("expirationDate", product.getExpirationDate().toString());
            builder.add("stock", product.getStock());
            builder.add("price", product.getPrice());
            jab.add(builder);
        }
        return Response.ok(jab.build().toString()).build();
    }

    @Path("expired")
    @POST
    @Consumes("application/json")
    public Response updateExpired(String json) {
        try (JsonReader jsonReader = Json.createReader(new StringReader(json))) {
            JsonArray jsonArray = jsonReader.readArray();
            for (JsonValue value : jsonArray) {
                JsonObject jsonObject = value.asJsonObject();
                String productNr = jsonObject.getString("productNr");
                String expirationDate = jsonObject.getString("expirationDate");
                int expiredAmount = jsonObject.getInt("expiredAmount");
                Product product = Product.getProductByProductNr(Integer.parseInt(productNr));
                if (product == null) {
                    return Response.status(Response.Status.NOT_FOUND).entity("Product not found").build();
                }
                int newStock = product.getStock() - expiredAmount;
                product.setStock(newStock);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(expirationDate);
                product.setExpirationDate(date);
                ProductDAO.updateProduct(product);
            }
            return Response.ok().build();
        } catch (JsonException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid JSON").build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
