<?php
/*
start local php:
php -S localhost:8083 -t ./
*/
require "DataBase.php";
require "models/Response.php";
$db = new DataBase();
$message = "";
$status = "";
if (isset($_POST['username']) && isset($_POST['password'])) {
    if ($db->dbConnect()) {
        if ($db->logIn("users", $_POST['username'], $_POST['password'])) {
            $message = "Login user successfully";
            $status = Response::$SUCCESS;
        } else {
            $message = "Username or Password wrong";
            $status = Response::$FAILED;
        }
    } else {        
        $message = "Error: Database connection";
        $status = Response::$FAILED;
    }
} else {    
    $message = "All fields are required";
    $status = Response::$FAILED;
}
$response = new Response(
        Response::$SUCCESS,         
        NULL, 
        $message
);
echo json_encode($response);

?>
