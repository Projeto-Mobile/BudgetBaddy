package pt.iade.abhaykumarjosefranco.budgetbuddy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.BudgetItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.SpendingItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.UserItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.adapters.BudgetItemRowAdapter;
import pt.iade.abhaykumarjosefranco.budgetbuddy.adapters.SpendingItemRowAdapter;

public class Viewspendings extends AppCompatActivity {
    private static final int EDITOR_ACTIVITY_RETURN_ID = 1;
    protected RecyclerView itemsListView;
    protected SpendingItemRowAdapter itemRowAdapter;
    protected ArrayList<SpendingItem> itemsList;

    protected SpendingItem item;
    protected int listPosition;
    private UserItem user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewspendings);


        Intent intent = getIntent();
        user = (UserItem) intent.getSerializableExtra("user") ;

        //listPosition = intent.getIntExtra("position",-1);
        //item = (SpendingItem) intent.getSerializableExtra("item");

        setupComponents();

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
                SpendingItem updatedItem = (SpendingItem) data.getSerializableExtra("item");

                itemsList.add(updatedItem);
                itemRowAdapter.notifyItemInserted(itemsList.size() - 1);

            }
        }
    }


    private void setupComponents() {
        itemsListView = findViewById(R.id.viewbS);
        itemsListView.setLayoutManager(new LinearLayoutManager(this));
        itemsList = new ArrayList<>();
        itemRowAdapter = new SpendingItemRowAdapter(this, itemsList);

        SpendingItem.List(new SpendingItem.ListResponse() {
            @Override
            public void response(ArrayList<SpendingItem> items) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        itemsList.clear();
                        itemsList.addAll(items);
                        itemsListView.setAdapter(itemRowAdapter);
                        itemRowAdapter.notifyDataSetChanged();
                    }
                });

            }
        });
    }

}