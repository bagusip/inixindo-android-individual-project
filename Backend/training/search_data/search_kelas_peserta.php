<?php 

	$id_pst = $_GET['id_pst'];

	//Import File Koneksi Database
	require_once('../koneksi.php');
	
	//Membuat SQL Query
	$sql = "SELECT DISTINCT p.nama_pst , i.nama_ins, m.nama_mat , k.tgl_mulai_kls, k.tgl_akhir_kls
			FROM tb_detail_kelas dk
			JOIN tb_peserta p ON (dk.id_pst = p.id_pst)
			JOIN tb_kelas k ON (dk.id_kls = k.id_kls)
			JOIN tb_materi m ON (k.id_mat = m.id_mat)
			JOIN tb_instruktur i ON (k.id_ins = i.id_ins)
			WHERE p.id_pst = $id_pst
			ORDER BY 4;";
	
	//Mendapatkan Hasil
	$r = mysqli_query($con,$sql);
	
	//Membuat Array Kosong 
	$result = array();
	
	while($row = mysqli_fetch_array($r)){
		
		//Memasukkan Nama dan ID kedalam Array Kosong yang telah dibuat 
		array_push($result,array(
			"p.nama_pst"=>$row['nama_pst'],
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

