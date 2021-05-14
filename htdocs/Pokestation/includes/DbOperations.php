<?php

class DbOperation {
    private $connection;

    function __construct() {
        require_once dirname(__FILE__) . '/DbConnect.php';

        $db = new DbConnect();

        $this->connection = $db->connect();
    }

    function createIngredient($order_day, $ingredient_name, $ingredient_type, $stock, $amount_needed) {
        $stmt = $this
            ->connection
            ->prepare("INSERT INTO ingredient (supplier_id, order_day, ingredient_name, ingredient_type) 
                VALUES (?, ?, ?, ?)");
        $stmt->bind_param("sssii", $order_day, $ingredient_name, $ingredient_type, $stock, $amount_needed);
        if ($stmt->execute()) return true;
        return false;
    }

    function getIngredients() {
        $stmt = $this
            ->connection
            ->prepare("SELECT * FROM ingredient");
        $stmt->execute();
        $stmt->bind_result($ingredient_id, $order_day, $ingredient_name, $ingredient_type, $stock, $amount_needed);

        $ingredients = array();

        while ($stmt->fetch()) {
            $ingredient = array();
            $ingredient['ingredient_id'] = $ingredient_id;
            $ingredient['order_day'] = $order_day;
            $ingredient['ingredient_name'] = $ingredient_name;
            $ingredient['ingredient_type'] = $ingredient_type;
            $ingredient['stock'] = $stock;
            $ingredient['amount_needed'] = $amount_needed;

            array_push($ingredients, $ingredient);
        }

        return $ingredients;
    }

    function updateIngredient($ingredient_id, $order_day, $ingredient_name, $ingredient_type, $stock, $amount_needed) {
        $stmt = $this
            ->connection
            ->prepare("UPDATE ingredient SET order_day = ?, ingredient_name = ?, ingredient_type = ?, stock = ?, 
            amount_needed = ? WHERE ingredient_id = ?");

        $stmt->bind_param("sssiii", $order_day, $ingredient_name, $ingredient_type, $stock, $amount_needed, $ingredient_id);
        if ($stmt->execute()) return true;
        return false;
    }

    function deleteIngredient($ingredient_id) {
        $stmt = $this
            ->connection
            ->prepare("DELETE FROM ingredient WHERE ingredient_id = ?");
        $stmt->bind_param("i", $ingredient_id);
        if ($stmt->execute()) return true;
        return false;
    }

    function createSupplier($supplier_name) {
        $stmt = $this
            ->connection
            ->prepare("INSERT INTO ingredient (supplier_name) VALUES (?)");
        $stmt->bind_param("s", $supplier_name);
        if ($stmt->execute()) return true;
        return false;
    }

    function getSuppliers() {
        $stmt = $this
            ->connection
            ->prepare("SELECT * FROM supplier");
        $stmt->execute();
        $stmt->bind_result($supplier_id, $supplier_name);

        $suppliers = array();

        while ($stmt->fetch()) {
            $supplier = array();
            $supplier['supplier_id'] = $supplier_id;
            $supplier['supplier_name'] = $supplier_name;

            array_push($suppliers, $supplier);
        }

        return $suppliers;
    }

    function updateSupplier($supplier_id, $supplier_name) {
        $stmt = $this
            ->connection
            ->prepare("UPDATE supplier SET supplier_name = ? WHERE supplier_id = ?");
        $stmt->bind_param("si", $supplier_name, $supplier_id);
        if ($stmt->execute()) return true;
        return false;
    }

    function deleteSupplier($supplier_id) {
        $stmt = $this
            ->connection
            ->prepare("DELETE FROM supplier WHERE supplier_id = ?");
        $stmt->bind_param("i", $supplier_id);
        if ($stmt->execute()) return true;
        return false;
    }

    function createOrder($supplier_id, $ingredient_id, $stock_id, $order_day) {
        $stmt = $this
            ->connection
            ->prepare("INSERT INTO orders (supplier_id, ingredient_id, stock_id, order_day) VALUES (?, ?, ?, ?)");
        $stmt->bind_param("iiis", $supplier_id, $ingredient_id, $stock_id, $order_day);
        if ($stmt->execute()) return true;
        return false;
    }

    function getOrders() {
        $stmt = $this
            ->connection
            ->prepare("SELECT * FROM orders");
        $stmt->execute();
        $stmt->bind_result($order_id, $supplier_id, $ingredient_id, $stock_id, $order_day);

        $orders = array();

        while ($stmt->fetch()) {
            $order = array();
            $order['order_id'] = $order_id;
            $order['supplier_id'] = $supplier_id;
            $order['ingredient_id'] = $ingredient_id;
            $order['stock_id'] = $stock_id;
            $order['order_day'] = $order_day;

            array_push($orders, $order);
        }

        return $orders;
    }

    function updateOrder($order_id, $supplier_id, $ingredient_id, $stock_id, $order_day) {
        $stmt = $this
            ->connection
            ->prepare("UPDATE orders SET supplier_id = ?, ingredient_id = ?, stock_id = ?, order_day = ?
                WHERE order_id = ?");

        $stmt->bind_param("isssi", $supplier_id, $ingredient_id, $stock_id, $order_day, $order_id);
        if ($stmt->execute()) return true;
        return false;
    }

    function deleteOrder($order_id) {
        $stmt = $this
            ->connection
            ->prepare("DELETE FROM orders WHERE order_id = ?");
        $stmt->bind_param("i", $order_id);
        if ($stmt->execute()) return true;
        return false;
    }

    function createStock($ingredient_id, $in_stock, $amount_needed) {
        $stmt = $this
            ->connection
            ->prepare("INSERT INTO stock (ingredient_id, in_stock, amount_needed) VALUES (?, ?, ?)");
        $stmt->bind_param("iii", $ingredient_id, $in_stock, $amount_needed);
        if ($stmt->execute()) return true;
        return false;
    }

    function getStocks() {
        $stmt = $this
            ->connection
            ->prepare("SELECT * FROM stock");
        $stmt->execute();
        $stmt->bind_result($stock_id, $ingredient_id, $in_stock, $amount_needed);

        $orders = array();

        while ($stmt->fetch()) {
            $order = array();
            $order['stock_id'] = $stock_id;
            $order['ingredient_id'] = $ingredient_id;
            $order['in_stock'] = $in_stock;
            $order['amount_needed'] = $amount_needed;

            array_push($orders, $order);
        }

        return $orders;
    }

    function updateStock($stock_id, $ingredient_id, $in_stock, $amount_needed) {
        $stmt = $this
            ->connection
            ->prepare("UPDATE stock SET ingredient_id = ?, in_stock = ?, amount_needed = ?
                WHERE stock_id = ?");

        $stmt->bind_param("iiii", $ingredient_id, $in_stock, $amount_needed, $stock_id);
        if ($stmt->execute()) return true;
        return false;
    }

    function deleteStock($stock_id) {
        $stmt = $this
            ->connection
            ->prepare("DELETE FROM stock WHERE stock_id = ?");
        $stmt->bind_param("i", $stock_id);
        if ($stmt->execute()) return true;
        return false;
    }
}

?>
