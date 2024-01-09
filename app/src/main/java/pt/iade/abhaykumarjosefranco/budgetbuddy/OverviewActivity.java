package pt.iade.abhaykumarjosefranco.budgetbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;



import com.google.android.material.bottomnavigation.BottomNavigationView;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.SpendingItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.UserItem;

public class OverviewActivity extends AppCompatActivity {

    private static final int EDITOR_ACTIVITY_RETURN_ID = 1;
    private Button totalexpense_button, totaldue_button, totalsaving_button,spending_button,create_button;
    private BottomNavigationView bottomNavigationView;

    UserItem user;

    //TODO : show the name of  highest time to the lowest for the challenges , average time for the challenges . and also show the value for each challenge.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        Intent intent = getIntent();
        user = (UserItem) intent.getSerializableExtra("user");


        totalexpense_button = findViewById(R.id.totalexpense_button);
        totalexpense_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OverviewActivity.this, ViewBudget.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        totaldue_button = findViewById(R.id.total_due_button);

        totaldue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OverviewActivity.this, TotalDue.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        totalsaving_button = findViewById(R.id.total_saving_button);
        totalsaving_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OverviewActivity.this, ChallengesActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });


        spending_button = findViewById(R.id.spending_button);
        spending_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OverviewActivity.this, Viewspendings.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        create_button = findViewById(R.id.create_spending_button);
        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO: Get the last spending from server and do this:
                Intent intent = new Intent(OverviewActivity.this, Spendings.class);
                intent.putExtra("item", new SpendingItem());
                intent.putExtra("user", user);
                startActivityForResult(intent, EDITOR_ACTIVITY_RETURN_ID);
                startActivity(intent);
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
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
    }
}
