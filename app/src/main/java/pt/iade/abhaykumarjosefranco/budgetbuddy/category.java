package pt.iade.abhaykumarjosefranco.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.BudgetItem;


public class Category extends AppCompatActivity {

    protected BudgetItem item;
    private BottomNavigationView bottomNavigationView;
    private Spinner periodSpinner;
    private EditText budgetEditText;
    protected int listPosition;
    private Button dinningout_button, travel_button, subscription_button, shopping_button, leisure_button, personalcare_button, specialoccassions_button, transportation_button, workexpenses_button, others_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        item = new BudgetItem(1, "", "", 0);

        // Get the item passed from the previous activity.
        Intent intent = getIntent();
        listPosition = intent.getIntExtra("position", -1);
        item = (BudgetItem) intent.getSerializableExtra("item");

        periodSpinner = findViewById(R.id.spinner);
        budgetEditText = findViewById(R.id.budget_cate_num2);


        dinningout_button = findViewById(R.id.dinningout_button);
        dinningout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBudget(dinningout_button, 1);
            }
        });

        travel_button = findViewById(R.id.travel_button);
        travel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBudget(travel_button, 2);
            }
        });

        subscription_button = findViewById(R.id.subscription_button);
        subscription_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBudget(subscription_button, 3);
            }
        });

        shopping_button = findViewById(R.id.shopping_button);
        shopping_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBudget(shopping_button, 4);
            }
        });

        leisure_button = findViewById(R.id.leisure_button);
        leisure_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBudget(leisure_button, 5);
            }
        });

        personalcare_button = findViewById(R.id.personalcare_button);
        personalcare_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBudget(personalcare_button, 6);
            }
        });

        specialoccassions_button = findViewById(R.id.specialoccassions_button);
        specialoccassions_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBudget(specialoccassions_button, 7);
            }
        });

        transportation_button = findViewById(R.id.transportation_button);
        transportation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBudget(transportation_button, 8);
            }
        });

        workexpenses_button = findViewById(R.id.workexpenses_button);
        workexpenses_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBudget(workexpenses_button, 9);
            }
        });

        others_button = findViewById(R.id.others_button);
        others_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBudget(others_button, 10);
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

    /**
     * Updates the contents of the components in the activity according to the object associated
     * with this class.
     */
    protected void populateView() {
        budgetEditText.setText(item.getBudgetValue());

        String[] periods = getResources().getStringArray(R.array.periods);
        for (int i = 0; i < periods.length; i++) {
            if (periods[i].equals(item.getPeriod()))
                periodSpinner.setSelection(i);
        }
    }

    /**
     * Updates the data in the associated object with the information from the UI components.
     */
    protected void setBudget(Button button, int idType) {


        item.setBudgetValue(Integer.parseInt(budgetEditText.getText().toString()));
        item.setCategory(button.getText().toString());
        item.setPeriod(periodSpinner.getSelectedItem().toString());

        populateView();

        // TODO: set type id of item.
    }


}


