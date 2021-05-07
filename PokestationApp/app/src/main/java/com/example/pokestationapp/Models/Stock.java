package com.example.pokestationapp.Models;

public class Stock {

    private int stock_id;
    private int ingredient_id;
    private int in_stock;
    private int amount_needed;

    public Stock(int stock_id, int ingredient_id, int in_stock, int amount_needed) {
        this.stock_id = stock_id;
        this.ingredient_id = ingredient_id;
        this.in_stock = in_stock;
        this.amount_needed = amount_needed;
    }

    public int getStock_id() {
        return stock_id;
    }

    public int getIngredient_id() {
        return ingredient_id;
    }

    public int getIn_stock() {
        return in_stock;
    }

    public int getAmount_needed() {
        return amount_needed;
    }
}
