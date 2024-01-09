package pt.iade.abhaykumarjosefranco.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.UserItem;

public class About extends AppCompatActivity {

    private UserItem user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        Intent intent = getIntent();
        //item = (ChallengeItem) intent.getSerializableExtra("item");
        user = (UserItem) intent.getSerializableExtra("user") ;
    }
}