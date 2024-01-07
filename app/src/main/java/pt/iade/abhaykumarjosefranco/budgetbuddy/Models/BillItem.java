package pt.iade.abhaykumarjosefranco.budgetbuddy.Models;

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

public class BillItem implements Serializable {
    private static int idCounter = 0;
    private int id;
    private String bill;
    private String typeofexpense;
    @JsonAdapter(DateJsonAdapter.class)
    private LocalDate datestart;
    private LocalDate dateend;
    @JsonAdapter(DateJsonAdapter.class)
    private int billValue;

    //public static ArrayList<BillItem> billItems;

    //TODO : Associate user ID, budget ID, type budget ID

    public BillItem() {
        this(0, "", "",  0, LocalDate.now(), LocalDate.now());
    }

    public BillItem(int id, String bill, String typeofexpense, int billValue,  LocalDate date1, LocalDate date2) {
        this.id = id;
        this.bill = bill;
        this.datestart = date1;
        this.dateend = date2;
        this.typeofexpense = typeofexpense;
        this.billValue = billValue;

        /*if (billItems == null) {
            billItems = new ArrayList<BillItem>();
        }*/
    }



    /*public static ArrayList<BillItem> List() {
        return billItems;
    }

    public static BillItem createNewBillItem(String bill,String typeofexpense, int billValue) {
        int newId = generateUniqueId();
        return new BillItem(newId, bill,typeofexpense, billValue);

    }
    private static synchronized int generateUniqueId() {
        return idCounter++;
    }

    /*
    public void save() {
        if (id == 0) {
            id = new Random().nextInt(1000) + 1;
            billItems.add(this);
        } else {
        }
    }

    public void save(SaveResponse response) {
        // Send the object's data to our web server and update the database there.
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (id == 0) {
                        // This is a brand new object and must be a INSERT in the database.
                        WebRequest req = new WebRequest(new URL(WebRequest.LOCALHOST + "/api/billsave"));
                        String resp = req.performPostRequest(BillItem.this);

                        // Get the new ID from the server's response.
                        BillItem respItem = new Gson().fromJson(resp, BillItem.class);
                        id = respItem.getId();
                        response.response();
                    } else {
                        // This is an update to an existing object and must use UPDATE in the database.
                        WebRequest req = new WebRequest(new URL(WebRequest.LOCALHOST + "/api/billsave/" + id));
                        req.performPostRequest(BillItem.this);

                        response.response();
                    }
                } catch (Exception e) {
                    Log.e("BillItem", e.toString());
                }
            }
        });
        thread.start();
    }*/

    public static BillItem GetById(int id) {
        return new BillItem(id,"","",0,  LocalDate.now(), LocalDate.now());
    }

    public void addBill(BillItem.SaveResponse response) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (id == 0) {
                        WebRequest req = new WebRequest(new URL(
                                WebRequest.LOCALHOST + "/api/budgets/add"));
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
                                WebRequest.LOCALHOST + "/api/budgets/list"));
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

    public static void GetById(int id, GetByIdResponse response) {
        // Fetch the item from the web server using its id and populate the object.
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    try {
                        WebRequest req = new WebRequest(new URL(
                                WebRequest.LOCALHOST + "/api/budgets/" + id));
                        String resp = req.performGetRequest();

                        response.response(new Gson().fromJson(resp, BillItem.class));
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

    public String getType() {
        return typeofexpense;
    }

    public void setType(String typeofexpense) {
        this.typeofexpense = typeofexpense;
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

    public interface SaveResponse{
        public void response();
    }

}

