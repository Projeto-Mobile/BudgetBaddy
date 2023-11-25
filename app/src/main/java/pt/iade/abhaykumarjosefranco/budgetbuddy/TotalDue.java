package pt.iade.abhaykumarjosefranco.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TotalDue extends AppCompatActivity {

    private Button waterbill,electricity,health,education,childcare,mortgage,insurance,loan,taxes,others;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_due);


        waterbill = findViewById(R.id.waterbill);

        waterbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TotalDue.this, budgetTotalDue.class);
                startActivity(intent);
            }
        });

        electricity = findViewById(R.id.eletricity);

        electricity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TotalDue.this, budgetTotalDue.class);
                startActivity(intent);
            }
        });

        health = findViewById(R.id.health);

        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TotalDue.this, budgetTotalDue.class);
                startActivity(intent);
            }
        });


        education = findViewById(R.id.education);

        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TotalDue.this, budgetTotalDue.class);
                startActivity(intent);
            }
        });

        childcare = findViewById(R.id.childcare);

        childcare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TotalDue.this, budgetTotalDue.class);
                startActivity(intent);
            }
        });


        mortgage = findViewById(R.id.mortgage);

        mortgage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TotalDue.this, budgetTotalDue.class);
                startActivity(intent);
            }
        });

        insurance = findViewById(R.id.insurance);

        insurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TotalDue.this, budgetTotalDue.class);
                startActivity(intent);
            }
        });

        loan = findViewById(R.id.loan);

        loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TotalDue.this, budgetTotalDue.class);
                startActivity(intent);
            }
        });

        taxes = findViewById(R.id.taxes);

        taxes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TotalDue.this, budgetTotalDue.class);
                startActivity(intent);
            }
        });

        others = findViewById(R.id.others);

        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TotalDue.this, budgetTotalDue.class);
                startActivity(intent);
            }
        });


    }
}
