package com.example.pokestationapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokestationapp.Models.Ingredient;
import com.example.pokestationapp.Models.Orders;
import com.example.pokestationapp.Models.Supplier;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Orders_RecyclerViewAdapter extends RecyclerView.Adapter<Orders_RecyclerViewAdapter.ViewHolder> {

    private ArrayList<Ingredient> ingredients;

    private LayoutInflater mInflator;
    private ItemClickListener mClickListener;

    public Orders_RecyclerViewAdapter(Context context, ArrayList<Ingredient> ingredients)
    {
        this.mInflator = LayoutInflater.from(context);
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public Orders_RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflator.inflate(R.layout.order_item, parent, false);
        return new Orders_RecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Orders_RecyclerViewAdapter.ViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);
        holder.order_ingredient_name.setText(ingredient.getIngredient_name());
        holder.order_ingredient_date.setText(ingredient.getOrder_day().toString()+"");
        holder.order_amount_needed.setText(ingredient.getAmount_needed()+"");
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView order_amount_needed;
        TextView order_ingredient_date;
        TextView order_ingredient_name;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            order_amount_needed = itemView.findViewById(R.id.ingredient_needed);
            order_ingredient_name = itemView.findViewById(R.id.ingredient_name);
            order_ingredient_date = itemView.findViewById(R.id.ingredient_date);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mClickListener !=null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    Ingredient getItem(int id) { return ingredients.get(id); }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick (View view, int position);
    }
}
