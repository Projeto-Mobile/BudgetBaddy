package pt.iade.abhaykumarjosefranco.budgetbuddy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class createNewChallenge extends AppCompatActivity {

    private Button start_challenge_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chall2);

        start_challenge_button = findViewById(R.id.start_challenge_button);



    }
}
