package pt.iade.abhaykumarjosefranco.budgetbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.BudgetItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.ChallengeItem;

public class createNewChallenge extends AppCompatActivity {

    private Button start_challenge_button;
    private EditText edittext_time;
    private EditText challengeEditText;
    protected ArrayList<ChallengeItem> itemsList;

    protected ChallengeItem item;
    protected int listPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chall2);


        Intent intent = getIntent();
        listPosition = intent.getIntExtra("position",-1);
        //item = (ChallengeItem) intent.getSerializableExtra("item");
        //itemsList = ChallengeItem.List();



        start_challenge_button = findViewById(R.id.start_challenge_button);
        edittext_time=findViewById(R.id.countdownTextView);
        challengeEditText = findViewById(R.id.edit_name_challenge);

   /* start_challenge_button.setOnClickListener(new View.OnClickListener() {
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
        });*/


        start_challenge_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String periodText = edittext_time.getText().toString();
                String[] timeParts = periodText.split(":"); //"HH:mm:ss"

                if (timeParts.length == 3) {
                    int hours = Integer.parseInt(timeParts[0]);
                    int minutes = Integer.parseInt(timeParts[1]);
                    int seconds = Integer.parseInt(timeParts[2]);

                    // Convert hours, minutes, and seconds to milliseconds
                    long totalTimeInMillis = (hours * 3600 + minutes * 60 + seconds) * 1000;

                    new CountDownTimer(totalTimeInMillis, 1000) {
                        public void onTick(long millisUntilFinished) {
                            NumberFormat f = new DecimalFormat("00");
                            long hour = (millisUntilFinished / 3600000) % 24;
                            long min = (millisUntilFinished / 60000) % 60;
                            long sec = (millisUntilFinished / 1000) % 60;
                            edittext_time.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
                        }

                        public void onFinish() {
                            edittext_time.setText("00:00:00");
                        }
                    }.start();
                }

                setupComponents();
            }
        });

    }
    //add confetti to the layout or sound at the end of the challenge/count down time

    private void setupComponents(){

        item = new ChallengeItem(-1, "", "00:00:00");
        item.setPeriod(edittext_time.getText().toString());
        item.setChallenge(challengeEditText.getText().toString());

        Intent intent = new Intent(createNewChallenge.this, ChallengesActivity.class);
        item.addChallenge(new ChallengeItem.SaveResponse() {
            @Override
            public void response() {
                populateView();
                intent.putExtra("item", item);
                intent.putExtra("position", listPosition);
                setResult(AppCompatActivity.RESULT_OK, intent);
                startActivity(intent);
            }
        });
    }

    protected void populateView() {


        item = new ChallengeItem(-1, "", "00:00:00");

        item.setPeriod(edittext_time.getText().toString());
        item.setChallenge(challengeEditText.getText().toString());


        // TODO: Get item from list and use it:

    }

}

























/*package pt.iade.abhaykumarjosefranco.budgetbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.ChallengeItem;

public class createNewChallenge extends AppCompatActivity {

    private Button start_challenge_button;
    private EditText edittext_time;
    private EditText challengeEditText;
    protected int listPosition;
    protected ChallengeItem item;
    protected ArrayList<ChallengeItem> itemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chall2);

        //itemsList = ChallengeItem.List();

   /* start_challenge_button.setOnClickListener(new View.OnClickListener() {
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

        Intent intent = getIntent();
        listPosition = intent.getIntExtra("position", -1);
        item = (ChallengeItem) intent.getSerializableExtra("item");
        setupComponents();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        start_challenge_button=findViewById(R.id.start_challenge_button);
        start_challenge_button.setOnClickListener(view -> {
            commitView();
            this.item.save();

            String periodText = edittext_time.getText().toString();
            String[] timeParts = periodText.split(":"); //"HH:mm:ss"

            if (timeParts.length == 3) {
                int hours = Integer.parseInt(timeParts[0]);
                int minutes = Integer.parseInt(timeParts[1]);
                int seconds = Integer.parseInt(timeParts[2]);

                // Convert hours, minutes, and seconds to milliseconds
                long totalTimeInMillis = (hours * 3600 + minutes * 60 + seconds) * 1000;

                new CountDownTimer(totalTimeInMillis, 1000) {
                    public void onTick(long millisUntilFinished) {
                        NumberFormat f = new DecimalFormat("00");
                        long hour = (millisUntilFinished / 3600000) % 24;
                        long min = (millisUntilFinished / 60000) % 60;
                        long sec = (millisUntilFinished / 1000) % 60;
                        edittext_time.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
                    }

                    public void onFinish() {
                        edittext_time.setText("00:00:00");
                    }
                }.start();
            }
            Intent returnIntent = new Intent();
            returnIntent.putExtra("position", this.listPosition);
            returnIntent.putExtra("item", this.item);
            setResult(AppCompatActivity.RESULT_OK, returnIntent);

            finish();
        });
        return super.onOptionsItemSelected(item);
    }

    //add confetti to the layout or sound at the end of the challenge/count down time

    private void setupComponents(){
        edittext_time=(EditText) findViewById(R.id.countdownTextView);
        challengeEditText = (EditText) findViewById(R.id.edit_name_challenge);
        populateView();
        /*
        Intent intent = new Intent(createNewChallenge.this, ChallengesActivity.class);

        ChallengeItem item = new ChallengeItem(-1, "", "");

        item.setPeriod(edittext_time.getText().toString());
        item.setChallenge(challengeEditText.getText().toString());
        item.save();
        ChallengeItem.challengeItems.add(item);
        populateView();


        startActivity(intent);
    }

    protected void populateView() {
        edittext_time.setText(item.getPeriod());
        challengeEditText.setText(item.getChallenge());
        // TODO: Get item from list and use it:
        /*
        budgetEditText.setText(String.valueOf(item.getBudgetValue()));

        String[] periods = getResources().getStringArray(R.array.periods);
        for (int i = 0; i < periods.length; i++) {
            if (periods[i].equals(item.getPeriod()))
                periodSpinner.setSelection(i);
        }

    }

    protected void commitView(){
        item.setPeriod(edittext_time.getText().toString());
        item.setChallenge(challengeEditText.getText().toString());
    }
}*/