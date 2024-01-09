package pt.iade.abhaykumarjosefranco.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.UserItem;

public class LoginActivity extends AppCompatActivity {

    //private FirebaseAuth auth;
    private EditText  loginEmail, loginPassword;
    private TextView loginRedirectText;
    private Button loginButton;

    private UserItem user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = getIntent();
        user = (UserItem) intent.getSerializableExtra("user") ;



        //auth = FirebaseAuth.getInstance();
        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);


        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email = loginEmail.getText().toString();
                String pass = loginPassword.getText().toString();

                if (loginEmail.equals("")) {
                    loginEmail.setError("Email is Required");
                } else if (loginPassword.equals("")) {
                    loginPassword.setError("Password is Required");
                } else {
                    UserItem.Login(email, pass, new UserItem.LoginResponse() {
                        @Override
                        public void response(UserItem returnedUser) {
                            UserItem.GetById(returnedUser.getId(), new UserItem.GetByIdResponse() {
                                @Override
                                public void response(UserItem returnedUser) {
                                    Intent intent = new Intent(LoginActivity.this, Profile.class);
                                    intent.putExtra("user", returnedUser);
                                    startActivity(intent);
                                }
                            });


                /*if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    loginEmail.setError("Invalid or empty email");
                } else if (pass.isEmpty()) {
                    loginPassword.setError("Password cannot be empty.");
                } else {
                    auth.signInWithEmailAndPassword(email, pass)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                }
                                // public void onRequestPermissionResult()
                       });
                }*/
                        }
                    });

                    loginRedirectText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                        }
                    });

                }
            }
        });
    }
}

