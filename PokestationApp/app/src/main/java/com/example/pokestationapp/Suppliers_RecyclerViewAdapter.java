package com.example.pokestationapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.example.pokestationapp.Controllers.Api;
import com.example.pokestationapp.Controllers.JsonParse;
import com.example.pokestationapp.Controllers.PerformNetworkRequest;
import com.example.pokestationapp.Models.Ingredient;
import com.example.pokestationapp.Models.Supplier;

public class Suppliers_RecyclerViewAdapter extends RecyclerView.Adapter<Suppliers_RecyclerViewAdapter.ViewHolder>
{
    private ArrayList<Supplier> suppliers;

    private LayoutInflater mInflator;
    private ItemClickListener mClickListener;
    private Context mContext;

    public Suppliers_RecyclerViewAdapter(Context context, ArrayList<Supplier> suppliers)
    {
        this.mInflator = LayoutInflater.from(context);
        this.suppliers = suppliers;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflator.inflate(R.layout.supplier_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Supplier supplier = suppliers.get(position);
        holder.supplier_name.setText(supplier.getSupplier_name());
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return suppliers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView supplier_name;
        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            supplier_name = itemView.findViewById(R.id.supplier_name);

            itemView.setOnClickListener(this);

            itemView.findViewById(R.id.deleteSupplier_button).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_DELETE_SUPPLIER + suppliers.get(position).getSupplier_id(), null, 1024);
                    while (!request.getResult().isDone())
                    {
                        Toast.makeText(mContext, "Supplier Deleted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext, Orders_Main.class);
                        mContext.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public void onClick(View view) {
            if(mClickListener !=null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    Supplier getItem(int id) { return suppliers.get(id); }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick (View view, int position);
    }
}