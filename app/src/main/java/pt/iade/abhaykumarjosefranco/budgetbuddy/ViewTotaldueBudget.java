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
import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.BudgetItem;
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


        itemsList = BillItem.billItems;
        Intent intent = getIntent();
        setupComponents();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDITOR_ACTIVITY_RETURN_ID) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                int position = data.getIntExtra("position", -1);
                BillItem updatedItem = (BillItem) data.getSerializableExtra("item");

                if (position == -1) {
                    itemsList.add(updatedItem);
                    itemRowAdapter.notifyItemInserted(itemsList.size() - 1);
                } else {
                    itemsList.set(position, updatedItem);
                    itemRowAdapter.notifyItemChanged(position);
                }
            }
        }
    }

    private void setupComponents() {
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
    }
}
