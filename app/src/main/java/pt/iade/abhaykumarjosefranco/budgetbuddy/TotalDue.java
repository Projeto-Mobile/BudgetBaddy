package pt.iade.abhaykumarjosefranco.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.BillItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.BudgetItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.ChallengeItem;

public class TotalDue extends AppCompatActivity {

    private Spinner periodSpinner;
    private EditText billEditText,typeofbill;
    private BottomNavigationView bottomNavigationView;
    protected ArrayList<BillItem> itemsList;

    protected BillItem item;
    protected int listPosition;
    private Button waterbill, electricity, health, education, childcare, mortgage, insurance, loan, taxes, others,button_bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_due);

        periodSpinner = findViewById(R.id.spinner);
        billEditText = findViewById(R.id.budget_cate_num);
        typeofbill = findViewById(R.id.description);

        //itemsList = BillItem.List();
        Intent intent = getIntent();
        listPosition = intent.getIntExtra("position",-1);
        item = (BillItem) intent.getSerializableExtra("item");
        //setBill();


        button_bill = findViewById(R.id.archeive_button);
        button_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TotalDue.this, ViewTotaldueBudget.class);
                startActivity(intent);
            }
        });

        waterbill = findViewById(R.id.waterbill);
        waterbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBill(waterbill,    1);
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


    protected void populateView() {

        /*budgetEditText.setText(item.getBillValue());

        String[] periods = getResources().getStringArray(R.array.periods);
        for (int i = 0; i < periods.length; i++) {
            if (periods[i].equals(item.getPeriod()))
                periodSpinner.setSelection(i);
        }*/
    }

    protected void setBill(Button button, int idType) {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TotalDue.this, ViewTotaldueBudget.class);


                BillItem item = new BillItem(-1, "", "", "", 0);
                item.setBillValue(Integer.parseInt(billEditText.getText().toString()));
                item.setBill(button.getText().toString());
                item.setType(billEditText.getText().toString());
                item.setPeriod(periodSpinner.getSelectedItem().toString());

                item.addBill(new BillItem.SaveResponse() {
                    @Override
                    public void response() {
                        populateView();
                        startActivity(intent);
                    }
                });

            }
        });

    }
}
