package com.example.pokestationapp.Models;

public class Ingredient {

    private int ingredient_id;
    private Days order_day;
    private String ingredient_name;
    private String ingredient_type;
    private int stock;
    private int amount_needed;


    public Ingredient(int ingredient_id, Days order_day, String ingredient_name, String ingredient_type, int stock, int amount_needed) {
        this.ingredient_id = ingredient_id;
        this.order_day = order_day;
        this.ingredient_name = ingredient_name;
        this.ingredient_type = ingredient_type;
        this.stock = stock;
        this.amount_needed = amount_needed;
    }

    public int getIngredient_id() {
        return ingredient_id;
    }

    /*public int getSupplier_id() {
        return supplier_id;
    }*/

    public Days getOrder_day() {
        return order_day;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public String getIngredient_type() {
        return ingredient_type;
    }

    public int getStock() {
        return stock;
    }

    public int getAmount_needed() {
        return amount_needed;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredient_id=" + ingredient_id +
                /*", supplier_id=" + supplier_id +*/
                ", order_day=" + order_day +
                ", ingredient_name='" + ingredient_name + '\'' +
                ", ingredient_type='" + ingredient_type + '\'' +
                '}';
    }
}
