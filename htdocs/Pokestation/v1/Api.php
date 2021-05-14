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
                $response['response'] = $db->getSuppliers();
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
                    "order_day",
                    "ingredient_name",
                    "ingredient_type",
                    "stock",
                    "amount_needed"
                ));

                $db = new DbOperation();

                $result = $db->createIngredient($_POST['supplier_id'], $_POST['order_day'], $_POST['ingredient_name'], $_POST['ingredient_type']);

                if ($result) {
                    $response['error'] = false;
                    $response['message'] = "Ingredient added successfully";
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
                $response['response'] = $db->getIngredients();
            break;

            case "updateIngredient":
                isTheseParamsAvailable(array(
                    "ingredient_id",
                    "order_day",
                    "ingredient_name",
                    "ingredient_type",
                    "stock",
                    "amount_needed"
                ));
                // var_dump($_POST);
                $db = new DbOperation();

                $result = $db->updateIngredient($_POST['ingredient_id'], $_POST['supplier_id'], $_POST['order_day'], $_POST['ingredient_name'], $_POST['ingredient_type']);

                if ($result) {
                    $response['error'] = false;
                    $response['message'] = 'Ingredient updated successfully';
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
                $response['response'] = $db->getOrders();
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
                $response['response'] = $db->getStocks();
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
