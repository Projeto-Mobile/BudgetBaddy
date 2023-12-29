package pt.iade.abhaykumarjosefranco.budgetbuddy.Models;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Utilities.WebRequest;

public class BudgetItem implements Serializable {

    private static int idCounter = 0;
    private int id;
    private String category;
    private String period;
    private int budgetValue;

    // TODO: REMOVE THIS FOR WEB SERVER IMPLEMENTATION.
   // public static ArrayList<BudgetItem> budgetItems;

    public BudgetItem() {
        this(0, "", "", 0);
    }

    public BudgetItem(int id, String category, String period, int budgetValue) {
        this.id = id;
        this.category = category;
        this.period = period;
        this.budgetValue = budgetValue;

        /*if (budgetItems == null) {
            budgetItems = new ArrayList<BudgetItem>();
        }*/
    }

    /*public static ArrayList<BudgetItem> List() {
        return budgetItems;
    }*/

    public static BudgetItem createNewBudgetItem(String category, String period, int budgetValue) {
        // This method creates a new BudgetItem with the provided values.
        int newId = generateUniqueId(); // You should implement this method to generate a unique ID.
        return new BudgetItem(newId, category, period, budgetValue);

    }
    private static synchronized int generateUniqueId() {
        return idCounter++;
    }

    /*
    public void save() {
        // TODO: Send the object's data to our web server and update the database there.
        if (id == 0) {
            // This is a brand new object and must be an INSERT in the database.

            // Simulate doing an insert and getting an ID back from the web server.
            id = new Random().nextInt(1000) + 1;
            budgetItems.add(this);
        } else {
            // This is an update to an existing object and must use UPDATE in the database.
        }
    }*/

    public static BudgetItem GetById(int id) {
        // TODO: Fetch the item from the web server using its id and populate the object.

        return new BudgetItem(id,"","Choose the duration",0);
    }

    public void addBudget() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (id == 0) {
                        WebRequest req = new WebRequest(new URL(
                                WebRequest.LOCALHOST + "/api/budget/add"));
                        String response = req.performPostRequest(BudgetItem.this);
                    }
                } catch (Exception e) {
                    Toast.makeText(null, "Web request failed: " + e.toString(),
                            Toast.LENGTH_LONG).show();
                    Log.e("BudgetItems", e.toString());
                }
            }
        });
        thread.start();
    }


    public static void List(BudgetItem.ListResponse response) {
        ArrayList<BudgetItem> items = new ArrayList<BudgetItem>();

        // Fetch a list of items from the web server and populate the list with them.

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    try {
                        WebRequest req = new WebRequest(new URL(
                                WebRequest.LOCALHOST + "/api/BudgetBuddy/budgets"));
                        String resp = req.performGetRequest();

                        // Get the array from the response.
                        JsonArray arr = new Gson().fromJson(resp, JsonArray.class);
                        ArrayList<BudgetItem> items = new ArrayList<BudgetItem>();
                        for (JsonElement elem : arr) {
                            items.add(new Gson().fromJson(elem, BudgetItem.class));
                        }

                        response.response(items);
                    } catch (Exception e) {
                        Toast.makeText(null, "Web request failed: " + e.toString(),
                                Toast.LENGTH_LONG).show();
                        Log.e("BudgetItem", e.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

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

    public interface ListResponse {
        public void response(ArrayList<BudgetItem> items);
    }

    public interface GetByIdResponse {
        public void response(BudgetItem item);
    }
}
