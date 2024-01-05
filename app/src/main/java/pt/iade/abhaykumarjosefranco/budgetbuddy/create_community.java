package pt.iade.abhaykumarjosefranco.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.BudgetItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.CommunityItem;

public class create_community extends AppCompatActivity {
    protected EditText value1;

    protected CommunityItem item;
    protected int listPosition;

    protected Button button;
    private Spinner optionSpinner;

    private EditText numberedittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_community);


        Intent intent = getIntent();
        listPosition = intent.getIntExtra("position",-1);
        item = (CommunityItem) intent.getSerializableExtra("item");

        optionSpinner = findViewById(R.id.commiunity_spinner);
        numberedittext = findViewById(R.id.random_number);

        value1 = (EditText) findViewById(R.id.valueforthecommunity);

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

        value1.setText(item.getAmount());

        commitView();

    }

    protected void commitView() {

        Intent intent = new Intent(create_community.this, Community.class);
        CommunityItem item = new CommunityItem(-1, "",0);

        item.setName(optionSpinner.getSelectedItem().toString());

        item.setAmount(Integer.parseInt(value1.getText().toString()));


        item.addCommunity(new CommunityItem.SaveResponse() {
            @Override
            public void response() {
                populateView();
                startActivity(intent);
            }
        });
    }


}