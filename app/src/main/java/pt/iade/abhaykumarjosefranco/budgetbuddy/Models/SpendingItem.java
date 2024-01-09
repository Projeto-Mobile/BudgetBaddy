package pt.iade.abhaykumarjosefranco.budgetbuddy.Models;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.annotations.JsonAdapter;

import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Utilities.DateJsonAdapter;
import pt.iade.abhaykumarjosefranco.budgetbuddy.Utilities.WebRequest;

public class SpendingItem implements Serializable {

    private int id;
    private String name;
    @JsonAdapter(DateJsonAdapter.class)
    private LocalDate date;
    private int spendValue;
    UserItem user;

    //public static ArrayList<SpendingItem> spendingItems;

    public SpendingItem() {
        this(0, 0,LocalDate.now(),  "",null);
    }

    public SpendingItem(int id, int spendValue, LocalDate date, String name, UserItem user) {
        this.id = id;
        this.spendValue = spendValue;
        this.date = date;
        this.name = name;
        this.user = user;
        

        /*if (spendingItems == null) {
            spendingItems = new ArrayList<SpendingItem>();
        }*/
    }

    /*public static ArrayList<SpendingItem> List() {
        return spendingItems;
    }


    public void save() {
        // TODO: Send the object's data to our web server and update the database there.
        if (id == 0) {

            id = new Random().nextInt(1000) + 1;
            spendingItems.add(this);
        } else {

        }
    }*/

    public void add(Context context, SpendingItem.SaveResponse response) {
        new Thread(() -> {
            try {
                // This will always use the 'add' endpoint
                String endpoint = "/api/spending/add";
                WebRequest req = new WebRequest(new URL(WebRequest.LOCALHOST + endpoint));

                String resp = req.performPostRequest(SpendingItem.this);

                // Process the response
                SpendingItem respItem = new Gson().fromJson(resp, SpendingItem.class);
                id = respItem.getId(); // Update the id with the new item's id
                response.response();

                // Update UI on success
                new Handler(Looper.getMainLooper()).post(() -> {
                    Toast.makeText(context, "Spending added successfully", Toast.LENGTH_SHORT).show();
                });
            } catch (Exception e) {
                // Update UI on error
                new Handler(Looper.getMainLooper()).post(() -> {
                    Toast.makeText(context, "Failed to add Budget: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("SpendingItem", "Add failed", e);
                });
            }
        }).start();
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
                                WebRequest.LOCALHOST + "/api/spending/list"));
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

    }





    public int getId() {
        return id;
    }

    public void setId(int  id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpendValue() {
        return spendValue;
    }

    public void setSpendValue(int spentValue) {
        this.spendValue = spentValue;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate spentDate) {
        this.date = spentDate;
    }

    public UserItem getUser() {
        return user;
    }

    public void setUser(UserItem user) {
        this.user = user;
    }


    public interface ListResponse {
        public void response(ArrayList<SpendingItem> items);
    }

    public interface GetByIdResponse {
        public void response(SpendingItem item);
    }

    public interface SaveResponse{
        public void response();
    }


}
