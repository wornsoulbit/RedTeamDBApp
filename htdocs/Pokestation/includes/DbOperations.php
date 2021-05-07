<?php 

    class DbOperation {
        private $connection;

        function __construct() {
            require_once dirname(__FILE__) . '/DbConnect.php';

            $db = new DbConnect();

            $this->connection = $db->connect();
        }

        function createIngredient($supplier_id, $order_day, $ingredient_name, $ingredient_type) {
            $stmt = $this->connection->prepare("INSERT INTO ingredient (supplier_id, order_day, ingredient_name, ingredient_type) 
                VALUES (?, ?, ?, ?)");
            $stmt->bind_param("isss", $supplier_id, $order_day, $ingredient_name, $ingredient_type);
            if ($stmt->execute()) 
                return true;
            return false;
        }

        function getIngredients() {
            $stmt = $this->connection->prepare("SELECT * FROM ingredient");
            $stmt->execute();
            $stmt->bind_result($ingredient_id, $supplier_id, $order_day, $ingredient_name, $ingredient_type);

            $ingredients = array();

            while ($stmt->fetch()) {
                $ingredient = array();
                $ingredient['ingredient_id'] = $ingredient_id;
                $ingredient['supplier_id'] = $supplier_id;
                $ingredient['order_day'] = $order_day;
                $ingredient['ingredient_name'] = $ingredient_name;
                $ingredient['ingredient_type'] = $ingredient_type;

                array_push($ingredients, $ingredient);
            }

            return $ingredients;
        }

        function updateIngredient($supplier_id, $order_day, $ingredient_name, $ingredient_type, $ingredient_id) {
            $stmt = $this->connection->prepare("UPDATE ingredient SET supplier_id = ?, order_day = ?, ingredient_name = ?, ingredient_type = ? WHERE ingredient_id = ?");
            $stmt->bind_param("isssi", $supplier_id, $order_day, $ingredient_name, $ingredient_type, $ingredient_id);
            if ($stmt->execute())
                return true;
            return false;
        }

        function deleteIngredient($ingredient_id) {
            $stmt = $this->connection->prepare("DELETE FROM ingredient WHERE ingredient_id = ?");
            $stmt->bind_param("i", $ingredient_id);
            if ($stmt->execute())
                return true;
            return false;
        }

        function createSupplier($supplier_name) {
            $stmt = $this->connection->prepare("INSERT INTO ingredient (supplier_name) VALUES (?)");
            $stmt->bind_param("s", $supplier_name);
            if ($stmt->execute()) 
                return true;
            return false;
        }

        function getSuppliers() {
            $stmt = $this->connection->prepare("SELECT * FROM supplier");
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
            $stmt = $this->connection->prepare("UPDATE supplier SET supplier_name = ? WHERE supplier_id = ?");
            $stmt->bind_param("si", $supplier_name, $supplier_id);
            if ($stmt->execute())
                return true;
            return false;
        }

        function deleteSupplier($supplier_id) {
            $stmt = $this->connection->prepare("DELETE FROM supplier WHERE supplier_id = ?");
            $stmt->bind_param("i", $supplier_id);
            if ($stmt->execute())
                return true;
            return false;
        }
    }

?>