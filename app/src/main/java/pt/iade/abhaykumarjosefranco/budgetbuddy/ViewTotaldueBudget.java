package pt.iade.abhaykumarjosefranco.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ViewTotaldueBudget extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_totaldue_budget);

        Intent intent = getIntent();
        String categoryName = intent.getStringExtra("CATEGORY_NAME");
        String selectedPeriod = intent.getStringExtra("SELECTED_PERIOD");
        String budgetValue = intent.getStringExtra("BUDGET_VALUE");

        // Log the values for debugging
        Log.d("ViewTotaldueBudget", "Category Name: " + categoryName);
        Log.d("ViewTotaldueBudget", "Selected Period: " + selectedPeriod);
        Log.d("ViewTotaldueBudget", "Budget Value: " + budgetValue);

        // Now you can use the retrieved values as needed in your ViewTotaldueBudget activity
    }
}
