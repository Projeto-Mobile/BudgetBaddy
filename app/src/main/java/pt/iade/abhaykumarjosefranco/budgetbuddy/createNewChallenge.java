package pt.iade.abhaykumarjosefranco.budgetbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.ChallengeItem;

public class createNewChallenge extends AppCompatActivity {

    private Button start_challenge_button;
    private TextView textView;
    private EditText challengeEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chall2);

        start_challenge_button = findViewById(R.id.start_challenge_button);
        textView = findViewById(R.id.countdownTextView);

        start_challenge_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // First onClick: Navigate to ChallengesActivity and save a ChallengeItem
                Intent intent = new Intent(createNewChallenge.this, ChallengesActivity.class);

                ChallengeItem item = new ChallengeItem(-1, "", "");



                //item.setPeriod(Integer.parseInt(textView.getText().toString()));
                item.setPeriod(textView.getText().toString());






                item.setChallenge(challengeEditText.getText().toString());
                item.save();
                ChallengeItem.challengeItems.add(item);

                populateView();
                startActivity(intent);

                // TODO: set type id of item.
            }
        });

         // Second onClick: Make the input time dynamic with a CountDownTimer
        start_challenge_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountDownTimer(50000, 1000) { // 50 seconds
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



    /**
     * Updates the contents of the components in the activity according to the object associated
     * with this class.
     */
    protected void populateView() {

        // TODO: Get item from list and use it:
        /*
        budgetEditText.setText(String.valueOf(item.getBudgetValue()));

        String[] periods = getResources().getStringArray(R.array.periods);
        for (int i = 0; i < periods.length; i++) {
            if (periods[i].equals(item.getPeriod()))
                periodSpinner.setSelection(i);
        }
        */
    }
}
