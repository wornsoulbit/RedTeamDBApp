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

import com.example.pokestationapp.Controllers.Api;
import com.example.pokestationapp.Controllers.JsonParse;
import com.example.pokestationapp.Controllers.PerformNetworkRequest;
import com.example.pokestationapp.Models.Ingredient;
import com.example.pokestationapp.Models.Orders;
import com.example.pokestationapp.Models.Supplier;

import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Orders_RecyclerViewAdapter extends RecyclerView.Adapter<Orders_RecyclerViewAdapter.ViewHolder> {

    private ArrayList<Ingredient> ingredients;
    private ArrayList<Orders> orders;

    private LayoutInflater mInflator;
    private ItemClickListener mClickListener;
    private Context mContext;

    public Orders_RecyclerViewAdapter(Context context, ArrayList<Ingredient> ingredients, ArrayList<Orders> orders)
    {
        this.mInflator = LayoutInflater.from(context);
        this.ingredients = ingredients;
        this.orders = orders;
        this.mContext = context;
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
        holder.order_amount_needed.setText(ingredient.getAmount_needed() - ingredient.getStock() +"");
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView order_amount_needed;
        TextView order_ingredient_date;
        TextView order_ingredient_name;
        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            order_amount_needed = itemView.findViewById(R.id.ingredient_needed);
            order_ingredient_name = itemView.findViewById(R.id.ingredient_name);
            order_ingredient_date = itemView.findViewById(R.id.ingredient_date);

            itemView.setOnClickListener(this);

            itemView.findViewById(R.id.deleteOrder_button).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int order_id = 1;

                    for(int i = 0; i < orders.size(); i++)
                    {
                        if(ingredients.get(position).getIngredient_id() == orders.get(i).getIngredient_id())
                        {
                            order_id = orders.get(i).getOrder_id();
                        }
                    }

                    PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_DELETE_ORDER + order_id, null, 1025);
                    while (!request.getResult().isDone())
                    {
//                        try {
//                            Log.e("test", JsonParse.getResponseArr(request.getResult().get(), request.getRequestCode()).toString());
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        } catch (ExecutionException e) {
//                            e.printStackTrace();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                        Toast.makeText(mContext, "Order Deleted", Toast.LENGTH_SHORT).show();
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

    Ingredient getItem(int id) { return ingredients.get(id); }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick (View view, int position);
    }
}
