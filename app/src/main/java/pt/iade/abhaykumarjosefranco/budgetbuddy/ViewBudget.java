package pt.iade.abhaykumarjosefranco.budgetbuddy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.BudgetItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.UserItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.adapters.BudgetItemRowAdapter;

public class ViewBudget extends AppCompatActivity {

    private static final int EDITOR_ACTIVITY_RETURN_ID = 1;
    protected RecyclerView itemsListView;
    protected BudgetItemRowAdapter itemRowAdapter;
    protected ArrayList<BudgetItem> itemsList;
    protected BudgetItem item;
    Button add_budget;
    private BottomNavigationView bottomNavigationView;
    UserItem user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_budget);


        //itemsList = BudgetItem.budgetItems;
        Intent intent = getIntent();
        user = (UserItem) intent.getSerializableExtra("user");

        setupComponents();


        add_budget = findViewById(R.id.add_budget);
        add_budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ViewBudget.this, EditBudget.class);
                intent1.putExtra("user", user);
                startActivityForResult(intent1, EDITOR_ACTIVITY_RETURN_ID);
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                finish();
                return true;
            } else if (item.getItemId() == R.id.wallet) {
                startActivity(new Intent(getApplicationContext(), WalletActivity.class).putExtra("user", user));
                finish();
                return true;
            }else if (item.getItemId() == R.id.profile) {
                startActivity(new Intent(getApplicationContext(), Profile.class).putExtra("user", user));
                finish();
                return true;
            }
            return false;
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // Must be called always and before everything.
        super.onActivityResult(requestCode, resultCode, data);

        // Check which activity returned to us.
        if (requestCode == EDITOR_ACTIVITY_RETURN_ID) {
            // Check if the activity was successful.
            if (resultCode == AppCompatActivity.RESULT_OK) {
                // Get extras returned to us.

                BudgetItem updatedItem = (BudgetItem) data.getSerializableExtra("item");

                itemsList.add(updatedItem);
                itemRowAdapter.notifyItemInserted(itemsList.size() - 1);
            }
        }
    }



    private void setupComponents() {
        itemsListView = findViewById(R.id.viewbRV);
        itemsListView.setLayoutManager(new LinearLayoutManager(this));
        itemsList = new ArrayList<>();
        itemRowAdapter = new BudgetItemRowAdapter(this, itemsList);


        BudgetItem.List(new BudgetItem.ListResponse() {
            @Override
            public void response(ArrayList<BudgetItem> items) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        itemsList.clear();
                        itemsList.addAll(items);
                        itemsListView.setAdapter(itemRowAdapter); // Set the adapter here
                        itemRowAdapter.notifyDataSetChanged();
                    }
                });

            }
        });
    }
}









