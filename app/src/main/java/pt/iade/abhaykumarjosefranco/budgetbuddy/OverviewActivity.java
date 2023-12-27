package pt.iade.abhaykumarjosefranco.budgetbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;



import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OverviewActivity extends AppCompatActivity {

    private Button totalexpense_button, totaldue_button, totalsaving_button,budget_button, bill_button,spending_button,create_button;
    private BottomNavigationView bottomNavigationView;

    //TODO : show the name of  highest time to the lowest for the challenges , average time for the challenges . and also show the value for each challenge.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);


        totalexpense_button = findViewById(R.id.totalexpense_button);
        totalexpense_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OverviewActivity.this, ViewBudget.class);
                startActivity(intent);
            }
        });

        totaldue_button = findViewById(R.id.total_due_button);

        totaldue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OverviewActivity.this, TotalDue.class);
                startActivity(intent);
            }
        });

        totalsaving_button = findViewById(R.id.total_saving_button);
        totalsaving_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OverviewActivity.this, ChallengesActivity.class);
                startActivity(intent);
            }
        });


        budget_button = findViewById(R.id.budgethistory_button);
        budget_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OverviewActivity.this, BudgetHistory.class);
                startActivity(intent);
            }
        });


        bill_button = findViewById(R.id.billhistory_button);
        bill_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OverviewActivity.this, BillHistory.class);
                startActivity(intent);
            }
        });

        spending_button = findViewById(R.id.spending_button);
        spending_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OverviewActivity.this, Viewspendings.class);
                startActivity(intent);
            }
        });

        create_button = findViewById(R.id.create_spending_button);
        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OverviewActivity.this, Spendings.class);
                startActivity(intent);
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                return true;
            } else if (item.getItemId() == R.id.wallet) {
                startActivity(new Intent(getApplicationContext(), WalletActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.add) {
                startActivity(new Intent(getApplicationContext(), Category.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.profile) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
                finish();
                return true;
            }
            return false;
        });
    }
}
