import java.util.ArrayList;

public class User {

    private int id;
    private String first_name;
    private String last_name;
    private String zipcode;
    private ArrayList<Integer> itemsBorrowed; //hashset
    private ArrayList<Integer> itemsLending;


    //return list of links, each link to a specific item
    //can store the id on the backend whatever way is conveient to me.  We will present a link to the user.

    public User() {

    }

    public User(int id, String first_name, String last_name, String zipcode, ArrayList<Integer> itemsBorrowed, ArrayList<Integer> itemsLending) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.zipcode = zipcode;
        this.itemsBorrowed = itemsBorrowed;
        this.itemsLending = itemsLending;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getZipcode() {
        return zipcode;
    }

    public ArrayList<Integer> getitemsBorrowed() {
        return itemsBorrowed;
    }

    public ArrayList<Integer> getitemsLending() {
        return itemsLending;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String name) {
        this.first_name = name;
    }

    public void setLastName(String name) {
        this.last_name = name;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setItemsBorrowed(ArrayList<Integer> items) {
       this.itemsBorrowed = items;
    }

    public void setItemsLending(ArrayList<Integer> items) {
        this.itemsLending = items;
    }

    public String toString() {
        return "hello";
    }

}
