package pt.iade.abhaykumarjosefranco.budgetbuddy.Models;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Spendings;
import pt.iade.abhaykumarjosefranco.budgetbuddy.Utilities.WebRequest;

public class SpendingItem implements Serializable {

    private static int idCounter = 0;
    private int id;
    private String spentcategory;
    //@JsonAdapter(DateJsonAdapter.class)
    private LocalDate spentDate;
    //@JsonAdapter(DateJsonAdapter.class)

    private int spentValue;

    public static ArrayList<SpendingItem> spendingItems;

    public SpendingItem() {
        this(0, "", 0, LocalDate.now());
    }

    public SpendingItem(int id, String spentcategory, int spentValue, LocalDate date) {
        this.id = id;
        this.spentcategory = spentcategory;
        this.spentValue = spentValue;
        this.spentDate = date;

        if (spendingItems == null) {
            spendingItems = new ArrayList<SpendingItem>();
        }
    }

    public static ArrayList<SpendingItem> List() {
        return spendingItems;
    }


    public void save() {
        // TODO: Send the object's data to our web server and update the database there.
        if (id == 0) {

            id = new Random().nextInt(1000) + 1;
            spendingItems.add(this);
        } else {

        }
    }
    /*
    public void addSpending() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (id == 0) {
                        WebRequest req = new WebRequest(new URL(
                                WebRequest.LOCALHOST + "/api/spending/add"));
                        String response = req.performPostRequest(SpendingItem.this);
                    }
                } catch (Exception e) {
                    Toast.makeText(null, "Web request failed: " + e.toString(),
                            Toast.LENGTH_LONG).show();
                    Log.e("SpendingItems", e.toString());
                }
            }
        });
        thread.start();
    }


    public static void List(SpendingItem.ListResponse response) {
        ArrayList<SpendingItem> items = new ArrayList<SpendingItem>();

        // Fetch a list of items from the web server and populate the list with them.

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    try {
                        WebRequest req = new WebRequest(new URL(
                                WebRequest.LOCALHOST + "/api/BudgetBuddy/spending"));
                        String resp = req.performGetRequest();

                        // Get the array from the response.
                        JsonArray arr = new Gson().fromJson(resp, JsonArray.class);
                        ArrayList<SpendingItem> items = new ArrayList<SpendingItem>();
                        for (JsonElement elem : arr) {
                            items.add(new Gson().fromJson(elem, SpendingItem.class));
                        }

                        response.response(items);
                    } catch (Exception e) {
                        Toast.makeText(null, "Web request failed: " + e.toString(),
                                Toast.LENGTH_LONG).show();
                        Log.e("SpendingItem", e.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }*/

    public static SpendingItem GetById(int id) {

        return new SpendingItem(id,"",0,LocalDate.now());
    }

    public int getId() {
        return id;
    }

    public String getSpentcategory() {
        return spentcategory;
    }

    public void setSpentcategory(String spentcategory) {
        this.spentcategory = spentcategory;
    }

    public int getPeriod() {
        return spentValue;
    }

    public void setPeriod(int spentValue) {
        this.spentValue = spentValue;
    }

    public LocalDate getDate() {
        return spentDate;
    }

    public void setDate(LocalDate spentDate) {
        this.spentDate = spentDate;
    }


    public interface ListResponse {
        public void response(ArrayList<SpendingItem> items);
    }

    public interface GetByIdResponse {
        public void response(SpendingItem item);
    }

}
