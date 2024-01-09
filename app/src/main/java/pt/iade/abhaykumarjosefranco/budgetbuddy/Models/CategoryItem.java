package pt.iade.abhaykumarjosefranco.budgetbuddy.Models;


import java.io.Serializable;

public class CategoryItem implements Serializable {


    private int id;
    private String name;
    private int tipId;

    public CategoryItem(int id, String name, int tipId) {
        this.id = id;
        this.name = name;
        this.tipId = tipId;
    }

    public CategoryItem() {

    }
    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }
    public int getTipId() {
        return tipId;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setTipId(int tipId){
        this.tipId = tipId;
    }
}
