package pt.iade.abhaykumarjosefranco.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.BudgetItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.ChallengeItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.CommunityItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.SpendingItem;

public class Spendings extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Spinner name_s;
    private EditText value_s,date;
    protected ArrayList<SpendingItem> itemsList;

    protected SpendingItem item;
    protected int listPosition;
    private Button done_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spendings);

        //itemsList = SpendingItem.List();

        Intent intent = getIntent();
        listPosition = intent.getIntExtra("position",-1);
        //item = (SpendingItem) intent.getSerializableExtra("item");

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.add);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                startActivity(new Intent(getApplicationContext(), OverviewActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.wallet) {
                startActivity(new Intent(getApplicationContext(), WalletActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.add) {
                return true;
            } else if (item.getItemId() == R.id.profile) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
                finish();
                return true;
            }
            return false;
        });


        Button done_Button = findViewById(R.id.done_spending_button);

        done_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Spendings.this, Viewspendings.class);
                startActivity(intent);

                commitView();
            }
        });

        setupComponents();
    }

    private void setupComponents(){

        name_s = (Spinner) findViewById(R.id.spending_spinner);
        value_s = (EditText) findViewById(R.id.expensevalue);
        date = (EditText) findViewById(R.id.date_spent);

        populateView();
    }

    protected void populateView(){

        item.setSpentcategory(name_s.getSelectedItem().toString());
        value_s.setText(String.valueOf(item.getPeriod()));
        date.setText(item.getDate().toString());

    }


    protected void commitView(){
        item = new SpendingItem(-1, "",0, LocalDate.now());

        item.setSpentcategory(name_s.getSelectedItem().toString());
        item.setPeriod(Integer.parseInt(value_s.getText().toString()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        item.setDate(LocalDate.parse(date.getText().toString(), formatter));

        Intent intent = new Intent(Spendings.this, Viewspendings.class);

        item.addSpending(new SpendingItem.SaveResponse() {
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