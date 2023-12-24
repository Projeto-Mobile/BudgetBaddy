package pt.iade.abhaykumarjosefranco.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.CommunityItem;

public class create_community extends AppCompatActivity {

    protected TextView username1;
    protected TextView value1;
    protected TextView username2;
    protected TextView value2;
    protected TextView username3;
    protected TextView value3;
    protected TextView username4;
    protected TextView value4;
    protected TextView username5;
    protected TextView value5;
    protected TextView type;

    protected CommunityItem item;
    protected int listPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_community);

        setupComponents();
    }

    private void setupComponents(){

        username1 = (TextView) findViewById(R.id.user1);
        notesEdit = (TextView) findViewById(R.id.user2);
        titleEdit = (TextView) findViewById(R.id.title);
        notesEdit = (TextView) findViewById(R.id.notebody);
        titleEdit = (TextView) findViewById(R.id.title);
        notesEdit = (TextView) findViewById(R.id.notebody);
        titleEdit = (TextView) findViewById(R.id.title);
        notesEdit = (TextView) findViewById(R.id.notebody);
        titleEdit = (TextView) findViewById(R.id.title);
        notesEdit = (TextView) findViewById(R.id.notebody);
        modifiedDate = (TextView) findViewById(R.id.date);

        populateView();
    }

    protected void populateView(){
        titleEdit.setText(item.getTitle());
        notesEdit.setText(item.getBody());
    }
    protected void commitView(){
        item.setTitle(titleEdit.getText().toString());
        item.setBody(notesEdit.getText().toString());
    }


}