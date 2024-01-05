package pt.iade.abhaykumarjosefranco.budgetbuddy.adapters;
import pt.iade.abhaykumarjosefranco.budgetbuddy.R;

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
        holder.nameperiod.setText(item.getPeriod());
        holder.specificationBudget.setText(item.getPeriod());
        holder.thevalue.setText(String.valueOf(item.getBudgetValue()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView namecategory;
        public TextView nameperiod;
        public TextView thevalue, specificationBudget;


        public ViewHolder(View itemView) {
            super(itemView);

            namecategory = itemView.findViewById(R.id.spanditure);
            nameperiod = itemView.findViewById(R.id.namedperiod);
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
