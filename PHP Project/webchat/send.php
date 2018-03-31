<?php

session_start();

include'dbh.php';
$msg=$_POST['msg'];
$name=$_SESSION['name'];


$sql="insert into posts(msg,name) values('$msg','$name')";
$result=$conn->query($sql);

if($result){
header("Location:home.php");

}


?>