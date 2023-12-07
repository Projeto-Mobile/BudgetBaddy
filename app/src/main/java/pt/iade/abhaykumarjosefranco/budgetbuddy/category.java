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
import pt.iade.abhaykumarjosefranco.budgetbuddy.adapters.BudgetItemRowAdapter;


// make row_list as templates for challenges, category, bills.


public class Category extends AppCompatActivity {

    protected BudgetItem item;
    private BottomNavigationView bottomNavigationView;
    private Spinner periodSpinner;
    private EditText budgetEditText;
    protected int listPosition;
    private Button dinningout_button,travel_button,subscription_button,shopping_button,leisure_button,personalcare_button,specialoccassions_button,transportation_button,workexpenses_button,others_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        item = new BudgetItem(1,"" ,"" ,0);

        // Get the item passed from the previous activity.
        Intent intent = getIntent();
        listPosition = intent.getIntExtra("position", -1);
        item = (BudgetItem) intent.getSerializableExtra("item");






        dinningout_button = findViewById(R.id.dinningout_button);
        dinningout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBudgetCategories("Dining Out");
            }
        });

        travel_button = findViewById(R.id.travel_button);
        travel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openBudgetCategories("Travel");
            }
        });

        subscription_button = findViewById(R.id.subscription_button);
        subscription_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openBudgetCategories("Subscription");
            }
        });

        shopping_button = findViewById(R.id.shopping_button);
        shopping_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openBudgetCategories("Shopping");
            }
        });

        leisure_button = findViewById(R.id.leisure_button);
        leisure_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openBudgetCategories("Leisure");
            }
        });

        personalcare_button = findViewById(R.id.personalcare_button);
        personalcare_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openBudgetCategories("Personal Care");
            }
        });

        specialoccassions_button=findViewById(R.id.specialoccassions_button);
        specialoccassions_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openBudgetCategories("Special Occasions");
            }
        });

        transportation_button = findViewById(R.id.transportation_button);
        transportation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openBudgetCategories("Transportation");
            }
        });

        workexpenses_button = findViewById(R.id.workexpenses_button);
        workexpenses_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openBudgetCategories("Work Expenses");
            }
        });

        others_button = findViewById(R.id.others_button);
        others_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openBudgetCategories("Others");
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

        protected void onCreate(Bundle savedInstanceState) { //TODO: ASK IF THIS IS IMPORTANT
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_category);

            // Get the item passed from the previous activity.
            Intent intent1 = getIntent();
            listPosition = intent1.getIntExtra("position", -1);
            item = (BudgetItem) intent1.getSerializableExtra("item");

            setupComponents();
        }

        private void setupComponents() { //TODO: make category a parameter.

            // Get components into properties.
            periodSpinner = findViewById(R.id.spinner);
            budgetEditText = findViewById(R.id.budget_cate_num2);

            // Populate the widgets with data from our object.
            populateView();
        }

        /**
         * Updates the contents of the components in the activity according to the object associated
         * with this class.
         */
        protected void populateView() {
            budgetEditText.setText(item.getBudgetValue());
            periodSpinner.setText(item.getNotes());
        }

        /**
         * Updates the data in the associated object with the information from the UI components.
         */
        protected void commitView() {
            item.setBudgetValue(budgetEditText.getText().toString());
            item.setTitle(titleEdit.getText().toString());
            item.setNotes(notesEdit.getText().toString());
        }

        /**
         * Event handler when menu items are selected.
         *
         * @param item Menu item that was selected.
         *
         * @return True if we handled the event, False if Android should take care of it.
         */
        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == R.id.action_save_item) {
                // ActionBar "Save" button.
                commitView();
                this.item.save();

                // Setup the data to be sent back to the previous activity.
                Intent returnIntent = new Intent();
                returnIntent.putExtra("position", this.listPosition);
                returnIntent.putExtra("item", this.item);
                setResult(AppCompatActivity.RESULT_OK, returnIntent);

                finish();
                return true;
            }

            return super.onOptionsItemSelected(item);
        }


        private void openBudgetCategories(String category) {
            // Get the selected period and budget value
            String selectedPeriod = periodSpinner.getSelectedItem().toString();
            String budgetValue = budgetEditText.getText().toString();

            // Pass the data to ViewBudget
            Intent intent = new Intent(Category.this, ViewBudget.class);
            intent.putExtra("CATEGORY_NAME", category);
            intent.putExtra("SELECTED_PERIOD", selectedPeriod);
            intent.putExtra("BUDGET_VALUE", budgetValue);
            startActivity(intent);
        }

    }

}
