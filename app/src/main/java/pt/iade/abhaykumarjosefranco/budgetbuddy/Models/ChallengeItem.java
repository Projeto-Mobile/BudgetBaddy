package pt.iade.abhaykumarjosefranco.budgetbuddy.Models;

import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Utilities.WebRequest;

public class ChallengeItem implements Serializable {
    private static int idCounter = 0;
    private int id;
    private String challenge;
    private String period;

    // TODO: REMOVE THIS FOR WEB SERVER IMPLEMENTATION.
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
    }*/

    public static ArrayList<ChallengeItem> List() {
        return challengeItems;
    }

    public static ChallengeItem createNewChallengeItem(String challenge, String period) {
        int newId = generateUniqueId();
        return new ChallengeItem(newId, challenge, period);

    }
    private static synchronized int generateUniqueId() {
        return idCounter++;
    }

    public void save() {
        // TODO: Send the object's data to our web server and update the database there.
        if (id == 0) {
            id = new Random().nextInt(1000) + 1;
            challengeItems.add(this);
        } else {
        }
    }

    public static ChallengeItem GetById(int id) {
        // TODO: Fetch the item from the web server using its id and populate the object.

        return new ChallengeItem(id,"","");
    }

    public void addChallenge() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (id == 0) {
                        WebRequest req = new WebRequest(new URL(
                                WebRequest.LOCALHOST + "/api/challenge/add"));
                        String response = req.performPostRequest(ChallengeItem.this);
                    }
                } catch (Exception e) {
                    Toast.makeText(null, "Web request failed: " + e.toString(),
                            Toast.LENGTH_LONG).show();
                    Log.e("ChallengeItem", e.toString());
                }
            }
        });
        thread.start();
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

    // TODO: REMOVE THIS FOR WEB SERVER IMPLEMENTATION.
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

