package com.example.pokestationapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.example.pokestationapp.Models.Ingredient;

public class Ingredients_RecyclerViewAdapter extends RecyclerView.Adapter<Ingredients_RecyclerViewAdapter.ViewHolder>
{
    private ArrayList<Ingredient> ingredients;

    private LayoutInflater mInflator;
    private ItemClickListener mClickListener;

    public Ingredients_RecyclerViewAdapter(Context context, ArrayList<Ingredient> ingredients)
    {
        this.mInflator = LayoutInflater.from(context);
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflator.inflate(R.layout.ingredient_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);
        holder.ingredient_name.setText(ingredient.getIngredient_name());
        holder.ingredient_type.setText(ingredient.getIngredient_type());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView ingredient_name;
        TextView ingredient_type;
        TextView ingredient_stock;
        TextView ingredient_needed;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredient_name = itemView.findViewById(R.id.ingredient_name);
            ingredient_type = itemView.findViewById(R.id.ingredient_type);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mClickListener !=null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    Ingredient getItem(int id) {
        return ingredients.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick (View view, int position);
    }
}