package pt.iade.abhaykumarjosefranco.budgetbuddy.adapters;

import androidx.recyclerview.widget.RecyclerView;
import pt.iade.abhaykumarjosefranco.budgetbuddy.Models.BillItem;
import pt.iade.abhaykumarjosefranco.budgetbuddy.R;

import android.widget.TextView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public class BillItemRowAdapter extends RecyclerView.Adapter<BillItemRowAdapter.ViewHolder>{
    private ArrayList<BillItem> items;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;


    public BillItemRowAdapter(Context context, ArrayList<BillItem> items) {
        inflater = LayoutInflater.from(context);
        this.items = items;
        clickListener = null;
    }

    public void setOnClickListener(ItemClickListener listener) {
        clickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_bill_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BillItem item = items.get(position);

        holder.namebill.setText(item.getBill());
        holder.nameperiod.setText(item.getPeriod());
        holder.specification.setText(item.getPeriod());
        holder.values.setText(String.valueOf(item.getBillValue()));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView namebill;
        public TextView nameperiod;
        public TextView values, specification;


        public ViewHolder(View itemView) {
            super(itemView);

            namebill = itemView.findViewById(R.id.namedbill);
            nameperiod = itemView.findViewById(R.id.chosenperiod);
            values = itemView.findViewById(R.id.selectedvalue);
            specification = itemView.findViewById(R.id.typeBill);

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
