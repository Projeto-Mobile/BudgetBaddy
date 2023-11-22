package pt.iade.abhaykumarjosefranco.budgetbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class WalletActivity extends AppCompatActivity {

    private Button oldreceipt_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        oldreceipt_button = findViewById(R.id.oldreceipt_button);
        oldreceipt_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WalletActivity.this, oldreceipt.class);
                startActivity(intent);
            }
        });
    }
}
