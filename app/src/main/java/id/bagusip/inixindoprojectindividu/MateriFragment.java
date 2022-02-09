package id.bagusip.inixindoprojectindividu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

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

import id.bagusip.inixindoprojectindividu.databinding.FragmentMateriBinding;

public class MateriFragment extends Fragment implements MainActivity.OnBackPressedListener, View.OnClickListener, AdapterView.OnItemClickListener {

    private FragmentMateriBinding materiBinding;
    private View view;
    private String JSON_STRING;
    private ProgressDialog loading;
    private ListView list_view;

    public MateriFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        materiBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_materi, container, false);
        ((MainActivity) getActivity()).setOnBackPressedListener(this);
        view = materiBinding.getRoot();
        initView();
        return view;
    }

    private void initView() {
        // custom action bar
        ActionBar customActionBar = ((MainActivity) getActivity()).getSupportActionBar();
        customActionBar.setTitle("Data Materi");

        // penanganan List View
//        materiBinding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
//                // membuka detail
//                Log.d("test","clicked");
//                Intent myIntent = new Intent(getActivity(), PesertaDetail.class);
//                HashMap<String, String> map = (HashMap) parent.getItemAtPosition(i);
//                String id_mat = map.get("id_mat").toString();
//                myIntent.putExtra(Konfigurasi.PGW_ID, id_mat);
//                Log.d("test",id_mat);
//                startActivity(myIntent);
//            }
//        });

        // penanganan FAB
        materiBinding.addFab.setOnClickListener(this);

        // ambil data dari JSON
        getJsonData();
    }

    private void getJsonData() {
        class GetJsonData extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(view.getContext(), "Ambil Data Materi", "Harap menunggu...", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.MATERI_URL_GET_ALL);
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
                displayAllDataMateri();
            }

            private void displayAllDataMateri() {
                JSONObject jsonObject = null;
                ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

                try {
                    jsonObject = new JSONObject(JSON_STRING);
                    JSONArray jsonArray = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id_mat = object.getString("id_mat");
                        String nama_mat = object.getString("nama_mat");

                        HashMap<String, String> peserta = new HashMap<>();
                        peserta.put("id_mat", id_mat);
                        peserta.put("nama_mat", nama_mat);
                        list.add(peserta);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                // adapter untuk meletakkan array list kedalam list view
                ListAdapter adapter = new SimpleAdapter(
                        view.getContext(), list, R.layout.activity_list_item,
                        new String[]{"id_mat", "nama_mat"},
                        new int[]{R.id.txt_id, R.id.txt_name}
                );
                materiBinding.listView.setAdapter(adapter);
            }
        }
        GetJsonData getJsonData = new GetJsonData();
        getJsonData.execute();
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(view.getContext(), TambahMateri.class));
    }

    @Override
    public void doBack() {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}