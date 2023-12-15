package pt.iade.abhaykumarjosefranco.budgetbuddy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.ChallengeItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.R;


public class ChallengeItemAdapter extends RecyclerView.Adapter<ChallengeItemAdapter.ViewHolder>{


    private ArrayList<ChallengeItem> items;
    private LayoutInflater inflater;
    private ChallengeItemAdapter.ItemClickListener clickListener;


    public ChallengeItemAdapter(Context context, ArrayList<ChallengeItem> items) {
        inflater = LayoutInflater.from(context);
        this.items = items;
        clickListener = null;
    }

    /**
     * Sets the event listener when a row gets clicked by the user.
     *
     * @param listener Event handler for the click.
     */
    public void setOnClickListener(ChallengeItemAdapter.ItemClickListener listener) {
        clickListener = listener;
    }

    /**
     * Inflates the layout of the row into the actual list.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return Instantiated row layout.
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_challenge_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Binds the data from each item in the list to a row in the list.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    //TODO: ID ?????
    public void onBindViewHolder(ChallengeItemAdapter.ViewHolder holder, int position) {
        ChallengeItem item = items.get(position);

        holder.namechallenge.setText(item.getChallenge());
        holder.nameperiod.setText(String.valueOf(item.getPeriod()));
    }

    /**
     * The RecyclerView needs to know the size of our list, this just provides that.
     *
     * @return Size of our data.
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Class responsible for recycling the views as you scroll.
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView namechallenge;
        public TextView nameperiod;

        /**
         * Sets up the view that was inflated.
         *
         * @param itemView Layout view that was inflated.
         */
        public ViewHolder(View itemView) {
            super(itemView);

            // Get the components in the view.
            namechallenge = itemView.findViewById(R.id.edit_name_challenge);
            nameperiod = itemView.findViewById(R.id.countdownTextView);

            // Set what happens when the view gets clicked.
            itemView.setOnClickListener(this);
        }

        /**
         * Handles the onclick event of the view.
         */
        @Override
        public void onClick(View view) {
            // Pass the event to our custom listener in the activity.
            if (clickListener != null) {
                clickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    /**
     * Defines what to do when a list item gets clicked.
     */
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
