package pt.iade.abhaykumarjosefranco.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.UserItem;

public class SignUpActivity extends AppCompatActivity {

    //private FirebaseAuth auth;
    private EditText signupName, signupEmail, signupPassword;
    private Button signupButton;
    private TextView loginRedirectText;

    protected int listPosition;
    private UserItem user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //auth = FirebaseAuth.getInstance();
        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = signupName.getText().toString();
                String email = signupEmail.getText().toString();
                String pass = signupPassword.getText().toString();

                if (email.isEmpty() || pass.isEmpty() || name.isEmpty()) {
                    if (name.isEmpty()) {
                        signupEmail.setError("Username cannot be empty.");
                    }
                    if (email.isEmpty()) {
                        signupEmail.setError("Email cannot be empty.");
                    }
                    if (pass.isEmpty()) {
                        signupPassword.setError("Password cannot be empty.");
                    }
                } else {

                    user = new UserItem();
                    user.setEmail(email);
                    user.setName(name);
                    user.setPassword(pass);


                    user.add(SignUpActivity.this, new UserItem.SaveResponse() {
                        @Override
                        public void response() {
                            Intent intent = new Intent(SignUpActivity.this, OverviewActivity.class);
                            intent.putExtra("user", user);
                            startActivity(intent);

                        }
                    });

                    /*auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                            } else {
                                Toast.makeText(SignUpActivity.this, "Signup Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });*/
                }
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });


    }

    private void setupComponents() {



    }
}
