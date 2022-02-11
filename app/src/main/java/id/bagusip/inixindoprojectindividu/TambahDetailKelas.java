package id.bagusip.inixindoprojectindividu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TambahDetailKelas extends AppCompatActivity implements View.OnClickListener{
    EditText edit_id_detail_kls, edit_id_kls, edit_id_pst;
    Button btn_tambah_detail_kelas, btn_lihat_detail_kelas;
    Spinner spinner_kls,spinner_pst;
    private int spinner_val_kls, spinner_val_pst;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_detail_kelas);

        edit_id_detail_kls = findViewById(R.id.edit_id_detail_kls);
        edit_id_kls = findViewById(R.id.edit_id_kls);
        edit_id_pst = findViewById(R.id.edit_id_pst);

        spinner_kls = findViewById(R.id.spinner_kls);
        spinner_pst = findViewById(R.id.spinner_pst);

        btn_tambah_detail_kelas = findViewById(R.id.btn_tambah_detail_kelas);
        btn_lihat_detail_kelas = findViewById(R.id.btn_lihat_detail_kelas);

        btn_tambah_detail_kelas.setOnClickListener(this);
        btn_lihat_detail_kelas.setOnClickListener(this);
        
        fetch_kelas();
        fetch_peserta();
    }

    private void fetch_kelas() {
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(TambahDetailKelas.this, "Getting Data", "Please wait...", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.KELAS_URL_GET_ALL);
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
                ArrayList<String> listID = new ArrayList<>();
                ArrayList<String> listNama = new ArrayList<>();

                try {
                    jsonObject = new JSONObject(JSON_STRING);
                    JSONArray jsonArray = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
                    Log.d("Data_JSON_LIST: ", String.valueOf(jsonArray));


                    for (int i=0;i<jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id = object.getString("k.id_kls");

                        listID.add(id);
                        Log.d("id: ", String.valueOf(id));
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TambahDetailKelas.this, android.R.layout.simple_spinner_dropdown_item, listID);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner_kls.setAdapter(adapter);

                spinner_kls.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        spinner_val_kls = Integer.parseInt(listID.get(i));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                spinner_kls.setAdapter(adapter);

                Log.d("spin", String.valueOf(arrayList));
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }


    private void fetch_peserta() {
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(TambahDetailKelas.this, "Getting Data", "Please wait...", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.PESERTA_URL_GET_ALL);
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
                ArrayList<String> listID = new ArrayList<>();
                ArrayList<String> listNama = new ArrayList<>();

                try {
                    jsonObject = new JSONObject(JSON_STRING);
                    JSONArray jsonArray = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
                    Log.d("Data_JSON_LIST: ", String.valueOf(jsonArray));


                    for (int i=0;i<jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id = object.getString("id_pst");
                        String nama = object.getString("nama_pst");

                        listID.add(id);
                        listNama.add(nama);
                        Log.d("id: ", String.valueOf(id));
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TambahDetailKelas.this, android.R.layout.simple_spinner_dropdown_item, listNama);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner_pst.setAdapter(adapter);

                spinner_pst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        spinner_val_pst = Integer.parseInt(listID.get(i));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                spinner_pst.setAdapter(adapter);

                Log.d("spin", String.valueOf(arrayList));
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
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
//        final String id_kls = edit_id_kls.getText().toString().trim();
//        final String id_pst = edit_id_pst.getText().toString().trim();

        final String id_kls = spinner_kls.getSelectedItem().toString().trim();
        final String id_pst = String.valueOf(spinner_val_pst);

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