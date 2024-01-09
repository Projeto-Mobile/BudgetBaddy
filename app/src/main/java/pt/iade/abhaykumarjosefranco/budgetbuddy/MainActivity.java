package pt.iade.abhaykumarjosefranco.budgetbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.UserItem;

public class MainActivity extends AppCompatActivity {

    private UserItem user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        user= (UserItem) intent.getSerializableExtra("user");


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, EditBudget.class);
                startActivity(intent);

                finish();
            }
        }, 2000);
    }
}
