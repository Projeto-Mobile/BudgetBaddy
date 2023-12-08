package pt.iade.abhaykumarjosefranco.budgetbuddy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.BillItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.adapters.BillItemRowAdapter;

public class ViewTotaldueBudget extends AppCompatActivity {

    private static final int EDITOR_ACTIVITY_RETURN_ID = 1;

    protected RecyclerView itemsListView;
    protected BillItemRowAdapter itemRowAdapter;
    protected ArrayList<BillItem> itemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_totaldue_budget);

        itemsList = BillItem.List();

        setupComponents();
    }

    /**
     * Gets the result back from another activity.
     *
     * @param requestCode Code sent with the {@link #startActivityForResult(Intent, int)}
     * @param resultCode  Code that was returned from the other activity (usually a flag of success).
     * @param data        Data sent back from the other activity in the form of an Intent.
     */
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
                BillItem updatedItem = (BillItem) data.getSerializableExtra("item");

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
        // Set up row adapter with our items list.
        itemRowAdapter = new BillItemRowAdapter(this, itemsList);
        itemRowAdapter.setOnClickListener(new BillItemRowAdapter.ItemClickListener() {

            public void onItemClick(View view, int position) {
                // Place our clicked item object in the intent to send to the other activity.
                Intent intent = new Intent(ViewTotaldueBudget.this, TotalDue.class);
                intent.putExtra("position", position);
                intent.putExtra("item", itemsList.get(position));

                startActivityForResult(intent, EDITOR_ACTIVITY_RETURN_ID);
            }
        });

        // Set up the items recycler view.
        itemsListView = findViewById(R.id.viewb);
        itemsListView.setLayoutManager(new LinearLayoutManager(this));
        itemsListView.setAdapter(itemRowAdapter);
    }
}
