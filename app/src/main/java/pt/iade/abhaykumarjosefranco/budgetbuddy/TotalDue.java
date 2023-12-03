package pt.iade.abhaykumarjosefranco.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TotalDue extends AppCompatActivity {

    private Spinner periodSpinner;
    private EditText budgetEditText;

    private Button waterbill, electricity, health, education, childcare, mortgage, insurance, loan, taxes, others;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_due);

        periodSpinner = findViewById(R.id.spinner);
        budgetEditText = findViewById(R.id.budget_cate_num);

        waterbill = findViewById(R.id.waterbill);
        waterbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBudgetCategories("Water Bill");
            }
        });

        electricity = findViewById(R.id.electricity);
        electricity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBudgetCategories("Electricity");
            }
        });

        health = findViewById(R.id.health);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBudgetCategories("Health");
            }
        });

        education = findViewById(R.id.education);
        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBudgetCategories("Education");
            }
        });

        childcare = findViewById(R.id.childcare);
        childcare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBudgetCategories("Child Care");
            }
        });

        mortgage = findViewById(R.id.mortgage);
        mortgage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBudgetCategories("Mortgage");
            }
        });

        insurance = findViewById(R.id.insurance);
        insurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBudgetCategories("Insurance");
            }
        });

        loan = findViewById(R.id.loan);
        loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBudgetCategories("Loan");
            }
        });

        taxes = findViewById(R.id.taxes);
        taxes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBudgetCategories("Taxes");
            }
        });

        others = findViewById(R.id.others);
        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBudgetCategories("Others");
            }
        });
    }

    private void openBudgetCategories(String category) {
        // Get the selected period and budget value
        String selectedPeriod = periodSpinner.getSelectedItem().toString();
        String budgetValue = budgetEditText.getText().toString();

        // Pass data to the next activity
        Intent intent = new Intent(TotalDue.this, ViewTotaldueBudget.class);
        intent.putExtra("CATEGORY_NAME", category);
        intent.putExtra("SELECTED_PERIOD", selectedPeriod);
        intent.putExtra("BUDGET_VALUE", budgetValue);
        startActivity(intent);
    }
}
