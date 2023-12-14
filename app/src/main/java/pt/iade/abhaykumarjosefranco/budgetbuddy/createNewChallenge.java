package pt.iade.abhaykumarjosefranco.budgetbuddy;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class createNewChallenge extends AppCompatActivity {

    private Button start_challenge_button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chall2);

        start_challenge_button = findViewById(R.id.start_challenge_button);
        textView = findViewById(R.id.countdownTextView);

        start_challenge_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // make the input time dynamic

                new CountDownTimer(50000, 1000) { //50 seconds
                    public void onTick(long millisUntilFinished) {

                        NumberFormat f = new DecimalFormat("00");
                        long hour = (millisUntilFinished / 3600000) % 24;
                        long min = (millisUntilFinished / 60000) % 60;
                        long sec = (millisUntilFinished / 1000) % 60;
                        textView.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
                    }

                    public void onFinish() {
                        textView.setText("00:00:00");
                    }
                }.start();
            }
        });
    }
    //add confetti to the layout or sound at the end of the challenge/count down time
}
