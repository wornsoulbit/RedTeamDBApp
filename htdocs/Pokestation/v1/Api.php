<?php
    require_once '../includes/DbOperations.php';

    function isTheseParamsAvailable($params) {
        $available = true;
        $missingparams = "";

        foreach ($params as $param) {
            if (!isset($_POST[$param]) || strlen($_POST[$param]) <= 0) {
                $available = false;
                $missingparams = $missingparams . ", " . $param;
            }
        }

        if (!$available) {
            $response = array();
            $response['error'] = true;
            $response['message'] = 'Parameters' . substr($missingparams, 1, strlen($missingparams)) . ' missing';

            echo json_encode($response);

            die();
        }
    }

    $response = array();

    if (isset($_GET['apicall'])) {
        switch ($_GET['apicall']) {

            case "createSupplier":
                isTheseParamsAvailable(array(
                    "supplier_name"
                ));

                $db = new DbOperation();

                $result = $db->createSupplier($_POST['supplier_name']);

                if ($result) {
                    $response['error'] = false;
                    $response['message'] = "Supplier added successfully";
                    $response['supplier'] = $db->getSuppliers();
                }
                else {
                    $response['error'] = true;
                    $response['message'] = "Something went wrong";
                }
            break;

            case "getSuppliers":
                $db = new DbOperation();
                $response['error'] = false;
                $response['message'] = "Request completed";
                $response['suppliers'] = $db->getSuppliers();
            break;

            case "updateSupplier":
                isTheseParamsAvailable(array(
                    "supplier_id",
                    "supplier_name"
                ));
                $db = new DbOperation();

                $result = $db->updateSupplier($_POST['supplier_id'], $_POST['supplier_name']);

                if ($result) {
                    $response['error'] = false;
                    $response['message'] = 'Supplier updated successfully';
                    $response['suppliers'] = $db->getSuppliers();
                }
                else {
                    $response['error'] = true;
                    $response['message'] = 'Some error occurred please try again';
                }
            break;

            case "deleteSupplier":

                if (isset($_GET['supplier_id'])) {
                    $db = new DbOperation();

                    if ($db->deleteSupplier($_GET['supplier_id'])) {
                        $response['error'] = false;
                        $response['message'] = "Supplier successfully deleted";
                        $response['supplier'] = $db->getSuppliers();
                    }
                    else {
                        $response['error'] = true;
                        $response['message'] = "Something went wrong";
                    }
                }
                else {
                    $response['error'] = true;
                    $response['message'] = "Nothing to delete, provide a supplier_id please";
                }

            break;

            case "createIngredient":
                isTheseParamsAvailable(array(
                    "supplier_id",
                    "order_day",
                    "ingredient_name",
                    "ingredient_type"
                ));

                $db = new DbOperation();

                $result = $db->createIngredient($_POST['supplier_id'], $_POST['order_day'], $_POST['ingredient_name'], $_POST['ingredient_type']);

                if ($result) {
                    $response['error'] = false;
                    $response['message'] = "Ingredient added successfully";
                    $response['ingredients'] = $db->getIngredients();
                }
                else {
                    $response['error'] = true;
                    $response['message'] = "Something went wrong";
                }
            break;

            case "getIngredients":
                $db = new DbOperation();
                $response['error'] = false;
                $response['message'] = "Request completed";
                $response['ingredients'] = $db->getIngredients();
            break;

            case "updateIngredient":
                isTheseParamsAvailable(array(
                    "ingredient_id",
                    "supplier_id",
                    "order_day",
                    "ingredient_name",
                    "ingredient_type"
                ));
                // var_dump($_POST);
                $db = new DbOperation();

                $result = $db->updateIngredient($_POST['ingredient_id'], $_POST['supplier_id'], $_POST['order_day'], $_POST['ingredient_name'], $_POST['ingredient_type']);

                if ($result) {
                    $response['error'] = false;
                    $response['message'] = 'Ingredient updated successfully';
                    $response['ingredients'] = $db->getIngredients();
                }
                else {
                    $response['error'] = true;
                    $response['message'] = 'Some error occurred please try again';
                }
            break;

            case "deleteIngredient":

                if (isset($_GET['ingredient_id'])) {
                    $db = new DbOperation();

                    if ($db->deleteIngredient($_GET['ingredient_id'])) {
                        $response['error'] = false;
                        $response['message'] = "Ingredient successfully deleted";
                        $response['ingredients'] = $db->getIngredients();
                    }
                    else {
                        $response['error'] = true;
                        $response['message'] = "Something went wrong";
                    }
                }
                else {
                    $response['error'] = true;
                    $response['message'] = "Nothing to delete, provide a ingredient_id please";
                }

            break;

            case "createOrder":
                isTheseParamsAvailable(array(
                    "supplier_id",
                    "ingredient_id",
                    "stock_id",
                    "order_day"
                ));

                $db = new DbOperation();

                $result = $db->createOrder($_POST['supplier_id'], $_POST['ingredient_id'], $_POST['stock_id'], $_POST['order_day']);

                if ($result) {
                    $response['error'] = false;
                    $response['message'] = "Order added successfully";
                    $response['order'] = $db->getOrders();
                }
                else {
                    $response['error'] = true;
                    $response['message'] = "Something went wrong";
                }
            break;

            case "getOrders":
                $db = new DbOperation();
                $response['error'] = false;
                $response['message'] = "Request completed";
                $response['order'] = $db->getOrders();
            break;

            case "updateOrder":
                isTheseParamsAvailable(array(
                    "order_id",
                    "supplier_id",
                    "ingredient_id",
                    "stock_id",
                    "order_day"
                ));
                $db = new DbOperation();

                $result = $db->updateOrder($_POST['order_id'], $_POST['supplier_id'], $_POST['ingredient_id'], $_POST['stock_id'], $_POST['order_day']);

                if ($result) {
                    $response['error'] = false;
                    $response['message'] = 'Order updated successfully';
                    $response['order'] = $db->getOrders();
                }
                else {
                    $response['error'] = true;
                    $response['message'] = 'Some error occurred please try again';
                }
            break;

            case "deleteOrders":

                if (isset($_GET['order_id'])) {
                    $db = new DbOperation();

                    if ($db->deleteOrder($_GET['order_id'])) {
                        $response['error'] = false;
                        $response['message'] = "Order successfully deleted";
                        $response['order'] = $db->getOrders();
                    }
                    else {
                        $response['error'] = true;
                        $response['message'] = "Something went wrong";
                    }
                }
                else {
                    $response['error'] = true;
                    $response['message'] = "Nothing to delete, provide a order_id please";
                }

            break;

            case "createStock":
                isTheseParamsAvailable(array(
                    "ingredient_id",
                    "in_stock",
                    "amount_needed"
                ));

                $db = new DbOperation();

                $result = $db->createStock($_POST['ingredient_id'], $_POST['in_stock'], $_POST['amount_needed']);

                if ($result) {
                    $response['error'] = false;
                    $response['message'] = "Stock added successfully";
                    $response['order'] = $db->getStocks();
                }
                else {
                    $response['error'] = true;
                    $response['message'] = "Something went wrong";
                }
            break;

            case "getStocks":
                $db = new DbOperation();
                $response['error'] = false;
                $response['message'] = "Request completed";
                $response['order'] = $db->getStocks();
            break;

            case "updateStock":
                isTheseParamsAvailable(array(
                    "stock_id",
                    "ingredient_id",
                    "in_stock",
                    "amount_needed"
                ));
                $db = new DbOperation();

                $result = $db->updateStock($_POST['stock_id'], $_POST['ingredient_id'], $_POST['in_stock'], $_POST['amount_needed']);

                if ($result) {
                    $response['error'] = false;
                    $response['message'] = 'Stock updated successfully';
                    $response['order'] = $db->getStocks();
                }
                else {
                    $response['error'] = true;
                    $response['message'] = 'Some error occurred please try again';
                }
            break;

            case "deleteStock":

                if (isset($_GET['stock_id'])) {
                    $db = new DbOperation();

                    if ($db->deleteOrder($_GET['stock_id'])) {
                        $response['error'] = false;
                        $response['message'] = "Stock successfully deleted";
                        $response['order'] = $db->getStocks();
                    }
                    else {
                        $response['error'] = true;
                        $response['message'] = "Something went wrong";
                    }
                }
                else {
                    $response['error'] = true;
                    $response['message'] = "Nothing to delete, provide a stock_id please";
                }

            break;
        }

    }
    else {
        $response['error'] = true;
        $response['message'] = "Invalid API Call";
    }

    echo json_encode($response);

?>
