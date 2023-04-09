<?php

    $mysql = new mysqli(
        "localhost",
        "root",
        "",
        "android"
    );

    if($mysql->connect_error){
        die("Failed to connect" . $mysql->connect_error);
    }else{
        echo "";
    }