package pt.iade.abhaykumarjosefranco.budgetbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class OverviewActivity extends AppCompatActivity  {

    private Button totalexpense_button, totaldue_button, totalsaving_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        totalexpense_button = findViewById(R.id.totalexpense_button);
        totalexpense_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OverviewActivity.this, WalletActivity.class);
                startActivity(intent);
            }
        });

        totaldue_button = findViewById(R.id.total_due_button);
        totaldue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OverviewActivity.this, TotalDue.class);
                startActivity(intent);
            }
        });

        totalsaving_button = findViewById(R.id.total_saving_button);
        totalsaving_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OverviewActivity.this, ChallengesActivity.class);
                startActivity(intent);
            }
        });
    }

}
