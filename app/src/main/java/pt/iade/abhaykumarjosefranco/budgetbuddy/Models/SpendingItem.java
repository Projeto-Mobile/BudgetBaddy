package pt.iade.abhaykumarjosefranco.budgetbuddy.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

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
}
