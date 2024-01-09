package pt.iade.abhaykumarjosefranco.budgetbuddy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.BudgetItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.CommunityItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.UserItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.adapters.CommunityItemAdapter;


public class Community extends AppCompatActivity {

    private Button add_button;
    private static final int EDITOR_ACTIVITY_RETURN_ID = 1;
    protected RecyclerView itemsListView;
    protected CommunityItemAdapter itemRowAdapter;
    protected ArrayList<CommunityItem> itemsList;
    protected CommunityItem item;
    private UserItem user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        //Intent intent = getIntent();
        //itemsList = new ArrayList<>();
        //itemsList = CommunityItem.List(); THIS WAS WORKING BECAUSE OF THIS .

        Intent intent = getIntent();
        //item = (CommunityItem) intent.getSerializableExtra("item");
        user = (UserItem) intent.getSerializableExtra("user") ;

        setupComponents();

        add_button = findViewById(R.id.add_community_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Community.this, create_community.class);
                startActivity(intent);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // Must be called always and before everything.
        super.onActivityResult(requestCode, resultCode, data);

        // Check which activity returned to us.
        if (requestCode == EDITOR_ACTIVITY_RETURN_ID) {
            // Check if the activity was successful.
            if (resultCode == AppCompatActivity.RESULT_OK) {
                // Get extras returned to us.
                int position = data.getIntExtra("position", -1);
                CommunityItem updatedItem = (CommunityItem) data.getSerializableExtra("item");

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

        CommunityItem.List(new CommunityItem.ListResponse() {
            @Override
            public void response(ArrayList<CommunityItem> items) {
                itemsList = items;

                // Set up row adapter with our items list.
                itemRowAdapter = new CommunityItemAdapter(Community.this, itemsList);

                Intent intent = getIntent();

                itemRowAdapter.setOnClickListener(new CommunityItemAdapter.ItemClickListener() {

                    public void onItemClick(View view, int position) {

                        // Place our clicked item object in the intent to send to the other activity.
                        Intent intent = new Intent(Community.this, create_community.class);
                        intent.putExtra("position", position);
                        intent.putExtra("item", itemsList.get(position));

                        startActivityForResult(intent, EDITOR_ACTIVITY_RETURN_ID);
                    }
                });

                itemsListView = findViewById(R.id.communityRV);
                itemsListView.setLayoutManager(new LinearLayoutManager(Community.this));
                itemsListView.setAdapter(itemRowAdapter);
            }
        });
    }
}