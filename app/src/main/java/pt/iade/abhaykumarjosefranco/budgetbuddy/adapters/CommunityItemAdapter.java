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

        holder.name1.setText(item.getUser1());
        holder.budget1.setText(String.valueOf(item.getAmount1()));
        holder.name2.setText(item.getUser2());
        holder.budget2.setText(String.valueOf(item.getAmount2()));
        holder.name3.setText(item.getUser3());
        holder.budget3.setText(String.valueOf(item.getAmount3()));
        holder.name4.setText(item.getUser4());
        holder.budget4.setText(String.valueOf(item.getAmount4()));
        holder.name5.setText(item.getUser5());
        holder.budget5.setText(String.valueOf(item.getAmount5()));
    }
    @Override
    public int getItemCount(){
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name1;
        public TextView budget1;

        public TextView name2;
        public TextView budget2;

        public TextView name3;
        public TextView budget3;

        public TextView name4;
        public TextView budget4;

        public TextView name5;
        public TextView budget5;

        public ViewHolder(View itemView) {
            super(itemView);

            // Get the components in the view.
            name1 = itemView.findViewById(R.id.user1);
            budget1 = itemView.findViewById(R.id.amount1);

            name2 = itemView.findViewById(R.id.user2);
            budget2 = itemView.findViewById(R.id.amount2);

            name3 = itemView.findViewById(R.id.user3);
            budget3 = itemView.findViewById(R.id.amount3);

            name4 = itemView.findViewById(R.id.user4);
            budget4 = itemView.findViewById(R.id.amount4);

            name5 = itemView.findViewById(R.id.user5);
            budget5 = itemView.findViewById(R.id.amount5);
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
