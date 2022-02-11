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

import id.bagusip.inixindoprojectindividu.databinding.FragmentDetailKelasBinding;

public class DetailKelasFragment extends Fragment implements MainActivity.OnBackPressedListener, View.OnClickListener, AdapterView.OnItemClickListener {
    private FragmentDetailKelasBinding detailKelasBinding;
    private View view;
    private String JSON_STRING;
    private ProgressDialog loading;
    private ListView list_view;


    public DetailKelasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        detailKelasBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_kelas, container, false);
        ((MainActivity) getActivity()).setOnBackPressedListener(this);
        view = detailKelasBinding.getRoot();
        initView();
        return view;
    }


    private void initView() {
        // custom action bar
        ActionBar customActionBar = ((MainActivity) getActivity()).getSupportActionBar();
        customActionBar.setTitle("Data Detail Kelas");

        // penanganan List View
        detailKelasBinding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                // membuka detail
                Log.d("test","clicked");
                Intent myIntent = new Intent(getActivity(), DetailKelasDetail.class);
                HashMap<String, String> map = (HashMap) parent.getItemAtPosition(i);
                String id_detail_kls = map.get("id_detail_kls").toString();
                myIntent.putExtra(Konfigurasi.PGW_ID, id_detail_kls);
                Log.d("test",id_detail_kls);
                startActivity(myIntent);
            }
        });

        // penanganan FAB
        detailKelasBinding.addFab.setOnClickListener(this);

        // ambil data dari JSON
        getJsonData();
    }

    private void getJsonData() {
        class GetJsonData extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(view.getContext(), "Ambil Data Detail Kelas", "Harap menunggu...", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.DETAIL_KELAS_URL_GET_ALL);
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
                displayAllDataDetailKelas();

            }
        }
        GetJsonData getJsonData = new GetJsonData();
        getJsonData.execute();
    }

    private void displayAllDataDetailKelas() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray jsonArray = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String id_kls = object.getString("k.id_kls");
                String nama_mat = object.getString("m.nama_mat");
                String nama_ins = object.getString("i.nama_ins");
                String id_detail_kls = object.getString("dk.id_detail_kls");

                HashMap<String, String> detailKelas = new HashMap<>();
                detailKelas.put("id_kls", id_kls);
                detailKelas.put("nama_mat", nama_mat);
                detailKelas.put("nama_ins", nama_ins);
                detailKelas.put("id_detail_kls",id_detail_kls);

                list.add(detailKelas);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // adapter untuk meletakkan array list kedalam list view
        ListAdapter adapter = new SimpleAdapter(
                view.getContext(), list, R.layout.activity_list_item_kelas,
                new String[]{"id_detail_kls","nama_mat", "nama_ins"},
                new int[]{R.id.txt_id, R.id.txt_name_mat,R.id.txt_name_ins}
        );
        detailKelasBinding.listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        // penanganan FAB
        startActivity(new Intent(view.getContext(), TambahDetailKelas.class));
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
        Log.d("test","clicked");
        Intent myIntent = new Intent(getActivity(), DetailKelasFragment.class);
        HashMap<String, String> map = (HashMap) adapterView.getItemAtPosition(i);
        String pgwId = map.get(Konfigurasi.TAG_JSON_ID).toString();
        myIntent.putExtra(Konfigurasi.PGW_ID, pgwId);
        startActivity(myIntent);
    }
}