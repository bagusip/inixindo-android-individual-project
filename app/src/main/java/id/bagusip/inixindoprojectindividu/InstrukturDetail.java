package id.bagusip.inixindoprojectindividu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

public class InstrukturDetail extends AppCompatActivity implements View.OnClickListener {

    EditText edit_id_ins, edit_nama_ins, edit_email_ins, edit_hp_ins;
    Button btn_update_instruktur, btn_delete_instruktur;
    String id_ins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruktur_detail);

        edit_id_ins = findViewById(R.id.edit_id_ins);
        edit_nama_ins = findViewById(R.id.edit_nama_ins);
        edit_email_ins = findViewById(R.id.edit_email_ins);
        edit_hp_ins = findViewById(R.id.edit_hp_ins);
        btn_update_instruktur = findViewById(R.id.btn_update_instruktur);
        btn_update_instruktur = findViewById(R.id.btn_update_peserta);

        Intent receiveIntent = getIntent();
        id_ins = receiveIntent.getStringExtra(Konfigurasi.PGW_ID);
        edit_id_ins.setText(id_ins);

        Log.d("id_ins",id_ins);
        
        getJSON();
        
//        btn_update_instruktur.setOnClickListener(this);
//        btn_delete_instruktur.setOnClickListener(this);
        

    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            // ctrl + o pilih OnPreExcetue
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(InstrukturDetail.this,
                        "Mengambil Data", "Harap Menunggu",
                        false, false);
            }

            // Saat proses ambil data terjadi
            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.INSTRUKTUR_URL_GET_DETAIL, id_ins);
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

            String nama_ins = object.getString("nama_ins");
            String email_ins = object.getString("email_ins");
            String hp_ins = object.getString("hp_ins");

            edit_nama_ins.setText(nama_ins);
            edit_email_ins.setText(email_ins);
            edit_hp_ins.setText(hp_ins);

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
        if (view == btn_update_instruktur){
            updateDataPeserta();
        }
        else if(view == btn_delete_instruktur){
            confirmDeleteDataPeserta();
        }
    }

    private void confirmDeleteDataPeserta() {
    }

    private void updateDataPeserta() {
    }
}