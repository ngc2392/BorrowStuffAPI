import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*
I used the following link to construct the http response codes:
https://developer.yahoo.com/social/rest_api_guide/http-response-codes.html
*/

@Path("/items")
public class ItemResource {
    private static Map<Integer, Item> itemDB = new HashMap<Integer, Item>();
    private AtomicInteger idCounter = new AtomicInteger();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllItems() {

        List<Item> list = new ArrayList<Item>();

        //fill list
        for (Map.Entry<Integer, Item> pair : itemDB.entrySet()) {
            System.out.println("KEY:" + pair.getKey() + "VALUE: " + pair.getValue().toString());
            list.add(pair.getValue());

        }

        Response response = Response.ok().entity(list).build();

        return response;

    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    //I can just return an 'Item' object here and will be in json, but I want to include the HTTP response code
    public Response getItemInJson(@PathParam("id") int id) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Response response;

        if(!itemDB.containsKey(id)) {
            System.out.println("THE KEY ISN'T THERE!!");
            response = Response.status(401).type(MediaType.TEXT_PLAIN).entity("The key was not in the database").build();
        } else {
            Item item = itemDB.get(id);

            if(item == null) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }

            String jsonInString =  gson.toJson(item);

            System.out.println(jsonInString);
           // response = Response.status(12345).type(MediaType.APPLICATION_JSON).entity(jsonInString).build();
            response = Response.status(200).type(MediaType.APPLICATION_JSON).entity(jsonInString).build();
        }

       return response;
    }

    @GET
    @Path("/category")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemsByCategory(@QueryParam("category") String category) {

        ArrayList<Item> itemsByCategory = new ArrayList<Item>();

        for (Map.Entry<Integer, Item> pair : itemDB.entrySet()) {
            System.out.println("KEY:" + pair.getKey() + "VALUE: " + pair.getValue().toString());
            if(pair.getValue().toString().equals(category)) {
                itemsByCategory.add(pair.getValue());
            }
        }

        Response response = Response.ok().entity(itemsByCategory).build();

        return response;
    }

    @GET
    @Path("/queryZip")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemsByZipCode(@QueryParam("zipcode") String zipCode) {

        System.out.println("Getting items from zipcode: " + zipCode);

        ArrayList<Item> items = new ArrayList<Item>();

        for (Map.Entry<Integer, Item> pair : itemDB.entrySet()) {
            System.out.println("KEY: " + pair.getKey() + "VALUE: " + pair.getValue().toString());

            if(pair.getValue().getZipcode().equals(zipCode)) { // not comparing correctly
                items.add(pair.getValue());
            }

        }

        Response response = Response.ok().entity(items).build();

        return response;
    }

    // https://www.mkyong.com/webservices/jax-rs/get-http-header-in-jax-rs/
    //@POST at /items
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    // get item from entity body
    public Response createItem(String x, @Context HttpHeaders headers) {

        //use gson to convert it right to an 'item' Object??
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonElement element = gson.fromJson(x, JsonElement.class); // create a json element

        JsonObject jsonObj = element.getAsJsonObject(); //Now we can convert the JsonElement to a JsonObject

        System.out.println(x.toString());

        int idForItem = idCounter.incrementAndGet();

        //create new item object
        Item newItem = new Item(Integer.parseInt(jsonObj.get("id").toString()), jsonObj.get("name").toString(), jsonObj.get("category").toString(),
                jsonObj.get("description").toString(), jsonObj.get("duration_beginning").toString(), jsonObj.get("duration_end").toString(),
                Integer.parseInt(jsonObj.get("belongs_to").toString()), Integer.parseInt(jsonObj.get("borrowed_by").toString()), jsonObj.get("zipcode").toString());


        String userAgent = headers.getRequestHeader("user-agent").get(0);

        Response response = Response.status(200).entity("THE BODY WAS: " + x.toString()).build();

        // add the item to our database
        itemDB.put(newItem.getId(), newItem);

        System.out.println("COMING FROM THE DATABASE DESCRIPTION: " + itemDB.get(newItem.getId()).getDescription());

        return response;

    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateItemById(@PathParam("id") int id, String x) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Item item = gson.fromJson(x, Item.class);

        Response response;

        if(item == null) {
            response = Response.status(404).build();
        }

        if(itemDB.containsKey(id)) {
            itemDB.put(id, item);
            response = Response.status(200).entity("THE BODY WAS: " + x.toString()).build();
        } else { //create the object
            itemDB.put(id, item);
            response = Response.status(201).build();
        }

        return response;

    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteItemById(@PathParam("id") int id) {

        Item itemToDelete = itemDB.get(id);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String json = gson.toJson(itemToDelete); //put the object to json to put in entity body of response

        Response response;

        if(itemDB.containsKey(id)) {
            itemDB.remove(id);
            response = Response.status(200).entity("Item was successfully deleted").build();
        } else { //element does not exist
            response = Response.status(400).entity("The item does not exist in the database").build();
        }

       return response;

    }

}