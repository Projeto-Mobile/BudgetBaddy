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

import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.BillItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.BudgetItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.UserItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.adapters.BillItemRowAdapter;
import pt.iade.abhaykumarjosefranco.budgetbuddy.adapters.BudgetItemRowAdapter;

public class ViewTotaldueBudget extends AppCompatActivity {

    private static final int EDITOR_ACTIVITY_RETURN_ID = 1;
    protected RecyclerView itemsListView;
    protected BillItemRowAdapter itemRowAdapter;
    protected ArrayList<BillItem> itemsList;
    protected BillItem item;
    UserItem user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_totaldue_budget);


        Intent intent = getIntent();
        user = (UserItem) intent.getSerializableExtra("user");
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
                BillItem updatedItem = (BillItem) data.getSerializableExtra("item");

                itemsList.add(updatedItem);
                itemRowAdapter.notifyItemInserted(itemsList.size() - 1);

            }
        }
    }


    /*private void setupComponents() {
        itemRowAdapter = new BillItemRowAdapter(this, itemsList);
        itemRowAdapter.setOnClickListener(new BillItemRowAdapter.ItemClickListener() {

            public void onItemClick(View view, int position) {

                Intent intent = new Intent(ViewTotaldueBudget.this, TotalDue.class);
                intent.putExtra("position", position);
                intent.putExtra("item", itemsList.get(position));

                startActivityForResult(intent, EDITOR_ACTIVITY_RETURN_ID);
            }
        });

        itemsListView = findViewById(R.id.viewb);
        itemsListView.setLayoutManager(new LinearLayoutManager(this));
        itemsListView.setAdapter(itemRowAdapter);
    }*/


    private void setupComponents() {
        itemsListView = findViewById(R.id.viewb);
        itemsListView.setLayoutManager(new LinearLayoutManager(this));
        itemsList = new ArrayList<>();
        itemRowAdapter = new BillItemRowAdapter(this, itemsList);


        BillItem.List(new BillItem.ListResponse() {
            @Override
            public void response(ArrayList<BillItem> items) {
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


    /*private void setupComponents() {

        BillItem.List(new BillItem.ListResponse() {
            @Override
            public void response(ArrayList<BillItem> items) {
                itemsList = items;

                itemRowAdapter = new BillItemRowAdapter(ViewTotaldueBudget.this, itemsList);
                itemRowAdapter.setOnClickListener(new BillItemRowAdapter.ItemClickListener() {

                    public void onItemClick(View view, int position) {

                        Intent intent = new Intent(ViewTotaldueBudget.this, Category.class);
                        intent.putExtra("position", position);
                        intent.putExtra("item", itemsList.get(position));

                        startActivityForResult(intent, EDITOR_ACTIVITY_RETURN_ID);
                    }
                });

                itemsListView = (RecyclerView) findViewById(R.id.viewb);
                itemsListView.setLayoutManager(new LinearLayoutManager(ViewTotaldueBudget.this));
                itemsListView.setAdapter(itemRowAdapter);
            }

        });
    }*/
}
