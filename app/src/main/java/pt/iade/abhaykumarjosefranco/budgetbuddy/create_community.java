package pt.iade.abhaykumarjosefranco.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
    protected Spinner type;

    protected CommunityItem item;
    protected int listPosition;

    protected Button button;

    private Spinner optionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_community);

        setupComponents();

        type = (Spinner) findViewById(R.id.commiunity_spinner);
        button = (Button) findViewById(R.id.community_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(create_community.this, Community.class);
                startActivity(intent);
                populateView();
            }
        });
    }

    private void setupComponents(){

        username1 = (TextView) findViewById(R.id.use1);
        username2 = (TextView) findViewById(R.id.use2);
        username3 = (TextView) findViewById(R.id.use3);
        username4 = (TextView) findViewById(R.id.use4);
        username5 = (TextView) findViewById(R.id.use5);
        value1 = (TextView) findViewById(R.id.amoun1);
        value2 = (TextView) findViewById(R.id.amoun2);
        value3 = (TextView) findViewById(R.id.amoun3);
        value4 = (TextView) findViewById(R.id.amoun4);
        value5 = (TextView) findViewById(R.id.amoun5);

    }


    protected void populateView(){
        username1.setText(item.getUser1());
        username2.setText(item.getUser2());
        username3.setText(item.getUser3());
        username4.setText(item.getUser4());
        username5.setText(item.getUser5());
        value1.setText(item.getAmount1());
        value2.setText(item.getAmount2());
        value3.setText(item.getAmount3());
        value4.setText(item.getAmount4());
        value5.setText(item.getAmount5());

        item.setName(optionSpinner.getSelectedItem().toString());


        commitView();
    }



    protected void commitView(){
        item.setUser1(username1.getText().toString());
        item.setUser2(username2.getText().toString());
        item.setUser3(username3.getText().toString());
        item.setUser4(username4.getText().toString());
        item.setUser5(username5.getText().toString());
        item.setAmount1(Integer.parseInt(value1.getText().toString()));
        item.setAmount2(Integer.parseInt(value2.getText().toString()));
        item.setAmount3(Integer.parseInt(value3.getText().toString()));
        item.setAmount4(Integer.parseInt(value4.getText().toString()));
        item.setAmount5(Integer.parseInt(value5.getText().toString()));

        item.setName(optionSpinner.getSelectedItem().toString());
    }


}