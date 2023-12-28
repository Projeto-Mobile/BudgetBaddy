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
import java.util.ArrayList;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.BudgetItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.CommunityItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.SpendingItem;

public class Spendings extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private EditText name_s;
    private EditText value_s,date;
    protected ArrayList<SpendingItem> itemsList;

    protected SpendingItem item;
    private Button done_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spendings);

        itemsList = SpendingItem.List();

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
                commitView();
            }
        });

        setupComponents();
    }

    private void setupComponents(){

        name_s = (EditText) findViewById(R.id.expensename);
        value_s = (EditText) findViewById(R.id.expensevalue);
        date = (EditText) findViewById(R.id.date_spent);

        populateView();
    }

    protected void populateView(){
        name_s.setText(item.getSpentcategory());
        value_s.setText(item.getPeriod());
        date.setText(item.getDate().toString());

    }
    protected void commitView(){

        Intent intent = new Intent(Spendings.this, Viewspendings.class);
        SpendingItem item = new SpendingItem(-1, "",0, LocalDate.now());

        item.setSpentcategory(name_s.getText().toString());
        item.setPeriod(Integer.parseInt(value_s.getText().toString()));
        item.setDate(LocalDate.now());

        SpendingItem.spendingItems.add(item);

        item.save();
        startActivity(intent);
    }


}