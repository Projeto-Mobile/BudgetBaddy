package pt.iade.abhaykumarjosefranco.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.BillItem;

public class TotalDue extends AppCompatActivity {

    private Spinner periodSpinner;
    private EditText budgetEditText;

    protected BillItem item;
    private BottomNavigationView bottomNavigationView;

    protected int listPosition;

    private Button waterbill, electricity, health, education, childcare, mortgage, insurance, loan, taxes, others;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_due);

        item = new BillItem(1, "", "", 0);

        // Get the item passed from the previous activity.
        Intent intent = getIntent();
        listPosition = intent.getIntExtra("position", -1);
        item = (BillItem) intent.getSerializableExtra("item");

        periodSpinner = findViewById(R.id.spinner);
        budgetEditText = findViewById(R.id.budget_cate_num);

        waterbill = findViewById(R.id.waterbill);
        waterbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBill(waterbill,1);
            }
        });

        electricity = findViewById(R.id.electricity);
        electricity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBill(electricity,2);
            }
        });

        health = findViewById(R.id.health);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBill(health,3);
            }
        });

        education = findViewById(R.id.education);
        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBill(education,4);
            }
        });

        childcare = findViewById(R.id.childcare);
        childcare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBill(childcare,5);
            }
        });

        mortgage = findViewById(R.id.mortgage);
        mortgage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBill(mortgage,6);
            }
        });

        insurance = findViewById(R.id.insurance);
        insurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBill(insurance,7);
            }
        });

        loan = findViewById(R.id.loan);
        loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBill(loan, 8);
            }
        });

        taxes = findViewById(R.id.taxes);
        taxes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBill(taxes, 9);
            }
        });

        others = findViewById(R.id.others);
        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBill(others, 10);
            }
        });
    }

    /**
     * Updates the contents of the components in the activity according to the object associated
     * with this class.
     */
    protected void populateView() {
        budgetEditText.setText(item.getBillValue());

        String[] periods = getResources().getStringArray(R.array.periods);
        for (int i = 0; i < periods.length; i++) {
            if (periods[i].equals(item.getPeriod()))
                periodSpinner.setSelection(i);
        }
    }

    /**
     * Updates the data in the associated object with the information from the UI components.
     */
    protected void setBill(Button button, int idType) {
        item.setBillValue(Integer.parseInt(budgetEditText.getText().toString()));
        item.setBill(button.getText().toString());
        item.setPeriod(periodSpinner.getSelectedItem().toString());

        populateView();

        // TODO: set type id of item.
    }


}
