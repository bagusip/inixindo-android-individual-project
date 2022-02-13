<?php 
	//Import File Koneksi Database
	require_once('../koneksi.php');
	
	//Membuat SQL Query
	// $sql = "SELECT k.id_kls, m.nama_mat, i.nama_ins, dk.id_detail_kls
	// 		FROM tb_kelas k
	// 		JOIN tb_instruktur i ON (k.id_ins = i.id_ins)
	// 		JOIN tb_materi m ON (k.id_mat = m.id_mat)
 //            JOIN tb_detail_kelas dk ON (k.id_kls = dk.id_kls)
	// 		ORDER BY 1;";

	$sql = "SELECT k.id_kls, i.nama_ins, m.nama_mat
		from tb_kelas k
		JOIN tb_instruktur i ON (k.id_ins = i.id_ins)
		JOIN tb_materi m ON (k.id_mat = m.id_mat)
		ORDER BY 1;";

	//Mendapatkan Hasil
	$r = mysqli_query($con,$sql);
	
	//Membuat Array Kosong 
	$result = array();
	
	while($row = mysqli_fetch_array($r)){
		
		//Memasukkan Nama dan ID kedalam Array Kosong yang telah dibuat 
		array_push($result,array(
			"k.id_kls"=>$row['id_kls'],
			"m.nama_mat"=>$row['nama_mat'],
            "i.nama_ins"=>$row['nama_ins']
		));
	}
	
	//Menampilkan Array dalam Format JSON
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
?>