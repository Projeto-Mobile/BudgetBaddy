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


public class UserItem implements Serializable {

    private static int idCounter = 0;
    private int id;
    private String name;
    private String password;
    private String email;

    // TODO: REMOVE THIS FOR WEB SERVER IMPLEMENTATION.
    //public static ArrayList<UserItem> userItems;

    public UserItem() {
        this(0, "", "", "");
    }

    public UserItem(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;


    }

    /*public static ArrayList<UserItem> List() {
        return userItems;
    }*/


    public static void List(ListResponse response) {
        ArrayList<UserItem> items = new ArrayList<UserItem>();

        // Fetch a list of items from the web server and populate the list with them.

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    try {
                        WebRequest req = new WebRequest(new URL(
                                WebRequest.LOCALHOST + "/api/BudgetBuddy"));
                        String resp = req.performGetRequest();

                        // Get the array from the response.
                        JsonArray arr = new Gson().fromJson(resp, JsonArray.class);
                        ArrayList<UserItem> items = new ArrayList<UserItem>();
                        for (JsonElement elem : arr) {
                            items.add(new Gson().fromJson(elem, UserItem.class));
                        }

                        response.response(items);
                    } catch (Exception e) {
                        Toast.makeText(null, "Web request failed: " + e.toString(),
                                Toast.LENGTH_LONG).show();
                        Log.e("TodoItem", e.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }

    public static UserItem createNewUserItem(String name, String email, String password) {
        int newId = generateUniqueId();
        return new UserItem(newId, name, email, password);

    }
    private static synchronized int generateUniqueId() {
        return idCounter++;
    }

    /*
    public void save() {
        // TODO: Send the object's data to our web server and update the database there.
        if (id == 0) {
            id = new Random().nextInt(1000) + 1;
            userItems.add(this);
        } else {
        }
    }*/



    public static UserItem GetById(int id) {
        // TODO: Fetch the item from the web server using its id and populate the object.

        return new UserItem(id,"","","");
    }

    public static void GetById(int id, GetByIdResponse response) {
        // Fetch the item from the web server using its id and populate the object.
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    try {
                        WebRequest req = new WebRequest(new URL(
                                WebRequest.LOCALHOST + "/api/BudgetBuddy/" + id));
                        String resp = req.performGetRequest();

                        response.response(new Gson().fromJson(resp, UserItem.class));
                    } catch (Exception e) {
                        Toast.makeText(null, "Web request failed: " + e.toString(),
                                Toast.LENGTH_LONG).show();
                        Log.e("NoteItem", e.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }


    public void addUser() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (id == 0) {
                        WebRequest req = new WebRequest(new URL(
                                WebRequest.LOCALHOST + "/api/user/add"));
                        String response = req.performPostRequest(UserItem.this);
                    }
                } catch (Exception e) {
                    Toast.makeText(null, "Web request failed: " + e.toString(),
                            Toast.LENGTH_LONG).show();
                    Log.e("UserItem", e.toString());
                }
            }
        });
        thread.start();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public interface ListResponse {
        public void response(ArrayList<UserItem> items);
    }

    public interface GetByIdResponse {
        public void response(UserItem item);
    }

    public interface SaveResponse{
        public void response();
    }

}

