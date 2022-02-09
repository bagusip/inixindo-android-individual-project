package id.bagusip.inixindoprojectindividu;

public class Konfigurasi {
    // PESERTA
    public static final String PESERTA_URL_GET_ALL = "http://192.168.1.3/training/peserta/datas_peserta.php";
    public static final String PESERTA_URL_GET_DETAIL = "http://192.168.1.3/training/peserta/detail_peserta.php?id_pst=";
    public static final String PESERTA_URL_GET_ADD = "http://192.168.1.3/training/peserta/add_peserta.php";
    public static final String PESERTA_URL_UPDATE = "http://192.168.1.3/training/peserta/update_peserta.php";
    public static final String PESERTA_URL_DELETE =    "http://192.168.1.3/training/peserta/delete_peserta.php?id_pst=";

    // INSTRUKTUR
    public static final String INSTRUKTUR_URL_GET_ALL = "http://192.168.1.3/training/instruktur/view_instruktur.php";
    public static final String INSTRUKTUR_URL_GET_DETAIL = "http://192.168.1.3/training/peserta/detail_peserta.php?id_pst=";
    public static final String INSTRUKTUR_URL_GET_ADD = "http://192.168.1.3/training/peserta/add_peserta.php";
    public static final String INSTRUKTUR_URL_UPDATE = "http://192.168.1.3/training/peserta/update_peserta.php";
    public static final String INSTRUKTUR_URL_DELETE =    "http://192.168.1.3/training/peserta/delete_peserta.php?id_pst=";

    // key adn value JSON yang muncul di browser
    public static final String KEY_PGW_ID = "id";
    public static final String KEY_PGW_NAMA = "name";
    public static final String KEY_PGW_JABATAN ="desg";
    public static final String KEY_PGW_GAJI ="salary";

    // flag JSON
    public static final String TAG_JSON_ARRAY = "result";
    public static final String TAG_JSON_ID = "id_pst";
    public static final String TAG_JSON_NAMA ="name";
    public static final String TAG_JSON_JABATAN ="desg";
    public static final String TAG_JSON_GAJI ="salary";

    // variabel alias ID Pegawai
    public static final String PGW_ID = "emp_id"; // Memberikan Alias
}
