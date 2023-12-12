package pt.iade.abhaykumarjosefranco.budgetbuddy;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.provider.MediaStore;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WalletActivity extends AppCompatActivity {

    private Button oldreceipt_button, picture_button;

    private ImageView ImageView01;

    private BottomNavigationView bottomNavigationView;

    protected final static int PERMISSION_REQUEST_CODE = 22;
    protected final static int CAMERA_PIC_REQUEST = 23;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        oldreceipt_button = findViewById(R.id.oldreceipt_button);
        picture_button = findViewById(R.id.picture_button);
        picture_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
            }
        });


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.wallet);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                startActivity(new Intent(getApplicationContext(), OverviewActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.wallet) {
                return true;
            } else if (item.getItemId() == R.id.add) {
                startActivity(new Intent(getApplicationContext(), Category.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.profile) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
                finish();
                return true;
            }
            return false;
        });

        requestCameraAccess();
    }

    protected void requestCameraAccess() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.CAMERA},
                    PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_PIC_REQUEST) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            ImageView imageview = (ImageView) findViewById(R.id.ImageView01); //sets imageview as the bitmap
            imageview.setImageBitmap(image);
        }
    }
}
