package id.bagusip.inixindoprojectindividu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class KelasDetail extends AppCompatActivity implements View.OnClickListener{
    EditText edit_id_kls, edit_tgl_mulai_kls, edit_tgl_akhir_kls, edit_id_ins, edit_id_mat;
    Button btn_update_kelas, btn_delete_kelas;
    String id_kls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelas_detail);

        edit_id_kls = findViewById(R.id.edit_id_kls);
        edit_tgl_mulai_kls = findViewById(R.id.edit_tgl_mulai_kls);
        edit_tgl_akhir_kls = findViewById(R.id.edit_tgl_akhir_kls);
        edit_id_ins = findViewById(R.id.edit_id_ins);
        edit_id_mat = findViewById(R.id.edit_id_mat);
        btn_update_kelas = findViewById(R.id.btn_update_kelas);
        btn_delete_kelas = findViewById(R.id.btn_delete_kelas);

        Intent receiveIntent = getIntent();
        id_kls = receiveIntent.getStringExtra(Konfigurasi.PGW_ID);
        edit_id_kls.setText(id_kls);

        getJSON();

        btn_update_kelas.setOnClickListener(this);
        btn_delete_kelas.setOnClickListener(this);

    }

    private void getJSON() {
        // MENGAMBIL DATA DARI ANDROID KE SERVER
        // BANTUAN DARI CLASS ASYNCtASK
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            // ctrl + o pilih OnPreExcetue
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(KelasDetail.this,
                        "Mengambil Data", "Harap Menunggu",
                        false, false);
            }

            // Saat proses ambil data terjadi
            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.KELAS_URL_GET_DETAIL, id_kls);
                Log.d("result",result);
                return result;
            }

            // ctrl + o pilih OnPostExcetue
            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                displayDetailData(message);

            }
        }

        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void displayDetailData(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            JSONObject object = result.getJSONObject(0);

            String tgl_mulai_kls = object.getString("tgl_mulai_kls");
            String tgl_akhir_kls = object.getString("tgl_akhir_kls");
            String id_ins =  object.getString("id_ins");
            String id_mat = object.getString("id_mat");

            edit_tgl_mulai_kls.setText(tgl_mulai_kls);
            edit_tgl_akhir_kls.setText(tgl_akhir_kls);
            edit_id_ins.setText(id_ins);
            edit_id_mat.setText(id_mat);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view == btn_update_kelas){
            updateDataKelas();
        }
        else if(view == btn_delete_kelas){
            confirmDeleteDataKelas();
        }
    }

    private void confirmDeleteDataKelas() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Menghapus Data");
        builder.setMessage("Apakah anda yaking menhapus data ini?");
        builder.setIcon(getResources().getDrawable(android.R.drawable.ic_delete));
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteDataKelas();
            }
        });
        builder.setNegativeButton("Cancel",null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deleteDataKelas() {
        class DeleteDataKelas extends AsyncTask<Void, Void, String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(KelasDetail.this,
                        "Menghapus data","Harap tunggu",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {

                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.KELAS_URL_DELETE, id_kls);

                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(KelasDetail.this,
                        "pesan: "+s, Toast.LENGTH_LONG).show();

                startActivity(new Intent(KelasDetail.this,MainActivity.class));

            }
        }
        DeleteDataKelas deleteDataKelas = new DeleteDataKelas();
        deleteDataKelas.execute();
    }

    private void updateDataKelas() {
        final String tgl_mulai_kls = edit_tgl_mulai_kls.getText().toString().trim();
        final String tgl_akhir_kls = edit_tgl_akhir_kls.getText().toString().trim();
        final String id_ins = edit_id_ins.getText().toString().trim();
        final String id_mat = edit_id_mat.getText().toString().trim();

        class UpdateDataKelas extends AsyncTask<Void, Void, String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(KelasDetail.this,
                        "Mengubah Data", "Harap Tunggu",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> kelas = new HashMap<>();
                kelas.put("id_kls", id_kls);
                kelas.put("tgl_mulai_kls", tgl_mulai_kls);
                kelas.put("tgl_akhir_kls", tgl_akhir_kls);
                kelas.put("id_ins", id_ins);
                kelas.put("id_mat", id_mat);

                HttpHandler handler = new HttpHandler();
                String result = handler.sendPostRequest(Konfigurasi.KELAS_URL_UPDATE, kelas);

                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(KelasDetail.this,
                        "pesan: "+s, Toast.LENGTH_SHORT).show();
                //redirect ke lihat data activity
                startActivity(new Intent(KelasDetail.this,MainActivity.class));
            }
        }
        UpdateDataKelas updateDataKelas = new UpdateDataKelas();
        updateDataKelas.execute();

    }
}