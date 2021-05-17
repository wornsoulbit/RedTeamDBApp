package com.example.pokestationapp.Models;

public class Orders {

    private int order_id;
    private int supplier_id;
    private int ingredient_id;
    private int stock_id;
    private Days order_day;

    public Orders(int order_id, int supplier_id, int ingredient_id, int stock_id, Days order_day) {
        this.order_id = order_id;
        this.supplier_id = supplier_id;
        this.ingredient_id = ingredient_id;
        this.stock_id = stock_id;
        this.order_day = order_day;
    }

    public int getOrder_id() {
        return order_id;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public int getIngredient_id() {
        return ingredient_id;
    }

    public int getStock_id() {
        return stock_id;
    }

    public Days getOrder_day() {
        return order_day;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "order_id=" + order_id +
                ", supplier_id=" + supplier_id +
                ", ingredient_id=" + ingredient_id +
                ", stock_id=" + stock_id +
                ", order_day=" + order_day +
                '}';
    }
}
