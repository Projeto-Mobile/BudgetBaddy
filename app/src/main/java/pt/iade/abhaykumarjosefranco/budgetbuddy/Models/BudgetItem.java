package pt.iade.abhaykumarjosefranco.budgetbuddy.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class BudgetItem implements Serializable {

    private static int idCounter = 0;
    private int id;
    private String category;
    private String period;
    private int budgetValue;

    public BudgetItem() {
        this(0, "", "", 0);
    }

    public BudgetItem(int id, String category, String period, int budgetValue) {
        this.id = id;
        this.category = category;
        this.period = period;
        this.budgetValue = budgetValue;
    }

    public static BudgetItem GetById(int id) {
        // TODO: Fetch the item from the web server using its id and populate the object.
        return new BudgetItem(id, "", "", 0);
    }

    public static ArrayList<BudgetItem> List() {
        ArrayList<BudgetItem> items = new ArrayList<BudgetItem>();


        return items;
    }

    public static BudgetItem createNewBudgetItem(String category, String period, int budgetValue) {
        // This method creates a new BudgetItem with the provided values.
        int newId = generateUniqueId(); // You should implement this method to generate a unique ID.
        return new BudgetItem(newId, category, period, budgetValue);

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
        } else {
            // This is an update to an existing object and must use UPDATE in the database.
        }
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getBudgetValue() {
        return budgetValue;
    }

    public void setBudgetValue(int budgetValue) {
        this.budgetValue = budgetValue;
    }
}
