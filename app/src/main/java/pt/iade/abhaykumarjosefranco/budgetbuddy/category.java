package pt.iade.abhaykumarjosefranco.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import pt.iade.abhaykumarjosefranco.budgetbuddy.R;

public class Category extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private Button dinningout_button,travel_button,subscription_button,shopping_button,leisure_button,personalcare_button,specialoccassions_button,transportation_button,workexpenses_button,others_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        dinningout_button = findViewById(R.id.dinningout_button);

        dinningout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Category.this, budgetCategories.class);
                startActivity(intent);
            }
        });

        travel_button = findViewById(R.id.travel_button);

        travel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Category.this, budgetCategories.class);
                startActivity(intent);
            }
        });

        subscription_button = findViewById(R.id.subscription_button);

        subscription_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Category.this, budgetCategories.class);
                startActivity(intent);
            }
        });

        shopping_button = findViewById(R.id.shopping_button);

        shopping_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Category.this, budgetCategories.class);
                startActivity(intent);
            }
        });

        leisure_button = findViewById(R.id.leisure_button);

        leisure_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Category.this, budgetCategories.class);
                startActivity(intent);
            }
        });

        personalcare_button = findViewById(R.id.personalcare_button);

        personalcare_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Category.this, budgetCategories.class);
                startActivity(intent);
            }
        });

        specialoccassions_button = findViewById(R.id.specialoccassions_button);

        specialoccassions_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Category.this, budgetCategories.class);
                startActivity(intent);
            }
        });

        transportation_button = findViewById(R.id.transportation_button);

        transportation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Category.this, budgetCategories.class);
                startActivity(intent);
            }
        });

        workexpenses_button = findViewById(R.id.workexpenses_button);

        workexpenses_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Category.this, budgetCategories.class);
                startActivity(intent);
            }
        });

        others_button = findViewById(R.id.others_button);

        others_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Category.this, budgetCategories.class);
                startActivity(intent);
            }
        });

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


    }
}
