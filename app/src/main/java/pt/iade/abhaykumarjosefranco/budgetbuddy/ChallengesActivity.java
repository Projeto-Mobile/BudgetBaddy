package pt.iade.abhaykumarjosefranco.budgetbuddy;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.Size;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import nl.dionsegijn.konfetti.core.Party;
import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.xml.KonfettiView;
import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.ChallengeItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.adapters.ChallengeItemAdapter;
//import nl.dionsegijn.konfetti.xml.image.ImageUtil;

public class ChallengesActivity extends AppCompatActivity {

    private Button create_challenges_button, button;
    private static final int EDITOR_ACTIVITY_RETURN_ID = 1;
    protected RecyclerView itemsListView;
    protected ChallengeItemAdapter itemRowAdapter;
    protected ArrayList<ChallengeItem> itemsList;
    private Shape.DrawableShape drawableShape = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);

        //Intent intent = getIntent();
        //itemsList = ChallengeItem.challengeItems;
        //itemsList = new ArrayList<>();
        setupComponents();

        /*KonfettiView konfettiView = findViewById(R.id.konfettiView);

        final Drawable drawable =
                ContextCompat.getDrawable(getApplicationContext(), R.drawable.img_1);
        drawableShape = ImageUtil.loadDrawable(drawable, true, true);

        konfettiView = findViewById(R.id.konfettiView);
        EmitterConfig emitterConfig = new Emitter(5L, TimeUnit.SECONDS).perSecond(50);
        Party party =
                new PartyFactory(emitterConfig)
                        .angle(270)
                        .spread(90)
                        .setSpeedBetween(1f, 5f)
                        .timeToLive(2000L)
                        .shapes(new Shape.Rectangle(0.2f), drawableShape)
                        .sizes(new Size(12, 5f, 0.2f))
                        .position(0.0, 0.0, 1.0, 0.0)
                        .build();
        konfettiView.setOnClickListener(view -> konfettiView.start(party));
        findViewById(R.id.btnExplode)
                .setOnClickListener(
                        v -> {
                            explode();
                        });

        public void explode() {
            EmitterConfig emitterConfig = new Emitter(100L, TimeUnit.MILLISECONDS).max(100);
            konfettiView.start(
                    new PartyFactory(emitterConfig)
                            .spread(360)
                            .shapes(Arrays.asList(Shape.Square.INSTANCE, Shape.Circle.INSTANCE, drawableShape))
                            .colors(Arrays.asList(0xfce18a, 0xff726d, 0xf4306d, 0xb48def))
                            .setSpeedBetween(0f, 30f)
                            .position(new Relative(0.5, 0.3))
                            .build());
        }


         */

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

        ChallengeItem.List(new ChallengeItem.ListResponse() {
            @Override
            public void response(ArrayList<ChallengeItem> items) {
                itemsList = items;

                itemRowAdapter = new ChallengeItemAdapter(ChallengesActivity.this, itemsList);

                Intent intent = getIntent();

                itemRowAdapter.setOnClickListener(new ChallengeItemAdapter.ItemClickListener() {

                    public void onItemClick(View view, int position) {


                        Intent intent = new Intent(ChallengesActivity.this, createNewChallenge.class);
                        intent.putExtra("position", position);
                        intent.putExtra("item", itemsList.get(position));

                        startActivityForResult(intent, EDITOR_ACTIVITY_RETURN_ID);
                    }
                });

                itemsListView = findViewById(R.id.challengeRV);
                itemsListView.setLayoutManager(new LinearLayoutManager(ChallengesActivity.this));
                itemsListView.setAdapter(itemRowAdapter);
            }
        });
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