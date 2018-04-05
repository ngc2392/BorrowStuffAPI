public class Item {

    private int id;
    private String name;
    private String category;
    private String description;
    private String duration_beginning;
    private String duration_end;
    private int belongsTo;
    private int borrowedBy;
    private String zipcode;


    public Item() {

    }

    public Item(int id, String name, String category, String description, String duration_beginning, String duration_end, int belongsTo, int borrowedBy, String zipcode) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.duration_beginning = duration_beginning;
        this.duration_end = duration_end;
        this.belongsTo = belongsTo;
        this.borrowedBy = borrowedBy;
        this.zipcode = zipcode;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getDuration_beginning() {
        return duration_beginning;
    }

    public String getDuration_end() {
        return duration_end;
    }

    // returns unique id of user
    public int getBelongsTo() {
        return belongsTo;
    }

    // returns unique id of user
    public int getBorrowedBy() {
        return borrowedBy;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuration_beginning(String duration_beginning) {
        this.duration_beginning = duration_beginning;
    }

    public void setDuration_end(String duration_end) {
        this.duration_end = duration_end;
    }

    public void setBelongsTo(int belongsTo) {
       this.belongsTo = belongsTo;
    }

    public void setBorrowedBy(int borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }


    public String toString() {
        String item = "ID:" + id + " NAME: " + name + " CATEGORY:" + category + " DESCRIPTION " + description
                        + " DURATION_BEGINNING: " + duration_beginning + " DURATION_END " + duration_end +
                        " BELONGS TO: " + belongsTo + " BORROWED BY " + borrowedBy + " ZIPCODE: " + zipcode;

        return item;
    }

}
