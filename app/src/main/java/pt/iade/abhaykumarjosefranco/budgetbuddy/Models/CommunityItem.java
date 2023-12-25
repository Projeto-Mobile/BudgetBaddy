package pt.iade.abhaykumarjosefranco.budgetbuddy.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;


public class CommunityItem implements Serializable {

    private int id;
    private String user5;
    private int amount5;
    private String user1;
    private int amount1;
    private String user2;
    private int amount2;
    private String user3;
    private int amount3;
    private String user4;
    private int amount4;

    private String name;

    //public CommunityItem(){
       // this(0, "", "", 0,"",0,"",0,"",0,"",0);
   // }
    public CommunityItem(int id, String name, String user1, int amount1,String user2, int amount2,String user3, int amount3,String user4, int amount4,String user5, int amount5){
        this.id = id;
        this.name = name;
        this.user1 = user1;
        this.amount1 = amount1;
        this.user2 = user2;
        this.amount2 = amount2;
        this.user3 = user3;
        this.amount3 = amount3;
        this.user4 = user4;
        this.amount4 = amount4;
        this.user5 = user5;
        this.amount5 = amount5;
    }
    public static ArrayList<CommunityItem> List(){
        ArrayList<CommunityItem> items = new ArrayList<CommunityItem>();

        return items;
    }

    public static CommunityItem GetById(int id){
        return new CommunityItem(id,"name", "user1",0,"user2",0,"user3",0,"user4",0,"user5",0);
    }
    public void save(){
        if (id == 0){
            id = new Random().nextInt(1000) + 1;
        }else{

        }
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

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public int getAmount1() {
        return amount1;
    }

    public void setAmount1(int amount1) {
        this.amount1 = amount1;
    }


    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public int getAmount2() {
        return amount2;
    }

    public void setAmount2(int amount2) {
        this.amount2 = amount2;
    }


    public String getUser3() {
        return user3;
    }

    public void setUser3(String user3) {
        this.user3 = user3;
    }

    public int getAmount3() {
        return amount3;
    }

    public void setAmount3(int amount3) {
        this.amount3 = amount3;
    }

    public String getUser4() {
        return user4;
    }

    public void setUser4(String user4) {
        this.user4 = user4;
    }

    public int getAmount4() {
        return amount4;
    }

    public void setAmount4(int amount4) {
        this.amount4 = amount4;
    }

    public String getUser5() {
        return user5;
    }

    public void setUser5(String user5) {
        this.user5 = user5;
    }

    public int getAmount5() {
        return amount5;
    }

    public void setAmount5(int amount5) {
        this.amount5 = amount5;
    }
}