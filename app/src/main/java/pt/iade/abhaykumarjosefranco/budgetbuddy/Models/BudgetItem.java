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

public class BudgetItem implements Serializable {
    private int id;
    private String name;
    CategoryItem category;
    UserItem user;
    @JsonAdapter(DateJsonAdapter.class)
    private LocalDate dateStart;
    @JsonAdapter(DateJsonAdapter.class)
    private LocalDate dateEnd;
    private int budgetValue;


    // TODO: REMOVE THIS FOR WEB SERVER IMPLEMENTATION.
    // public static ArrayList<BudgetItem> budgetItems;

    public BudgetItem() {
        this(0, "", new CategoryItem(),null, LocalDate.now(), LocalDate.now(), 0);
    }

    public BudgetItem(int id, String name, CategoryItem category, UserItem user, LocalDate dateStart, LocalDate dateEnd, int budgetValue) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.user = user;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.budgetValue = budgetValue;
    }


    /*public static ArrayList<BudgetItem> List() {
        return budgetItems;
    }

    public static BudgetItem createNewBudgetItem(String category, String type, int budgetValue) {
        // This method creates a new BudgetItem with the provided values.
        int newId = generateUniqueId(); // You should implement this method to generate a unique ID.
        return new BudgetItem(newId, category,type , budgetValue);

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

    public void add(Context context, SaveResponse response) {
        new Thread(() -> {
            try {
                // This will always use the 'add' endpoint
                String endpoint = "/api/budgets/add";
                WebRequest req = new WebRequest(new URL(WebRequest.LOCALHOST + endpoint));

                String resp = req.performPostRequest(BudgetItem.this);

                // Process the response
                BudgetItem respItem = new Gson().fromJson(resp, BudgetItem.class);
                id = respItem.getId(); // Update the id with the new item's id
                category.setId(respItem.getCategory().getId());

                response.response();

                // Update UI on success
                new Handler(Looper.getMainLooper()).post(() -> {
                    Toast.makeText(context, "Budget added successfully", Toast.LENGTH_SHORT).show();
                });
            } catch (Exception e) {
                // Update UI on error
                new Handler(Looper.getMainLooper()).post(() -> {
                    Toast.makeText(context, "Failed to add Budget: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("BudgetItem", "Add failed", e);
                });
            }
        }).start();
    }

    /*public void addBudget(BudgetItem.SaveResponse response) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (id == 0) {
                        WebRequest req = new WebRequest(new URL(WebRequest.LOCALHOST + "/api/budgets/add"));
                        String response = req.performPostRequest(BudgetItem.this);
                        BudgetItem responseItem = new Gson().fromJson(response, BudgetItem.class);
                        id = responseItem.getId();
                    }
                } catch (Exception e) {
                    Toast.makeText(null, "Web request failed: " + e.toString(),
                            Toast.LENGTH_LONG).show();
                    Log.e("BudgetItems", e.toString());
                }
            }
        });
        thread.start();
    }*/

    /*public void save(SaveResponse response) {
        // Send the object's data to our web server and update the database there.
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (id == 0) {
                        // This is a brand new object and must be a INSERT in the database.
                        WebRequest req = new WebRequest(new URL(WebRequest.LOCALHOST + "/api/budgetbuddysave"));
                        String resp = req.performPostRequest(BudgetItem.this);

                        // Get the new ID from the server's response.
                        BudgetItem respItem = new Gson().fromJson(resp, BudgetItem.class);
                        id = respItem.getId();
                        response.response();
                    } else {
                        // This is an update to an existing object and must use UPDATE in the database.
                        WebRequest req = new WebRequest(new URL(WebRequest.LOCALHOST + "/api/budgetbuddysave/" + id));
                        req.performPostRequest(BudgetItem.this);

                        response.response();
                    }
                } catch (Exception e) {
                    Log.e("BudgetItem", e.toString());
                }
            }
        });
        thread.start();
    }*/


    public static void List(BudgetItem.ListResponse response) {
        ArrayList<BudgetItem> items = new ArrayList<BudgetItem>();

        // Fetch a list of items from the web server and populate the list with them.

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    try {
                        WebRequest req = new WebRequest(new URL(
                                WebRequest.LOCALHOST + "/api/budgets/list"));
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



    public void setId(int  id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryItem getCategory() {
        return category;
    }

    public void setCategory(CategoryItem category) {
        this.category = category;
    }

    public UserItem getUser() {
        return user;
    }

    public void setUser(UserItem user) {
        this.user = user;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
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

    public interface SaveResponse{
        public void response();
    }

}





















/*package pt.iade.abhaykumarjosefranco.budgetbuddy.Models;

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

public class BudgetItem implements Serializable {

    private static int idCounter = 0;
    private int id;
    private String category;
    private String typeC;

    @JsonAdapter(DateJsonAdapter.class)
    private LocalDate datestart;
    private LocalDate dateend;
    @JsonAdapter(DateJsonAdapter.class)
    private int budgetValue;

    // TODO: REMOVE THIS FOR WEB SERVER IMPLEMENTATION.
   // public static ArrayList<BudgetItem> budgetItems;

    public BudgetItem() {
        this(0, "","", 0, LocalDate.now(), LocalDate.now());
    }

    public BudgetItem(int id, String category,String typeC,  int budgetValue, LocalDate date1, LocalDate date2) {
        this.id = id;
        this.category = category;
        this.typeC = typeC;
        this.budgetValue = budgetValue;
        this.datestart = date1;
        this.dateend = date2;

        /*if (budgetItems == null) {
            budgetItems = new ArrayList<BudgetItem>();
        }
    }*/

    /*public static ArrayList<BudgetItem> List() {
        return budgetItems;
    }

    public static BudgetItem createNewBudgetItem(String category, String type, int budgetValue) {
        // This method creates a new BudgetItem with the provided values.
        int newId = generateUniqueId(); // You should implement this method to generate a unique ID.
        return new BudgetItem(newId, category,type , budgetValue);

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
    }

    public static BudgetItem GetById(int id) {
        // TODO: Fetch the item from the web server using its id and populate the object.

        return new BudgetItem(id,"","", 0, LocalDate.now(), LocalDate.now());
    }

    public void addBudget(BudgetItem.SaveResponse response) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (id == 0) {
                        WebRequest req = new WebRequest(new URL(
                                WebRequest.LOCALHOST + "/api/budgets/add"));
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

    /*public void save(SaveResponse response) {
        // Send the object's data to our web server and update the database there.
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (id == 0) {
                        // This is a brand new object and must be a INSERT in the database.
                        WebRequest req = new WebRequest(new URL(WebRequest.LOCALHOST + "/api/budgetbuddysave"));
                        String resp = req.performPostRequest(BudgetItem.this);

                        // Get the new ID from the server's response.
                        BudgetItem respItem = new Gson().fromJson(resp, BudgetItem.class);
                        id = respItem.getId();
                        response.response();
                    } else {
                        // This is an update to an existing object and must use UPDATE in the database.
                        WebRequest req = new WebRequest(new URL(WebRequest.LOCALHOST + "/api/budgetbuddysave/" + id));
                        req.performPostRequest(BudgetItem.this);

                        response.response();
                    }
                } catch (Exception e) {
                    Log.e("BudgetItem", e.toString());
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
                                WebRequest.LOCALHOST + "/api/budgets/list"));
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

    public String getType() {
        return typeC;
    }
    public void setType(String typeC) {
        this.typeC = typeC;
    }

    public LocalDate getDatestart() {
        return datestart;
    }
    public void setDatestart(LocalDate datestart) {
        this.datestart = datestart;
    }
    public LocalDate getDateend() {
        return dateend;
    }

    public void setDateend(LocalDate dateend) {
        this.dateend = dateend;
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

    public interface SaveResponse{
        public void response();
    }

}*/
