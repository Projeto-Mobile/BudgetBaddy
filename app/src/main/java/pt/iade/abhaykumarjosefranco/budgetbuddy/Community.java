package pt.iade.abhaykumarjosefranco.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Community extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        TextView signupNameTextView = findViewById(R.id.signupNameTextview);

        String nameFromSignup = "signup_name";

        signupNameTextView.setText("Hi " + nameFromSignup);
    }
}