<?php 
 //Mendapatkan Nilai ID
 $id_ins = $_GET['id_ins'];
 
 //Import File Koneksi Database
 require_once('../koneksi.php');
 
 //Membuat SQL Query
 $sql = "DELETE FROM tb_instruktur WHERE id_ins=$id_ins;";

 
 //Menghapus Nilai pada Database 
 if(mysqli_query($con,$sql)){
 echo 'Berhasil Menghapus Instruktur';
 echo($sql);
 }else{
 echo 'Gagal Menghapus Instruktur';
 echo($con);
 echo($sql);
 }
 
 mysqli_close($con);
 ?>