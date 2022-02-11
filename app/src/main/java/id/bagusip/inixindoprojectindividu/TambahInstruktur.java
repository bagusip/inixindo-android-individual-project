package id.bagusip.inixindoprojectindividu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class TambahInstruktur extends AppCompatActivity implements View.OnClickListener {
    EditText edit_nama_ins, edit_email_ins, edit_no_hp_ins;
    Button btn_tambah_instruktur, btn_lihat_instruktur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_instruktur);

        edit_nama_ins = findViewById(R.id.edit_nama_ins);
        edit_email_ins= findViewById(R.id.edit_email_ins);
        edit_no_hp_ins = findViewById(R.id.edit_hp_ins);

        btn_tambah_instruktur = findViewById(R.id.btn_tambah_instruktur);
        btn_lihat_instruktur = findViewById(R.id.btn_lihat_instruktur);

        btn_tambah_instruktur.setOnClickListener(this);
        btn_lihat_instruktur.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_tambah_instruktur:
                simpanDataInstruktur();
                Intent myIntent = new Intent(TambahInstruktur.this, MainActivity.class);
                myIntent.putExtra("keyName", "instruktur");
                startActivity(myIntent);
                break;
            case R.id.btn_lihat_instruktur:
                Intent i = new Intent(TambahInstruktur.this, MainActivity.class);
                i.putExtra("keyName", "instruktur");
                startActivity(i);
                break;
        }
    }

    private void simpanDataInstruktur() {
        final String nama_ins = edit_nama_ins.getText().toString().trim();
        final String email_ins = edit_email_ins.getText().toString().trim();
        final String hp_ins = edit_no_hp_ins.getText().toString().trim();

        class SimpanDataInstruktur extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahInstruktur.this,
                        "Menyimpan Data", "Harap Tunggu ...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                // params digunakan untuk meyimpan ke HttpHandler
                HashMap<String, String> params = new HashMap<>();
                params.put("nama_ins", nama_ins);
                params.put("email_ins", email_ins);
                params.put("hp_ins", hp_ins);
                HttpHandler handler = new HttpHandler();
                // HttpHandler untuk kirim data pakai sendPostRequest
                String result = handler.sendPostRequest(Konfigurasi.INSTRUKTUR_URL_GET_ADD, params);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TambahInstruktur.this, "pesan:" + s,
                        Toast.LENGTH_SHORT).show();
                // method untuk clear setelah data ditambah di form
                clearText();
            }
        }
        SimpanDataInstruktur simpanDataInstruktur = new SimpanDataInstruktur();
        simpanDataInstruktur.execute();

    }

    private void clearText() {
        edit_nama_ins.setText("");
        edit_email_ins.setText("");
        edit_no_hp_ins.setText("");
        // untuk pointer langsung menuju kolom nama di layout
        edit_nama_ins.requestFocus();
    }
}