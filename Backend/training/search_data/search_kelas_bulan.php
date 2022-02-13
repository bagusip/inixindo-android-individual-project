<?php 

	$month = $_GET['month'];

	//Import File Koneksi Database
	require_once('../koneksi.php');
	
	//Membuat SQL Query
	$sql = "SELECT DISTINCT m.nama_mat, i.nama_ins, k.tgl_mulai_kls, k.tgl_akhir_kls
			FROM tb_kelas k
			JOIN tb_instruktur i ON (k.id_ins = i.id_ins)
			JOIN tb_materi m ON (k.id_mat = m.id_mat)
			WHERE MONTH(tgl_mulai_kls) = $month
			ORDER BY 3;";
	
	//Mendapatkan Hasil
	$r = mysqli_query($con,$sql);
	
	//Membuat Array Kosong 
	$result = array();
	
	while($row = mysqli_fetch_array($r)){
		
		//Memasukkan Nama dan ID kedalam Array Kosong yang telah dibuat 
		array_push($result,array(
			"m.nama_mat"=>$row['nama_mat'],
			"i.nama_ins"=>$row['nama_ins'],
			"k.tgl_mulai_kls"=>$row['tgl_mulai_kls'],
			"k.tgl_akhir_kls"=>$row['tgl_akhir_kls']
		));
	}
	
	//Menampilkan Array dalam Format JSON
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
?>

