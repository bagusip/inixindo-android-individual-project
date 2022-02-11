package id.bagusip.inixindoprojectindividu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class TambahDetailKelas extends AppCompatActivity implements View.OnClickListener{
    EditText edit_id_detail_kls, edit_id_kls, edit_id_pst;
    Button btn_tambah_detail_kelas, btn_lihat_detail_kelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_detail_kelas);

        edit_id_detail_kls = findViewById(R.id.edit_id_detail_kls);
        edit_id_kls = findViewById(R.id.edit_id_kls);
        edit_id_pst = findViewById(R.id.edit_id_pst);

        btn_tambah_detail_kelas = findViewById(R.id.btn_tambah_detail_kelas);
        btn_lihat_detail_kelas = findViewById(R.id.btn_lihat_detail_kelas);

        btn_tambah_detail_kelas.setOnClickListener(this);
        btn_lihat_detail_kelas.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_tambah_detail_kelas:
                simpanDataDetailKelas();
                Intent myIntent = new Intent(TambahDetailKelas.this, MainActivity.class);
                myIntent.putExtra("keyName", "detail_kelas");
                startActivity(myIntent);
                break;
            case R.id.btn_lihat_detail_kelas:
                Intent i = new Intent(TambahDetailKelas.this, MainActivity.class);
                i.putExtra("keyName", "detail_kelas");
                startActivity(i);
                break;
        }
    }

    private void simpanDataDetailKelas() {
//        final String id_detail_kls = edit_id_detail_kls.getText().toString().trim();
        final String id_kls = edit_id_kls.getText().toString().trim();
        final String id_pst = edit_id_pst.getText().toString().trim();

        class SimpanDataKelas extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahDetailKelas.this,
                        "Menyimpan Data", "Harap Tunggu ...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                // params digunakan untuk meyimpan ke HttpHandler
                HashMap<String, String> params = new HashMap<>();
//                params.put("id_detail_kls", id_detail_kls);
                params.put("id_kls", id_kls);
                params.put("id_pst", id_pst);
                HttpHandler handler = new HttpHandler();
                // HttpHandler untuk kirim data pakai sendPostRequest
                String result = handler.sendPostRequest(Konfigurasi.DETAIL_KELAS_URL_GET_ADD, params);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TambahDetailKelas.this, "pesan:" + s,
                        Toast.LENGTH_SHORT).show();
                // method untuk clear setelah data ditambah di form
                clearText();
            }
        }
        SimpanDataKelas simpanDataKelas = new SimpanDataKelas();
        simpanDataKelas.execute();
    }

    private void clearText() {
        edit_id_kls.setText("");
        edit_id_pst.setText("");
        // untuk pointer langsung menuju kolom nama di layout
        edit_id_kls.requestFocus();
    }
}