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
    public static final String INSTRUKTUR_URL_GET_DETAIL = "http://192.168.1.3/training/instruktur/detail_instruktur.php?id_ins=";
    public static final String INSTRUKTUR_URL_GET_ADD = "http://192.168.1.3/training/instruktur/add_instruktur.php";
    public static final String INSTRUKTUR_URL_UPDATE = "http://192.168.1.3/training/instruktur/update_instruktur.php";
    public static final String INSTRUKTUR_URL_DELETE =    "http://192.168.1.3/training/instruktur/delete_instruktur.php?id_ins=";

    // MATERI
    public static final String MATERI_URL_GET_ALL = "http://192.168.1.3/training/materi/datas_materi.php";
    public static final String MATERI_URL_GET_DETAIL = "http://192.168.1.3/training/instruktur/detail_materi.php?id_mat=";
    public static final String MATERI_URL_GET_ADD = "http://192.168.1.3/training/materi/add_materi.php";
    public static final String MATERI_URL_UPDATE = "http://192.168.1.3/training/instruktur/update_materi.php";
    public static final String MATERI_URL_DELETE =    "http://192.168.1.3/training/instruktur/delete_materi.php?id_mat=";

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
