    <?php

        if ($_SERVER['REQUEST_METHOD'] == 'POST'){

            require_once("db.php");
            $name = $_POST['name'];
            $edad = $_POST['edad'];
            $password = $_POST['password'];
            $fechaRegistro = $_POST['fechaRegistro'];

            $query = "INSERT INTO users (name, edad, password, fechaRegistro) VALUES ('$name','$edad','$password','$fechaRegistro')";
            $result = $mysql->query($query);

            if ($result === TRUE){
                echo "the user was created Successfully";
            }else{
                echo "ERROR";
            }
            $mysql->close();
        }