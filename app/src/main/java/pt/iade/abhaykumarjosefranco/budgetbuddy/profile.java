package pt.iade.abhaykumarjosefranco.budgetbuddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.BudgetItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.UserItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.adapters.BudgetItemRowAdapter;

public class Profile extends Activity {

    //private FirebaseAuth auth;

    private static final int EDITOR_ACTIVITY_RETURN_ID = 1;
    private BottomNavigationView bottomNavigationView;
    private Button log_out_button,delete_account_button, community_button;
    private EditText nameEditText;
    private EditText emailEditText;
    protected ArrayList<UserItem> itemsList;
    private UserItem user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        user= (UserItem) intent.getSerializableExtra("user");

        setupComponents();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                startActivity(new Intent(getApplicationContext(), OverviewActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.wallet) {
                startActivity(new Intent(getApplicationContext(), WalletActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.profile) {
                return true;
            }
            return false;
        });




        log_out_button = findViewById(R.id.log_out_button);
        log_out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        /*log_out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log out the user and redirect to the login activity
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Profile.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Close the current activity
            }
        });*/

        delete_account_button = findViewById(R.id.delete_account_button);
        /*delete_account_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Delete the user account
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    FirebaseAuth.getInstance().getCurrentUser().delete()
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Profile.this, "Account deleted successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Profile.this, SignUpActivity.class);
                                    startActivity(intent);
                                    finish(); // Close the current activity
                                } else {
                                    Toast.makeText(Profile.this, "Account deletion failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Toast.makeText(Profile.this, "No user is currently signed in", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        EditText aboutEditText = findViewById(R.id.signup_about);

        aboutEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, About.class);
                startActivity(intent);
            }
        });

        community_button = findViewById(R.id.community_button);
        community_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, Community.class);
                startActivity(intent);
            }
        });






        //FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

       /* if (currentUser != null) {
            // The user that is signed in
            String name = currentUser.getDisplayName();
            String email = currentUser.getEmail();

            // Set the retrieved data to the EditText
            nameEditText.setText(name);
            emailEditText.setText(email);
        }*/

    }

    private void setupComponents() {
        nameEditText = findViewById(R.id.signup_name);
        emailEditText = findViewById(R.id.signup_email);

        commitView();
    }
    protected void commitView(){
        user.setName(nameEditText.getText().toString());
        user.setEmail(emailEditText.getText().toString());

    }




}
