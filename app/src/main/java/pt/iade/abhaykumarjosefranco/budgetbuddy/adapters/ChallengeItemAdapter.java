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
    private ItemClickListener clickListener;


    public ChallengeItemAdapter(Context context, ArrayList<ChallengeItem> items) {
        inflater = LayoutInflater.from(context);
        this.items = items;
        clickListener = null;
    }

    public void setOnClickListener(ItemClickListener listener) {
        clickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_challenge_item, parent, false);
        return new ViewHolder(view);
    }

    //TODO: ID ?????
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChallengeItem item = items.get(position);

        holder.namechallenge.setText(item.getChallenge());
        holder.nameperiod.setText(String.valueOf(item.getPeriod()));
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView namechallenge;
        public TextView nameperiod;

        public ViewHolder(View itemView) {
            super(itemView);

            // Get the components in the view.
            namechallenge = itemView.findViewById(R.id.edit_name_challenge);
            nameperiod = itemView.findViewById(R.id.countdownTextView);

            // Set what happens when the view gets clicked.
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Pass the event to our custom listener in the activity.
            if (clickListener != null) {
                clickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}














/*package pt.iade.abhaykumarjosefranco.budgetbuddy.adapters;

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

    public void setOnClickListener(ChallengeItemAdapter.ItemClickListener listener) {
        clickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_challenge_item, parent, false);
        return new ViewHolder(view);
    }

    //TODO: ID ?????
    public void onBindViewHolder(ChallengeItemAdapter.ViewHolder holder, int position) {
        ChallengeItem item = items.get(position);

        holder.namechallenge.setText(item.getChallenge());
        holder.nameperiod.setText(String.valueOf(item.getPeriod()));
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView namechallenge;
        public TextView nameperiod;

        public ViewHolder(View itemView) {
            super(itemView);

            // Get the components in the view.
            namechallenge = itemView.findViewById(R.id.edit_name_challenge);
            nameperiod = itemView.findViewById(R.id.countdownTextView);

            // Set what happens when the view gets clicked.
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Pass the event to our custom listener in the activity.
            if (clickListener != null) {
                clickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}*/
