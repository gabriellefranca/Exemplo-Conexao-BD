<?php
 
 if($_SERVER['REQUEST_METHOD']=='POST'){

 $username = $_POST['username'];
 $password = $_POST['password'];
 $senhamd5=md5($password);
 
 include "Banco/conexao.php"; 
 
 $sql = "SELECT * FROM Usuarios_tb WHERE usuario = '$username' AND senha='$senhamd5'";
	$conferir = $conex -> prepare($sql);
	$conferir ->  execute();
	$verifica=$conferir->rowCount();
	if($verifica>0){
 echo 'successo';
	}else{
		echo 'erro';
	}

 
 }
