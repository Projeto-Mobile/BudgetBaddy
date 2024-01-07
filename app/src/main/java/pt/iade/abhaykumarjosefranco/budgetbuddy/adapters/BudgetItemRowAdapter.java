package pt.iade.abhaykumarjosefranco.budgetbuddy.adapters;

import pt.iade.abhaykumarjosefranco.budgetbuddy.R;
import java.time.format.DateTimeFormatter;


import android.widget.TextView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.BudgetItem;

public class BudgetItemRowAdapter extends RecyclerView.Adapter<BudgetItemRowAdapter.ViewHolder> {
    private ArrayList<BudgetItem> items;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;


    public BudgetItemRowAdapter(Context context, ArrayList<BudgetItem> items) {
        inflater = LayoutInflater.from(context);
        this.items = items;
        clickListener = null;
    }

    public void setOnClickListener(ItemClickListener listener) {
        clickListener = listener;
    }

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_budget_item, parent, false);
        return new ViewHolder(view);
    }

    //TODO: ID ?????
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BudgetItem item = items.get(position);

        holder.namecategory.setText(item.getCategory());

        holder.startperiod.setText(item.getDatestart().format(dateFormatter));
        holder.endperiod.setText(item.getDateend().format(dateFormatter));
        holder.specificationBudget.setText(item.getType());


        holder.thevalue.setText(String.valueOf(item.getBudgetValue()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView namecategory;
        public TextView startperiod, endperiod;
        public TextView thevalue, specificationBudget;


        public ViewHolder(View itemView) {
            super(itemView);

            namecategory = itemView.findViewById(R.id.spanditure);
            startperiod = itemView.findViewById(R.id.startperiod);
            endperiod = itemView.findViewById(R.id.endperiod);
            thevalue = itemView.findViewById(R.id.givenvalue);
            specificationBudget = itemView.findViewById(R.id.typeCategory);

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
