package pt.iade.abhaykumarjosefranco.budgetbuddy.Models;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import pt.iade.abhaykumarjosefranco.budgetbuddy.Utilities.WebRequest;

public class ChallengeItem implements Serializable {
    private static int idCounter = 0;
    private int id;
    private String challenge;
    private String period;
    CategoryItem category;
    UserItem user;


    // TODO: REMOVE THIS FOR WEB SERVER IMPLEMENTATION.
    //public static ArrayList<ChallengeItem> challengeItems;

    public ChallengeItem() {
        this(0, "", "");
    }

    public ChallengeItem(int id, String challenge, String period) {
        this.id = id;
        this.challenge = challenge;
        this.period = period;

       /* if (challengeItems == null) {
            challengeItems = new ArrayList<ChallengeItem>();
        }*/
    }

    /*public static ChallengeItem GetById(int id) {
        // TODO: Fetch the item using its id and populate the object.
        return new ChallengeItem(id, "", "", 0);
    }*/

   /* public static ArrayList<ChallengeItem> List() {
        return challengeItems;
    }*/

    public static ChallengeItem createNewChallengeItem(String challenge, String period) {
        int newId = generateUniqueId();
        return new ChallengeItem(newId, challenge, period);

    }
    private static synchronized int generateUniqueId() {
        return idCounter++;
    }


    /*public void save() {
        // TODO: Send the object's data to our web server and update the database there.
        if (id == 0) {
            id = new Random().nextInt(1000) + 1;
            challengeItems.add(this);
        } else {
        }
    }*/


    /*public void save(ChallengeItem.SaveResponse response) {
        // Send the object's data to our web server and update the database there.
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (id == 0) {
                        // This is a brand new object and must be a INSERT in the database.
                        WebRequest req = new WebRequest(new URL(WebRequest.LOCALHOST + "/api/savechallenge"));
                        String resp = req.performPostRequest(ChallengeItem.this);

                        // Get the new ID from the server's response.
                        ChallengeItem respItem = new Gson().fromJson(resp, ChallengeItem.class);
                        id = respItem.getId();
                        response.response();
                    } else {
                        // This is an update to an existing object and must use UPDATE in the database.
                        WebRequest req = new WebRequest(new URL(WebRequest.LOCALHOST + "/api/savechallenge/" + id));
                        req.performPostRequest(ChallengeItem.this);

                        response.response();
                    }
                } catch (Exception e) {
                    Log.e("ChallengeItem", e.toString());
                }
            }
        });
        thread.start();
    }

    public static ChallengeItem GetById(int id) {
        // TODO: Fetch the item from the web server using its id and populate the object.

        return new ChallengeItem(id,"","");
    }*/


    public void add(Context context, ChallengeItem.SaveResponse response) {
        new Thread(() -> {
            try {
                // This will always use the 'add' endpoint
                String endpoint = "/api/challenge/add";
                WebRequest req = new WebRequest(new URL(WebRequest.LOCALHOST + endpoint));

                String resp = req.performPostRequest(ChallengeItem.this);

                // Process the response
                ChallengeItem respItem = new Gson().fromJson(resp, ChallengeItem.class);
                id = respItem.getId(); // Update the id with the new item's id
                category.setId(respItem.getCategory().getId());

                response.response();

                // Update UI on success
                new Handler(Looper.getMainLooper()).post(() -> {
                    Toast.makeText(context, "Challenge added successfully", Toast.LENGTH_SHORT).show();
                });
            } catch (Exception e) {
                // Update UI on error
                new Handler(Looper.getMainLooper()).post(() -> {
                    Toast.makeText(context, "Failed to add Challenge: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("ChallengeItem", "Add failed", e);
                });
            }
        }).start();
    }

    public static void List(ChallengeItem.ListResponse response) {
        ArrayList<ChallengeItem> items = new ArrayList<ChallengeItem>();

        // Fetch a list of items from the web server and populate the list with them.

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    try {
                        WebRequest req = new WebRequest(new URL(
                                WebRequest.LOCALHOST + "/api/challenge/list"));
                        String resp = req.performGetRequest();

                        // Get the array from the response.
                        JsonArray arr = new Gson().fromJson(resp, JsonArray.class);
                        ArrayList<ChallengeItem> items = new ArrayList<ChallengeItem>();
                        for (JsonElement elem : arr) {
                            items.add(new Gson().fromJson(elem, ChallengeItem.class));
                        }

                        response.response(items);
                    } catch (Exception e) {
                        Toast.makeText(null, "Web request failed: " + e.toString(),
                                Toast.LENGTH_LONG).show();
                        Log.e("Challenge", e.toString());

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

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public interface ListResponse {
        public void response(ArrayList<ChallengeItem> items);
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

    public interface GetByIdResponse {
        public void response(ChallengeItem item);
    }

    public interface SaveResponse {
        public void response();
    }
}
















/*package pt.iade.abhaykumarjosefranco.budgetbuddy.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class ChallengeItem implements Serializable {
    private static int idCounter = 0;
    private int id;
    private String challenge;
    private String period;

    public static ArrayList<ChallengeItem> challengeItems;

    public ChallengeItem() {
        this(0, "", "");
    }

    public ChallengeItem(int id, String challenge, String period) {
        this.id = id;
        this.challenge = challenge;
        this.period = period;

        if (challengeItems == null) {
            challengeItems = new ArrayList<ChallengeItem>();
        }
    }

    /*public static BudgetItem GetById(int id) {
        // TODO: Fetch the item using its id and populate the object.
        return new BudgetItem(id, "", "", 0);
    }

    public static ArrayList<ChallengeItem> List() {
        ArrayList<ChallengeItem> challengeItems = new ArrayList<ChallengeItem>();
        challengeItems.add(new ChallengeItem(1,"Name 1", ""));

        return challengeItems;
    }

    public static ChallengeItem createNewChallengeItem(String challenge, String period) {
        // This method creates a new BudgetItem with the provided values.
        int newId = generateUniqueId(); // You should implement this method to generate a unique ID.
        return new ChallengeItem(newId, challenge, period);

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
            challengeItems.add(this);
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

    public static ChallengeItem GetById(int id) {
        // TODO: Fetch the item from the web server using its id and populate the object.

        return new ChallengeItem(id,"","");
    }

    public int getId() {
        return id;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

}*/

