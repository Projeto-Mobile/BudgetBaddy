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
import pt.iade.abhaykumarjosefranco.budgetbuddy.adapters.SpendingItemRowAdapter;

public class Viewspendings extends AppCompatActivity {
    private static final int EDITOR_ACTIVITY_RETURN_ID = 1;
    protected RecyclerView itemsListView;
    protected SpendingItemRowAdapter itemRowAdapter;
    protected ArrayList<SpendingItem> itemsList;

    protected SpendingItem item;
    protected int listPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewspendings);


        Intent intent = getIntent();
        listPosition = intent.getIntExtra("position",-1);
        item = (SpendingItem) intent.getSerializableExtra("item");

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
                int position = data.getIntExtra("position", -1);
                SpendingItem updatedItem = (SpendingItem) data.getSerializableExtra("item");

                if (position == -1) {
                    // Add the item to the list it was created new.
                    itemsList.add(updatedItem);
                    itemRowAdapter.notifyItemInserted(itemsList.size() - 1);
                } else {
                    // Updates an existing item on the list.
                    itemsList.set(position, updatedItem);
                    itemRowAdapter.notifyItemChanged(position);
                }
            }
        }
    }

    /**
     * Sets up the components and event handlers in the activity.
     */
    private void setupComponents() {

        SpendingItem.List(new SpendingItem.ListResponse() {
            @Override
            public void response(ArrayList<SpendingItem> items) {
                itemsList = items;

                // Set up row adapter with our items list.
                itemRowAdapter = new SpendingItemRowAdapter(Viewspendings.this, itemsList);
                itemRowAdapter.setOnClickListener(new SpendingItemRowAdapter.ItemClickListener() {

                    public void onItemClick(View view, int position) {

                        // Place our clicked item object in the intent to send to the other activity.
                        Intent intent = new Intent(Viewspendings.this, Spendings.class);
                        intent.putExtra("position", position);
                        intent.putExtra("item", itemsList.get(position));

                        startActivityForResult(intent, EDITOR_ACTIVITY_RETURN_ID);
                    }
                });

                itemsListView = (RecyclerView) findViewById(R.id.viewbS);
                itemsListView.setLayoutManager(new LinearLayoutManager(Viewspendings.this));
                itemsListView.setAdapter(itemRowAdapter);
            }
        });
    }

}