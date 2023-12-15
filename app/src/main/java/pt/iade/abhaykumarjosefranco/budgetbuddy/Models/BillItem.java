package pt.iade.abhaykumarjosefranco.budgetbuddy.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class BillItem implements Serializable {


    private static int idCounter = 0;
    private int id;
    private String bill;
    private String period;
    private int billValue;
    public static ArrayList<BillItem> billItems;

    //TODO : Associate user ID, budget ID, type budget ID



    public BillItem() {
        this(0, "", "", 0);
    }

    public BillItem(int id, String bill, String period, int billValue) {
        this.id = id;
        this.bill = bill;
        this.period = period;
        this.billValue = billValue;

        if (billItems == null) {
            billItems = new ArrayList<BillItem>();
        }
    }


    /*public static BudgetItem GetById(int id) {
        // TODO: Fetch the item using its id and populate the object.
        return new BudgetItem(id, "", "", 0);
    }*/
    public static ArrayList<BillItem> List() {

        return billItems;
    }

    public static BillItem createNewBudgetItem(String bill, String period, int billValue) {
        // This method creates a new BudgetItem with the provided values.
        int newId = generateUniqueId(); // You should implement this method to generate a unique ID.
        return new BillItem(newId, bill, period, billValue);

    }
    private static synchronized int generateUniqueId() {
        return idCounter++;
    }

    public void save() {
        // TODO: Send the object's data to our web server and update the database there.
        if (id == 0) {
            // This is a brand new object and must be an INSERT in the database.

            // Simulate doing an insert and getting an ID back from the web server.
            id = new Random().nextInt(1000) + 1;
            billItems.add(this);
        } else {
            // This is an update to an existing object and must use UPDATE in the database.
        }
    }

    /**
     * Gets the object from the web server by its ID in the database.
     *
     * @param id ID of the item in the database.
     *
     * @return Object with data from our web server.
     */
    //TODO : ????????????
    public static BillItem GetById(int id) {
        // TODO: Fetch the item from the web server using its id and populate the object.

        return new BillItem(id,"","Choose the duration",0);
    }

    public int getId() {
        return id;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getBillValue() {
        return billValue;
    }

    public void setBillValue(int billValue) {
        this.billValue = billValue;
    }
}

