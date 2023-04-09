<?php
    if ($_SERVER['REQUEST_METHOD'] == 'POST'){

        require_once("db.php");

        $id = $_POST['id'];
        $name = $_POST['name'];
        $edad = $_POST['edad'];
        $password = $_POST['password'];
        $fechaRegistro = $_POST['fechaRegistro'];

        $query = "UPDATE users SET name = '$name', edad ='$edad', password = '$password', fechaRegistro = '$fechaRegistro' WHERE id = '$id'";
        $result = $mysql->query($query);
        
        if ($mysql->affected_rows > 0){
            if ($result === TRUE){
                echo "Update successfully";
            }else{
                echo "Error";
            }
        }else{
            echo "Not found any rows";
        }

        $mysql->close();
    }