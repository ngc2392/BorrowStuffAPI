import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Link;
import javax.ws.rs.WebApplicationException;

@Path("/users")
public class UserResource {

    private static Map<Integer, User> userDB = new HashMap<Integer, User>();
    private static AtomicInteger idCounter = new AtomicInteger();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {

        List<JsonObject> list = new ArrayList<JsonObject>();

        //fill list
        for (Map.Entry<Integer, User> pair : userDB.entrySet()) {
            System.out.println("KEY:" + pair.getKey() + "VALUE: " + pair.getValue().toString());

            int id = pair.getValue().getId();
            String firstName = pair.getValue().getFirstName();
            String lastName = pair.getValue().getLastName();
            String zipCode = pair.getValue().getZipcode();
            ArrayList<Integer> itemsBorrowedIds= pair.getValue().getitemsBorrowed();
            ArrayList<Integer> itemsLendingIds = pair.getValue().getitemsLending();

            JsonArray linksForBorrowedItems = new JsonArray();
            for(int i = 0; i < itemsBorrowedIds.size(); i++) {
                Link link = Link.fromUri("http://localhost:8080/BorrowStuffWebApplication_war_exploded/items/"+itemsBorrowedIds.get(i)).rel("get").type("text/plain").build();
                linksForBorrowedItems.add(link.toString());
            }

            JsonArray linksForLendingItems = new JsonArray();
            for(int i = 0; i < itemsLendingIds.size(); i++) {
                Link link = Link.fromUri("http://localhost:8080/BorrowStuffWebApplication_war_exploded/items/"+itemsBorrowedIds.get(i)).rel("get").type("text/plain").build();
                linksForLendingItems.add(link.toString());
            }

            JsonObject userToAddToList = new JsonObject();

            userToAddToList.addProperty("id", id);
            userToAddToList.addProperty("first_name", firstName);
            userToAddToList.addProperty("last_name", lastName);
            userToAddToList.addProperty("zip_code", zipCode);
            userToAddToList.add("items_borrowed", linksForBorrowedItems);
            userToAddToList.add("items_lending", linksForLendingItems);

            System.out.println("Adding the following item to the list: " + userToAddToList.toString());

            list.add(userToAddToList);
        }

        //Response response = Response.ok().entity(list).build();
        Response response = Response.ok().entity(list.toString()).build();

        return response;
    }

