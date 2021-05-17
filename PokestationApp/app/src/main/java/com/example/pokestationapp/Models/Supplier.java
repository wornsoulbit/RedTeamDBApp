package com.example.pokestationapp.Models;

public class Supplier {

    private int supplier_id;
    private String supplier_name;

    public Supplier(int supplier_id, String supplier_name) {
        this.supplier_id = supplier_id;
        this.supplier_name = supplier_name;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplier_id=" + supplier_id +
                ", supplier_name='" + supplier_name + '\'' +
                '}';
    }
}
