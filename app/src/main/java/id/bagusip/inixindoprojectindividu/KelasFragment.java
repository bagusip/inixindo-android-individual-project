package id.bagusip.inixindoprojectindividu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import id.bagusip.inixindoprojectindividu.databinding.FragmentKelasBinding;

public class KelasFragment extends Fragment implements MainActivity.OnBackPressedListener, View.OnClickListener, AdapterView.OnItemClickListener {
    private FragmentKelasBinding kelasBinding;
    private View view;
    private String JSON_STRING;
    private ProgressDialog loading;
    private ListView list_view;

    public KelasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        kelasBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_kelas, container, false);
        ((MainActivity) getActivity()).setOnBackPressedListener(this);
        view = kelasBinding.getRoot();
        initView();
        // Inflate the layout for this fragment
        return view;
    }

    private void initView() {
        // custom action bar
        ActionBar customActionBar = ((MainActivity) getActivity()).getSupportActionBar();
        customActionBar.setTitle("Data Peserta");

        // penanganan List View
        kelasBinding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                // membuka detail
                Log.d("test","clicked");
                Intent myIntent = new Intent(getActivity(), PesertaDetail.class);
                HashMap<String, String> map = (HashMap) parent.getItemAtPosition(i);
                String id_peserta = map.get(Konfigurasi.TAG_JSON_ID).toString();
                myIntent.putExtra(Konfigurasi.PGW_ID, id_peserta);
                Log.d("test",id_peserta);
                startActivity(myIntent);
            }
        });

        // penanganan FAB
        kelasBinding.addFab.setOnClickListener(this);

        // ambil data dari JSON
        getJsonData();
    }

    private void getJsonData() {
        class GetJsonData extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(view.getContext(), "Ambil Data Kelas", "Harap menunggu...", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.KELAS_URL_GET_ALL);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                JSON_STRING = message;
                Log.d("DATA_JSON: ", JSON_STRING);
                // Toast.makeText(view.getContext(), JSON_STRING, Toast.LENGTH_LONG).show();

                // menampilkan data json kedalam list view
                diplayAllDataKelas();
            }
        }
        GetJsonData getJsonData = new GetJsonData();
        getJsonData.execute();
    }

    private void diplayAllDataKelas() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray jsonArray = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String tgl_mulai_kls = object.getString("tgl_mulai_kls");
                String tgl_akhir_kls = object.getString("tgl_akhir_kls");
                String id_ins = object.getString("id_ins");
                String id_mat = object.getString("id_mat");

                HashMap<String, String> kelas = new HashMap<>();
                kelas.put("tgl_mulai_kls", tgl_mulai_kls);
                kelas.put("tgl_akhir_kls", tgl_akhir_kls);
                kelas.put("id_ins", id_ins);
                kelas.put("id_mat", id_mat);

                list.add(kelas);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // adapter untuk meletakkan array list kedalam list view
        ListAdapter adapter = new SimpleAdapter(
                view.getContext(), list, R.layout.activity_list_item_kelas,
                new String[]{"tgl_mulai_kls", "tgl_akhir_kls"},
                new int[]{R.id.txt_id, R.id.txt_name}
        );
        kelasBinding.listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void doBack() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.replace(R.id.frame_layout, new HomeFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // Ketika salah satu list dipilih
        // detail : id, name, Desg, Salary
        Log.d("test","clicked");
        Intent myIntent = new Intent(getActivity(), KelasDetail.class);
        HashMap<String, String> map = (HashMap) adapterView.getItemAtPosition(i);
        String pgwId = map.get(Konfigurasi.TAG_JSON_ID).toString();
        myIntent.putExtra(Konfigurasi.PGW_ID, pgwId);
        startActivity(myIntent);
    }
}