    @GET
    @Path("zipcode")
    public Response getAllUsersByZipcode(@QueryParam("zipcode") String zipcode) {
        System.out.println("Getting users from zipcode: " + zipcode);

        ArrayList<JsonObject> items = new ArrayList<JsonObject>();

        for (Map.Entry<Integer, User> pair : userDB.entrySet()) {
            System.out.println("KEY: " + pair.getKey() + "VALUE: " + pair.getValue().toString());

            System.out.println("ZIPCODE FROM OBJECT IS " + pair.getValue().getZipcode());

            if(pair.getValue().getZipcode().equals(zipcode)) {

                int id = pair.getValue().getId();
                String firstName = pair.getValue().getFirstName();
                String lastName = pair.getValue().getLastName();
                String zipCode = pair.getValue().getZipcode();
                ArrayList<Integer> itemsBorrowedIds= pair.getValue().getitemsBorrowed();
                ArrayList<Integer> itemsLendingIds = pair.getValue().getitemsLending();

                JsonArray linksForBorrowedItems = new JsonArray();
                for(int i = 0; i < itemsBorrowedIds.size(); i++) {
                    Link link = Link.fromUri("http://localhost:8080/BorrowStuffWebApplication_war_exploded/items/"+itemsBorrowedIds.get(i)).rel("get").type("text/plain").build();
                    linksForBorrowedItems.add(link.toString());
                }

                JsonArray linksForLendingItems = new JsonArray();
                for(int i = 0; i < itemsLendingIds.size(); i++) {
                    Link link = Link.fromUri("http://localhost:8080/BorrowStuffWebApplication_war_exploded/items/"+itemsBorrowedIds.get(i)).rel("get").type("text/plain").build();
                    linksForLendingItems.add(link.toString());
                }

                JsonObject userToAddToList = new JsonObject();

                userToAddToList.addProperty("id", id);
                userToAddToList.addProperty("first_name", firstName);
                userToAddToList.addProperty("last_name", lastName);
                userToAddToList.addProperty("zip_code", zipCode);
                userToAddToList.add("items_borrowed", linksForBorrowedItems);
                userToAddToList.add("items_lending", linksForLendingItems);

                System.out.println("Adding the following item to the list: " + userToAddToList.toString());

                items.add(userToAddToList);


            }

        }

        Response response = Response.ok().entity(items.toString()).build();

        return response;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserInJson(@PathParam("id") int id) {

        System.out.println("THE ID is " + id);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        //System.out.println("The description is " + item.getDescription());

        ArrayList<String> linksForItemsBorrowed;
        ArrayList<String> linksForItemsLending;

        Response response;

        if(!userDB.containsKey(id)) {
            System.out.println("THEY KEY ISN'T THERE!!");
            response = Response.status(400).type(MediaType.TEXT_PLAIN).entity("The key was not in the database").build();
        } else {
            User user = userDB.get(id);


            ArrayList<Integer> itemsBorrowedIds = user.getitemsBorrowed();
            System.out.println("The size of itemsBorrowedIds is " + itemsBorrowedIds.size());


            ArrayList<String> linksForBorrowedItems = new ArrayList<String>();

            for(int i = 0; i < itemsBorrowedIds.size(); i++) {
                Link link = Link.fromUri("http://localhost:8080/BorrowStuffWebApplication_war_exploded/items/" + itemsBorrowedIds.get(i)).rel("get").type("text/plain").build();
                System.out.println("The link is " + link.toString());
                linksForBorrowedItems.add(link.toString());
            }

            ArrayList<Integer> itemsLendingIds = user.getitemsLending();

            ArrayList<String> linksForLendingItems = new ArrayList<String>();

            for(int i = 0; i < itemsLendingIds.size(); i++) {
                Link link = Link.fromUri("http://localhost:8080/BorrowStuffWebApplication_war_exploded/items/"+itemsLendingIds.get(i)).rel("get").type("text/plain").build();
                System.out.println("The link is " + link.toString());
                linksForLendingItems.add(link.toString());
            }

            if(user == null) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }

            //String jsonInString =  gson.toJson(user);
            JsonObject userToReturnInJson = new JsonObject();
            userToReturnInJson.addProperty("first_name", user.getFirstName());
            userToReturnInJson.addProperty("last_name", user.getLastName());
            userToReturnInJson.addProperty("zip_code", user.getZipcode());

            JsonArray jsonArrayItemsBorrowed = new JsonArray();
            for(int i = 0; i < linksForBorrowedItems.size(); i++) {
                jsonArrayItemsBorrowed.add(linksForBorrowedItems.get(i));
            }

            JsonArray jsonArrayItemsLending = new JsonArray();
            for(int i = 0; i < linksForLendingItems.size(); i++) {
                jsonArrayItemsLending.add(linksForLendingItems.get(i));
            }

            String data = new Gson().toJson(linksForBorrowedItems);
            //JsonArray jsonArrayItemsBorrowed = new JsonParser().parse(data).getAsJsonArray();

            userToReturnInJson.add("items_borrowed", jsonArrayItemsBorrowed);

            //data = new Gson().toJson(linksForLendingItems);
            //JsonArray jsonArrayItemsLending = new JsonParser().parse(data).getAsJsonArray();

            userToReturnInJson.add("items_lending", jsonArrayItemsLending);

            System.out.println("WE ARE RETURNING " + gson.toJson(userToReturnInJson).toString());

            response = Response.status(200).type(MediaType.APPLICATION_JSON).entity(userToReturnInJson.toString()).build();
        }

        return response;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON) // This makes it return as json in postman.  Without this, it comes up as plain text
    public Response createUser(String x) {

        System.out.println("IN createUser()");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonElement element = gson.fromJson(x, JsonElement.class); // create a json element

        JsonObject jsonObj = element.getAsJsonObject(); //Now we can convert the JsonElement to a JsonObject

        System.out.println(x.toString());

        int idForUser = idCounter.incrementAndGet();

        ArrayList<Integer> itemsBorrowedList = new ArrayList<Integer>();

        JsonArray itemsBorrowedJson = jsonObj.getAsJsonArray("items_borrowed");
        System.out.println("The ids for borrowed items are: " + itemsBorrowedJson.toString());

        if(itemsBorrowedJson != null) {
            for(int i = 0; i < itemsBorrowedJson.size(); i++) {
                itemsBorrowedList.add(itemsBorrowedJson.get(i).getAsInt());
            }
        }

        ArrayList<Integer> itemsLendingList = new ArrayList<Integer>();

        JsonArray itemsLendingJson = jsonObj.getAsJsonArray("items_lending");
        System.out.println("The ids for lending items are: " + itemsLendingJson.toString());

        if(itemsLendingJson != null) {
            for(int i = 0; i < itemsLendingJson.size(); i++) {
                itemsLendingList.add(itemsLendingJson.get(i).getAsInt());
            }
        }

        User user = new User(idForUser, jsonObj.get("first_name").toString(), jsonObj.get("last_name").toString(), jsonObj.get("zipcode").toString(),
                    itemsBorrowedList, itemsLendingList);

        userDB.put(idForUser, user);

        //convert to json by gson
        String json = gson.toJson(user);

        Response response = Response.status(200).entity("The following user was created" + json).build();

        return response;
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(String x, @PathParam("id")int id) {

        System.out.println("IN updateUser()");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Response response;

        if(userDB.containsKey(id)) {

            JsonElement element = gson.fromJson(x, JsonElement.class); // create a json element

            JsonObject jsonObj = element.getAsJsonObject(); //Now we can convert the JsonElement to a JsonObject

            ArrayList<Integer> itemsBorrowedList = new ArrayList<Integer>();

            JsonArray itemsBorrowedJson = jsonObj.getAsJsonArray("items_borrowed");
            System.out.println("The ids for borrowed items are: " + itemsBorrowedJson.toString());

            if(itemsBorrowedJson != null) {
                for(int i = 0; i < itemsBorrowedJson.size(); i++) {
                    itemsBorrowedList.add(itemsBorrowedJson.get(i).getAsInt());
                }
            }

            ArrayList<Integer> itemsLendingList = new ArrayList<Integer>();

            JsonArray itemsLendingJson = jsonObj.getAsJsonArray("items_lending");
            System.out.println("The ids for lending items are: " + itemsLendingJson.toString());

            if(itemsLendingJson != null) {
                for(int i = 0; i < itemsLendingJson.size(); i++) {
                    itemsLendingList.add(itemsLendingJson.get(i).getAsInt());
                }
            }

            User user = new User(id, jsonObj.get("first_name").toString(), jsonObj.get("last_name").toString(), jsonObj.get("zipcode").toString(),
                    itemsBorrowedList, itemsLendingList);

            userDB.put(id, user);
            response = Response.status(200).entity("THE BODY WAS: " + x.toString()).build();

        } else { //create the object

            User tempUser = gson.fromJson(x, User.class);

            userDB.put(id, tempUser);
            response = Response.status(201).build();
        }

        return response;
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN) //return deleted user
    public Response deleteUserById(@PathParam("id") int id) {
        User userToDelete = userDB.get(id);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String json = gson.toJson(userToDelete); //put the object to json to put in entity body of response

        Response response;

        if(userDB.containsKey(id)) {
            userDB.remove(id);
            response = Response.status(200).entity("Item was successfully deleted").build();
        } else { //element does not exist
            response = Response.status(204).entity("The item does not exist in the database").build();
        }
        return response;
    }
}