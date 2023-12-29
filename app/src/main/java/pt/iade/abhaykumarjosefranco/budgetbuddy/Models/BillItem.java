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



    public static ArrayList<BillItem> List() {
        return billItems;
    }

    public static BillItem createNewBudgetItem(String bill, String period, int billValue) {
        int newId = generateUniqueId();
        return new BillItem(newId, bill, period, billValue);

    }
    private static synchronized int generateUniqueId() {
        return idCounter++;
    }

    public void save() {
        if (id == 0) {
            id = new Random().nextInt(1000) + 1;
            billItems.add(this);
        } else {
        }
    }

    public static BillItem GetById(int id) {
        return new BillItem(id,"","Choose the duration",0);
    }

    public void addBill() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (id == 0) {
                        WebRequest req = new WebRequest(new URL(
                                WebRequest.LOCALHOST + "/api/bill/add"));
                        String response = req.performPostRequest(BillItem.this);
                    }
                } catch (Exception e) {
                    Toast.makeText(null, "Web request failed: " + e.toString(),
                            Toast.LENGTH_LONG).show();
                    Log.e("BillItems", e.toString());
                }
            }
        });
        thread.start();
    }

    public static void List(BillItem.ListResponse response) {
        ArrayList<BillItem> items = new ArrayList<BillItem>();

        // Fetch a list of items from the web server and populate the list with them.

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    try {
                        WebRequest req = new WebRequest(new URL(
                                WebRequest.LOCALHOST + "/api/BudgetBuddy/bills"));
                        String resp = req.performGetRequest();

                        // Get the array from the response.
                        JsonArray arr = new Gson().fromJson(resp, JsonArray.class);
                        ArrayList<BillItem> items = new ArrayList<BillItem>();
                        for (JsonElement elem : arr) {
                            items.add(new Gson().fromJson(elem, BillItem.class));
                        }

                        response.response(items);
                    } catch (Exception e) {
                        Toast.makeText(null, "Web request failed: " + e.toString(),
                                Toast.LENGTH_LONG).show();
                        Log.e("BillItem", e.toString());
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

    public interface ListResponse {
        public void response(ArrayList<BillItem> items);
    }

    public interface GetByIdResponse {
        public void response(BillItem item);
    }
}

