<?php

    if ($_SERVER['REQUEST_METHOD'] == 'POST'){

        require_once("db.php");
        $id = $_POST['id'];

        $query = "DELETE FROM users WHERE id = '$id'";
        $result = $mysql->query($query);

        if ($mysql->affected_rows > 0){
            if($result === TRUE){
                echo "the user was removed Successfully";
            }
        }else{
            echo "not found any rows";
        }

        $mysql->close();

    }