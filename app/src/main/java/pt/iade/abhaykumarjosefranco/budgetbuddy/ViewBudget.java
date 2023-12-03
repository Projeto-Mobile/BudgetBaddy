package pt.iade.abhaykumarjosefranco.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ViewBudget extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_budget);

        Intent intent = getIntent();
        String categoryName = intent.getStringExtra("CATEGORY_NAME");
        String selectedPeriod = intent.getStringExtra("SELECTED_PERIOD");
        String budgetValue = intent.getStringExtra("BUDGET_VALUE");

        // Log the values for debugging
        Log.d("ViewBudget", "Category Name: " + categoryName);
        Log.d("ViewBudget", "Selected Period: " + selectedPeriod);
        Log.d("ViewBudget", "Budget Value: " + budgetValue);

        // Now you can use the retrieved values as needed in your ViewBudget activity
    }
}
