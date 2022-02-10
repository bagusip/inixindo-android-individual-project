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

public class MateriDetail extends AppCompatActivity implements View.OnClickListener {
    EditText edit_id_mat, edit_nama_mat;
    Button btn_update_materi, btn_delete_materi;
    String id_mat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi_detail);

        edit_id_mat = findViewById(R.id.edit_id_mat);
        edit_nama_mat = findViewById(R.id.edit_nama_mat);
        btn_update_materi = findViewById(R.id.btn_update_materi);
        btn_delete_materi = findViewById(R.id.btn_delete_materi);

        Intent receiveIntent = getIntent();
        id_mat = receiveIntent.getStringExtra(Konfigurasi.PGW_ID);
        edit_id_mat.setText(id_mat);

        getJSON();

        btn_update_materi.setOnClickListener(this);
        btn_delete_materi.setOnClickListener(this);

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
                loading = ProgressDialog.show(MateriDetail.this,
                        "Mengambil Data", "Harap Menunggu",
                        false, false);
            }

            // Saat proses ambil data terjadi
            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.MATERI_URL_GET_DETAIL, id_mat);
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

            String nama_mat = object.getString("nama_mat");

            edit_id_mat.setText(id_mat);
            edit_nama_mat.setText(nama_mat);

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
        if (view == btn_update_materi){
            updateDataMateri();
        }
        else if(view == btn_delete_materi){
            confirmDeleteDataMateri();
        }
    }

    private void confirmDeleteDataMateri() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Menghapus Data");
        builder.setMessage("Apakah anda yaking menhapus data ini?");
        builder.setIcon(getResources().getDrawable(android.R.drawable.ic_delete));
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteDataMateri();
            }
        });
        builder.setNegativeButton("Cancel",null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deleteDataMateri() {
        class DeleteDataPeserta extends AsyncTask<Void, Void, String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MateriDetail.this,
                        "Menghapus data","Harap tunggu",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {

                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.MATERI_URL_DELETE, id_mat);

                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MateriDetail.this,
                        "pesan: "+s, Toast.LENGTH_SHORT).show();
                //redirect ke lihat data activity
//                startActivity(new Intent(PesertaDetail.this,PesertaFragment.class));
                startActivity(new Intent(MateriDetail.this,MainActivity.class));

            }
        }
        DeleteDataPeserta deleteDataPeserta = new DeleteDataPeserta();
        deleteDataPeserta.execute();
    }

    private void updateDataMateri() {
        final String nama_mat = edit_nama_mat.getText().toString().trim();

        class UpdateDataMateri extends AsyncTask<Void, Void, String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MateriDetail.this,
                        "Mengubah Data", "Harap Tunggu",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> peserta = new HashMap<>();
                peserta.put("id_mat", id_mat);
                peserta.put("nama_mat", nama_mat);

                HttpHandler handler = new HttpHandler();
                String result = handler.sendPostRequest(Konfigurasi.MATERI_URL_UPDATE, peserta);

                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MateriDetail.this,
                        "pesan: "+s, Toast.LENGTH_SHORT).show();
                //redirect ke lihat data activity
                startActivity(new Intent(MateriDetail.this,MainActivity.class));
            }
        }
        UpdateDataMateri updateDataMateri = new UpdateDataMateri();
        updateDataMateri.execute();
    }
}
