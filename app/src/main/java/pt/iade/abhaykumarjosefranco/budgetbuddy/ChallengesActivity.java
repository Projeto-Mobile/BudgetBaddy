package pt.iade.abhaykumarjosefranco.budgetbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.ChallengeItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.adapters.ChallengeItemAdapter;

public class ChallengesActivity extends AppCompatActivity {

    private Button create_challenges_button;
    private static final int EDITOR_ACTIVITY_RETURN_ID = 1;
    protected RecyclerView itemsListView;
    protected ChallengeItemAdapter itemRowAdapter;
    protected ArrayList<ChallengeItem> itemsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);

        Intent intent = getIntent();
        //itemsList = ChallengeItem.challengeItems;
        itemsList = new ArrayList<>();
        setupComponents();

        create_challenges_button = findViewById(R.id.create_challenges_button);
        create_challenges_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChallengesActivity.this, createNewChallenge.class);
                startActivity(intent);
            }
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
                int position = data.getIntExtra("position", -1);
                ChallengeItem updatedItem = (ChallengeItem) data.getSerializableExtra("item");

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
        // Set up row adapter with our items list.
        itemRowAdapter = new ChallengeItemAdapter(this, itemsList);

        Intent intent = getIntent();

        itemRowAdapter.setOnClickListener(new ChallengeItemAdapter.ItemClickListener() {

            public void onItemClick(View view, int position) {

                // Place our clicked item object in the intent to send to the other activity.
                Intent intent = new Intent(ChallengesActivity.this, createNewChallenge.class);
                intent.putExtra("position", position);
                intent.putExtra("item", itemsList.get(position));

                startActivityForResult(intent, EDITOR_ACTIVITY_RETURN_ID);
            }
        });

        itemsListView = findViewById(R.id.challengeRV);
        itemsListView.setLayoutManager(new LinearLayoutManager(this));
        itemsListView.setAdapter(itemRowAdapter);
    }
}






















/*package pt.iade.abhaykumarjosefranco.budgetbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.ChallengeItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.adapters.ChallengeItemAdapter;

public class ChallengesActivity extends AppCompatActivity {

    private Button create_challenges_button;
    private static final int EDITOR_ACTIVITY_RETURN_ID = 1;
    protected RecyclerView itemsListView;
    protected ChallengeItemAdapter itemRowAdapter;
    protected ArrayList<ChallengeItem> itemsList;



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.create_challenges_button) {
            Intent intent = new Intent(ChallengesActivity.this, createNewChallenge.class);
            intent.putExtra("position", -1);
            intent.putExtra("item", new ChallengeItem());

            startActivityForResult(intent, EDITOR_ACTIVITY_RETURN_ID);

            return true;
        }
        return super.onOptionsItemSelected(item);

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
                ChallengeItem updatedItem = (ChallengeItem) data.getSerializableExtra("item");

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);

        //itemsList = ChallengeItem.List();


        //Intent intent = getIntent();
        itemsList = new ArrayList<>();
        itemsList = ChallengeItem.challengeItems;


        create_challenges_button = findViewById(R.id.create_challenges_button);
        create_challenges_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChallengesActivity.this, createNewChallenge.class);
                intent.putExtra("position", -1);
                intent.putExtra("item",new ChallengeItem());

                //startActivity(intent);
                startActivityForResult(intent, EDITOR_ACTIVITY_RETURN_ID);
            }
        });
        //itemsList= ChallengeItem.List();
        itemsList = new ArrayList<>();
        setupComponents();
    }

    private void setupComponents() {
        // Set up row adapter with our items list.
        itemRowAdapter = new ChallengeItemAdapter(this, itemsList);

        Intent intent = getIntent();

        itemRowAdapter.setOnClickListener(new ChallengeItemAdapter.ItemClickListener() {

            public void onItemClick(View view, int position) {

                // Place our clicked item object in the intent to send to the other activity.
                Intent intent = new Intent(ChallengesActivity.this, createNewChallenge.class);
                intent.putExtra("position", position);
                intent.putExtra("item", itemsList.get(position));

                startActivityForResult(intent, EDITOR_ACTIVITY_RETURN_ID);
            }
        });

        itemsListView = findViewById(R.id.challengeRV);
        itemsListView.setLayoutManager(new LinearLayoutManager(this));
        itemsListView.setAdapter(itemRowAdapter);
    }
}
*/