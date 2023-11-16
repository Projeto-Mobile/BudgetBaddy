package pt.iade.abhaykumarjosefranco.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import pt.iade.abhaykumarjosefranco.budgetbuddy.R;

public class Category extends AppCompatActivity {
    private TextView categories,selectcategories;

    private Button dinningout_button,travel_button,subscription_button,shopping_button,leisure_button,personcare_button,specialoccassions_button,transportation_button,workexpenses_button,others_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
    }
}