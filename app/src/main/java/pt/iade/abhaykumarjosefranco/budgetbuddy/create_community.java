package pt.iade.abhaykumarjosefranco.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.CommunityItem;

public class create_community extends AppCompatActivity {

    protected EditText username1;
    protected EditText value1;
    protected EditText username2;
    protected EditText value2;
    protected EditText username3;
    protected EditText value3;
    protected EditText username4;
    protected EditText value4;
    protected EditText username5;
    protected EditText value5;

    protected CommunityItem item;
    protected int listPosition;

    protected Button button;
    private Spinner optionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_community);

        optionSpinner = findViewById(R.id.commiunity_spinner);


        username1 = (EditText) findViewById(R.id.use1);
        username2 = (EditText) findViewById(R.id.use2);
        username3 = (EditText) findViewById(R.id.use3);
        username4 = (EditText) findViewById(R.id.use4);
        username5 = (EditText) findViewById(R.id.use5);
        value1 = (EditText) findViewById(R.id.amoun1);
        value2 = (EditText) findViewById(R.id.amoun2);
        value3 = (EditText) findViewById(R.id.amoun3);
        value4 = (EditText) findViewById(R.id.amoun4);
        value5 = (EditText) findViewById(R.id.amoun5);

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



    protected void populateView(){

        item.setName(optionSpinner.getSelectedItem().toString());


        //name.setText(item.getName());

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

        commitView();

    }

    protected void commitView(){

        Intent intent = new Intent(create_community.this, Community.class);
        CommunityItem item = new CommunityItem(-1, "", "", 0, "", 0, "", 0, "", 0, "", 0);

        item.setName(optionSpinner.getSelectedItem().toString());

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


        item.save();
        startActivity(intent);
    }


}