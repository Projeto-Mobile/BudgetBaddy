package pt.iade.abhaykumarjosefranco.budgetbuddy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.CommunityItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.R;

public class CommunityItemAdapter extends RecyclerView.Adapter<CommunityItemAdapter.ViewHolder> {

    private ArrayList<CommunityItem> items;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;


    public CommunityItemAdapter(Context context, ArrayList<CommunityItem> items){
        inflater = LayoutInflater.from(context);
        this.items = items;
        clickListener = null;
    }

    public void setOnClickListener(ItemClickListener listener){
        clickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.row_community_item, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        CommunityItem item = items.get(position);

        holder.name.setText(item.getName());
        holder.amount.setText(item.getAmount());
    }
    @Override
    public int getItemCount(){
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public  TextView name;
        public TextView amount;



        public ViewHolder(View itemView) {
            super(itemView);

            // Get the components in the view.

            name = itemView.findViewById(R.id.chosencategory);

            amount = itemView.findViewById(R.id.value);
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

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
}
