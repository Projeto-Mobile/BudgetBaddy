package pt.iade.abhaykumarjosefranco.budgetbuddy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.SpendingItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.R;

public class SpendingItemRowAdapter extends RecyclerView.Adapter<SpendingItemRowAdapter.ViewHolder>{

    private ArrayList<SpendingItem> items;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;

    public SpendingItemRowAdapter(Context context, ArrayList<SpendingItem> items) {
        inflater = LayoutInflater.from(context);
        this.items = items;
        clickListener = null;
    }


    public void setOnClickListener(ItemClickListener listener) {
        clickListener = listener;
    }

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_spending_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SpendingItem item = items.get(position);

        holder.namespending.setText(item.getName());
        holder.thevalue.setText(String.valueOf(item.getSpendValue()));
        holder.dates.setText(item.getDate().format(dateFormatter));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView namespending;
        public TextView dates;
        public TextView thevalue;


        public ViewHolder(View itemView) {
            super(itemView);

            // Get the components in the view.
            namespending = itemView.findViewById(R.id.spenditure);
            thevalue = itemView.findViewById(R.id.valuespent);
            dates = itemView.findViewById(R.id.datespenditure);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }


}
