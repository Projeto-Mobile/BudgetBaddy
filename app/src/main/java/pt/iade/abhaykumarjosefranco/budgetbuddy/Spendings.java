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
import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.SpendingItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.UserItem;

public class Spendings extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Spinner name_s;
    private EditText value_s,date;
    protected ArrayList<SpendingItem> itemsList;
    protected SpendingItem item;
    private UserItem user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spendings);

        //itemsList = SpendingItem.List();

        Intent intent = getIntent();
        user = (UserItem) intent.getSerializableExtra("user") ;

        setupComponents();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                startActivity(new Intent(getApplicationContext(), OverviewActivity.class).putExtra("user", user));
                finish();
                return true;
            } else if (item.getItemId() == R.id.wallet) {
                startActivity(new Intent(getApplicationContext(), WalletActivity.class).putExtra("user", user));
                finish();
                return true;
            } else if (item.getItemId() == R.id.profile) {
                startActivity(new Intent(getApplicationContext(), Profile.class).putExtra("user", user));
                finish();
                return true;
            }
            return false;
        });


        Button done_Button = findViewById(R.id.done_spending_button);

        done_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                item = new SpendingItem();
                commitView();

                item.add(Spendings.this, new SpendingItem.SaveResponse() {
                    @Override
                    public void response() {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("item", item);
                        returnIntent.putExtra("user", user);
                        setResult(AppCompatActivity.RESULT_OK, returnIntent);

                        finish();
                    }
                });

            }

        });
    }

    private void setupComponents(){

        name_s = (Spinner) findViewById(R.id.spending_spinner);
        value_s = (EditText) findViewById(R.id.expensevalue);
        date = (EditText) findViewById(R.id.date_spent);

    }



    protected void commitView(){

        item.setName(name_s.getSelectedItem().toString());
        item.setSpendValue(Integer.parseInt(value_s.getText().toString()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        item.setDate(LocalDate.parse(date.getText().toString(), formatter));
        item.setUser(user);

    }


}