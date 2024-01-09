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
    protected EditText value1;

    protected CommunityItem item;
    protected int listPosition;

    protected Button button;
    private Spinner optionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_community);


        Intent intent = getIntent();
        listPosition = intent.getIntExtra("position",-1);
        //item = (CommunityItem) intent.getSerializableExtra("item");

        optionSpinner = findViewById(R.id.commiunity_spinner);

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
        value1.setText(item.getAmount());

        commitView();

    }

    protected void commitView() {

        item = new CommunityItem(-1, "",0);
        item.setName(optionSpinner.getSelectedItem().toString());
        item.setAmount(Integer.parseInt(value1.getText().toString()));


        Intent intent = new Intent(create_community.this, Community.class);
        item.addCommunity(new CommunityItem.SaveResponse() {
            @Override
            public void response() {
                populateView();
                intent.putExtra("item", item);
                intent.putExtra("position", listPosition);
                setResult(AppCompatActivity.RESULT_OK, intent);
                startActivity(intent);

            }
        });
    }


}