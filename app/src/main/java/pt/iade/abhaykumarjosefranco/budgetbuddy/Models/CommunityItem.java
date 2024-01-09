package pt.iade.abhaykumarjosefranco.budgetbuddy.Models;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Community;
import pt.iade.abhaykumarjosefranco.budgetbuddy.Utilities.WebRequest;


public class CommunityItem implements Serializable {

    private int id;
    private int amount;

    private String name_community;

    public CommunityItem(){
       this(0, "",  0);
    }
    public CommunityItem(int id, String name_community, int amount){
        this.id = id;
        this.name_community = name_community;
        this.amount = amount;
    }
    /*public static ArrayList<CommunityItem> List(){
        ArrayList<CommunityItem> items = new ArrayList<CommunityItem>();

        items.add(new CommunityItem(1, "Vacation", "a", 10, "b", 20, "c", 30, "d", 40, "e", 50));
        return items;
    }*/

    public static CommunityItem GetById(int id){
        return new CommunityItem(id,"name",0);
    }
/*
    public void save(){
        if (id == 0){
            id = new Random().nextInt(1000) + 1;
            List().add(this);
        }else{

        }
    }*/


    public void addCommunity(CommunityItem.SaveResponse response) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (id == 0) {
                        WebRequest req = new WebRequest(new URL(
                                WebRequest.LOCALHOST + "/api/community/add"));
                        String response = req.performPostRequest(CommunityItem.this);
                    }
                } catch (Exception e) {
                    Toast.makeText(null, "Web request failed: " + e.toString(),
                            Toast.LENGTH_LONG).show();
                    Log.e("CommunityItems", e.toString());
                }
            }
        });
        thread.start();
    }

    public static void List(CommunityItem.ListResponse response) {
        ArrayList<CommunityItem> items = new ArrayList<CommunityItem>();

        // Fetch a list of items from the web server and populate the list with them.

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    try {
                        WebRequest req = new WebRequest(new URL(
                                WebRequest.LOCALHOST + "/api/community/list"));
                        String resp = req.performGetRequest();

                        // Get the array from the response.
                        JsonArray arr = new Gson().fromJson(resp, JsonArray.class);
                        ArrayList<CommunityItem> items = new ArrayList<CommunityItem>();
                        for (JsonElement elem : arr) {
                            items.add(new Gson().fromJson(elem, CommunityItem.class));
                        }

                        response.response(items);
                    } catch (Exception e) {
                        Toast.makeText(null, "Web request failed: " + e.toString(),
                                Toast.LENGTH_LONG).show();
                        Log.e("CommunityItem", e.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }

    public void save(CommunityItem.SaveResponse response) {
        // Send the object's data to our web server and update the database there.
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (id == 0) {
                        // This is a brand new object and must be a INSERT in the database.
                        WebRequest req = new WebRequest(new URL(WebRequest.LOCALHOST + "/api/savecommunity"));
                        String resp = req.performPostRequest(CommunityItem.this);

                        // Get the new ID from the server's response.
                        CommunityItem respItem = new Gson().fromJson(resp, CommunityItem.class);
                        id = respItem.getId();
                        response.response();
                    } else {
                        // This is an update to an existing object and must use UPDATE in the database.
                        WebRequest req = new WebRequest(new URL(WebRequest.LOCALHOST + "/api/savecommunity/" + id));
                        req.performPostRequest(CommunityItem.this);

                        response.response();
                    }
                } catch (Exception e) {
                    Log.e("CommunityItem", e.toString());
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
        return name_community;
    }

    public void setName(String name_community) {
        this.name_community = name_community;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public interface ListResponse {
        public void response(ArrayList<CommunityItem> items);
    }

    public interface GetByIdResponse {
        public void response(CommunityItem item);
    }

    public interface SaveResponse{
        public void response();
    }

}
