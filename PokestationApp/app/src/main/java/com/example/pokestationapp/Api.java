package com.example.pokestationapp;

public class Api {

    private static final String ROOT_URL =  "http://192.168.2.13/Pokestation/v1/Api.php?apicall=";

    //Ingredients
    public static final String URL_CREATE_INGREDIENT = ROOT_URL + "createIngredient";
    public static final String URL_GET_INGREDIENTS = ROOT_URL + "getIngredients";
    public static final String URL_UPDATE_INGREDIENT = ROOT_URL + "updateIngredient";
    public static final String URL_DELETE_INGREDIENT = ROOT_URL + "deleteIngredient&id=";

    //Orders
    public static final String URL_CREATE_ORDER = ROOT_URL + "createOrder";
    public static final String URL_GET_ORDERS = ROOT_URL + "getOrders";
    public static final String URL_UPDATE_ORDER = ROOT_URL + "updateOrder";
    public static final String URL_DELETE_ORDER = ROOT_URL + "deleteOrder&id=";

    //Stock
    public static final String URL_CREATE_STOCK = ROOT_URL + "createStock";
    public static final String URL_GET_STOCKS = ROOT_URL + "getStocks";
    public static final String URL_UPDATE_STOCK = ROOT_URL + "updateStock";
    public static final String URL_DELETE_STOCK = ROOT_URL + "deleteStock&id=";

    //Suppliers
    public static final String URL_CREATE_SUPPLIER = ROOT_URL + "createSupplier";
    public static final String URL_GET_SUPPLIERS = ROOT_URL + "getSuppliers";
    public static final String URL_UPDATE_SUPPLIER = ROOT_URL + "updateSupplier";
    public static final String URL_DELETE_SUPPLIER = ROOT_URL + "deleteSupplier&id=";
}
