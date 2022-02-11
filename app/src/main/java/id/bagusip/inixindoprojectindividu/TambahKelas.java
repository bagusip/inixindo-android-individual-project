package id.bagusip.inixindoprojectindividu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import id.bagusip.inixindoprojectindividu.databinding.ActivityMainBinding;

public class TambahKelas extends AppCompatActivity implements View.OnClickListener{
    private ActivityMainBinding binding;
    EditText edit_tgl_mulai_kls, edit_tgl_akhir_kls, edit_id_ins, edit_id_mat;
    Button btn_tambah_kelas, btn_lihat_kelas;
    Spinner spinner_mat, spinner_ins;
    private String JSON_STRING;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kelas);

        edit_tgl_mulai_kls = findViewById(R.id.edit_tgl_mulai_kls);
        edit_tgl_akhir_kls = findViewById(R.id.edit_tgl_akhir_kls);
        edit_id_ins = findViewById(R.id.edit_id_ins);
        edit_id_mat = findViewById(R.id.edit_id_mat);

        spinner_ins = findViewById(R.id.spinner_ins);
        spinner_mat = findViewById(R.id.spinner_mat);

        btn_tambah_kelas = findViewById(R.id.btn_tambah_kelas);
        btn_lihat_kelas = findViewById(R.id.btn_lihat_kelas);

        btn_tambah_kelas.setOnClickListener(this);
        btn_lihat_kelas.setOnClickListener(this);

        fetch_instruktur();
        fetch_materi();
    }

    private void fetch_instruktur() {
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(TambahKelas.this, "Getting Data", "Please wait...", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.INSTRUKTUR_URL_GET_ALL);
                Log.d("GetData", result);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();

                JSON_STRING = s;
                Log.d("Data_JSON", JSON_STRING);

                JSONObject jsonObject = null;
                ArrayList<String> arrayList = new ArrayList<>();

                try {
                    jsonObject = new JSONObject(JSON_STRING);
                    JSONArray jsonArray = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
                    Log.d("Data_JSON_LIST: ", String.valueOf(jsonArray));


                    for (int i=0;i<jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id = object.getString("id_ins");
                        String name = object.getString("nama_ins");

                        arrayList.add(id);
                        Log.d("DataArr: ", String.valueOf(id));
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TambahKelas.this, android.R.layout.simple_spinner_dropdown_item, arrayList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner_ins.setAdapter(adapter);
                //slct_spin = p_comp.getSelectedItem().toString();
                Log.d("spin", String.valueOf(arrayList));
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void fetch_materi() {
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(TambahKelas.this, "Getting Data", "Please wait...", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.MATERI_URL_GET_ALL);
                Log.d("GetData", result);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();

                JSON_STRING = s;
                Log.d("Data_JSON", JSON_STRING);

                JSONObject jsonObject = null;
                ArrayList<String> arrayList = new ArrayList<>();

                try {
                    jsonObject = new JSONObject(JSON_STRING);
                    JSONArray jsonArray = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
                    Log.d("Data_JSON_LIST: ", String.valueOf(jsonArray));


                    for (int i=0;i<jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id = object.getString("id_mat");
                        String name = object.getString("nama_mat");

                        arrayList.add(id);
                        Log.d("DataArr: ", String.valueOf(id));
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TambahKelas.this, android.R.layout.simple_spinner_dropdown_item, arrayList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner_mat.setAdapter(adapter);
                //slct_spin = p_comp.getSelectedItem().toString();
                Log.d("spin", String.valueOf(arrayList));
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_tambah_kelas:
                simpanDataKelas();
                Intent myIntent = new Intent(TambahKelas.this, MainActivity.class);
                myIntent.putExtra("keyName", "kelas");
                startActivity(myIntent);
                break;
            case R.id.btn_lihat_kelas:
                Intent i = new Intent(TambahKelas.this, MainActivity.class);
                i.putExtra("keyName", "kelas");
                startActivity(i);
                break;
        }
    }

    private void simpanDataKelas() {
        final String tgl_mulai_kls = edit_tgl_mulai_kls.getText().toString().trim();
        final String tgl_akhir_kls = edit_tgl_akhir_kls.getText().toString().trim();
        final String id_ins = spinner_ins.getSelectedItem().toString().trim();
        final String id_mat = spinner_mat.getSelectedItem().toString().trim();

        class SimpanDataKelas extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahKelas.this,
                        "Menyimpan Data", "Harap Tunggu ...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                // params digunakan untuk meyimpan ke HttpHandler
                HashMap<String, String> params = new HashMap<>();
                params.put("tgl_mulai_kls", tgl_mulai_kls);
                params.put("tgl_akhir_kls", tgl_akhir_kls);
                params.put("id_ins", id_ins);
                params.put("id_mat", id_mat);
                HttpHandler handler = new HttpHandler();
                // HttpHandler untuk kirim data pakai sendPostRequest
                String result = handler.sendPostRequest(Konfigurasi.KELAS_URL_GET_ADD, params);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TambahKelas.this, "pesan:" + s,
                        Toast.LENGTH_SHORT).show();
                // method untuk clear setelah data ditambah di form
                clearText();
            }
        }
        SimpanDataKelas simpanDataKelas = new SimpanDataKelas();
        simpanDataKelas.execute();

    }

    private void clearText() {
        edit_tgl_mulai_kls.setText("");
        edit_tgl_akhir_kls.setText("");
        edit_id_ins.setText("");
        edit_id_mat.setText("");
        // untuk pointer langsung menuju kolom nama di layout
        edit_tgl_mulai_kls.requestFocus();
    }
}