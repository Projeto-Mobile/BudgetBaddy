package pt.iade.abhaykumarjosefranco.budgetbuddy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.BudgetItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.adapters.BudgetItemRowAdapter;

public class ViewBudget extends AppCompatActivity {

    private static final int EDITOR_ACTIVITY_RETURN_ID = 1;
    protected RecyclerView itemsListView;
    protected BudgetItemRowAdapter itemRowAdapter;
    protected ArrayList<BudgetItem> itemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_budget);


        //itemsList = BudgetItem.budgetItems;
        //Intent intent = getIntent();
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
                BudgetItem updatedItem = (BudgetItem) data.getSerializableExtra("item");
                Log.e("onActivityResult", "updatedItemId=" +updatedItem.getId());

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

    private void setupComponents() {

        BudgetItem.List(new BudgetItem.ListResponse() {
            @Override
            public void response(ArrayList<BudgetItem> items) {
                itemsList = items;

                itemRowAdapter = new BudgetItemRowAdapter(ViewBudget.this, itemsList);
                itemRowAdapter.setOnClickListener(new BudgetItemRowAdapter.ItemClickListener() {

                    public void onItemClick(View view, int position) {

                        Intent intent = new Intent(ViewBudget.this, Category.class);
                        intent.putExtra("position", position);
                        intent.putExtra("item", itemsList.get(position));

                        startActivityForResult(intent, EDITOR_ACTIVITY_RETURN_ID);
                    }
                });

                itemsListView = (RecyclerView) findViewById(R.id.viewbRV);
                itemsListView.setLayoutManager(new LinearLayoutManager(ViewBudget.this));
                itemsListView.setAdapter(itemRowAdapter);
            }
        });
    }
}
