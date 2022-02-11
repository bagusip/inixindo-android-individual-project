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

public class TambahMateri extends AppCompatActivity implements View.OnClickListener{
    EditText edit_nama_mat;
    Button btn_tambah_materi, btn_lihat_materi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_materi);

        edit_nama_mat = findViewById(R.id.edit_nama_mat);

        btn_tambah_materi = findViewById(R.id.btn_tambah_materi);
        btn_lihat_materi = findViewById(R.id.btn_lihat_materi);

        btn_tambah_materi.setOnClickListener(this);
        btn_lihat_materi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_tambah_materi:
                simpanDataMateri();
                Intent myIntent = new Intent(TambahMateri.this, MainActivity.class);
                myIntent.putExtra("keyName", "materi");
                startActivity(myIntent);
                break;
            case R.id.btn_lihat_materi:
                startActivity(new Intent(TambahMateri.this, MainActivity.class));
                Intent i = new Intent(TambahMateri.this, MainActivity.class);
                i.putExtra("keyName", "materi");
                startActivity(i);
                break;
        }
    }

    private void simpanDataMateri() {
        final String nama_mat = edit_nama_mat.getText().toString().trim();

        class SimpanDataInstruktur extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahMateri.this,
                        "Menyimpan Data", "Harap Tunggu ...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                // params digunakan untuk meyimpan ke HttpHandler
                HashMap<String, String> params = new HashMap<>();
                params.put("nama_mat", nama_mat);
                HttpHandler handler = new HttpHandler();
                // HttpHandler untuk kirim data pakai sendPostRequest
                String result = handler.sendPostRequest(Konfigurasi.MATERI_URL_GET_ADD, params);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TambahMateri.this, "pesan:" + s,
                        Toast.LENGTH_SHORT).show();
                // method untuk clear setelah data ditambah di form
                clearText();
            }
        }
        SimpanDataInstruktur simpanDataInstruktur = new SimpanDataInstruktur();
        simpanDataInstruktur.execute();

    }

    private void clearText() {
        edit_nama_mat.setText("");

        // untuk pointer langsung menuju kolom nama di layout
        edit_nama_mat.requestFocus();
    }
}