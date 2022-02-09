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

import id.bagusip.inixindoprojectindividu.databinding.FragmentInstrukturBinding;

public class InstrukturFragment extends Fragment implements MainActivity.OnBackPressedListener, View.OnClickListener, AdapterView.OnItemClickListener {

    private FragmentInstrukturBinding instrukturBinding;
    private View view;
    private String JSON_STRING;
    private ProgressDialog loading;
    private ListView list_view;

    public InstrukturFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        instrukturBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_instruktur, container, false);
        ((MainActivity) getActivity()).setOnBackPressedListener(this);
        view = instrukturBinding.getRoot();
        initView();
        return view;
    }

    private void initView() {
        ActionBar customActionBar = ((MainActivity) getActivity()).getSupportActionBar();
        customActionBar.setTitle("Data Instruktur");

        // penanganan List View
        instrukturBinding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                // membuka detail
                Intent myIntent = new Intent(getActivity(), InstrukturDetail.class);
                HashMap<String, String> map = (HashMap) parent.getItemAtPosition(i);
                String id_peserta = map.get("id_ins").toString();
                myIntent.putExtra(Konfigurasi.PGW_ID, id_peserta);
                startActivity(myIntent);
            }
        });

        // penanganan FAB
        instrukturBinding.addFab.setOnClickListener(this);

        // ambil data dari JSON
        getJsonData();
    }

    private void getJsonData() {
        class GetJsonData extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(view.getContext(), "Ambil Data Instruktur", "Harap menunggu...", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.INSTRUKTUR_URL_GET_ALL);
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
                displayAllDataPeserta();
            }
        }
        GetJsonData getJsonData = new GetJsonData();
        getJsonData.execute();
    }

    private void displayAllDataPeserta() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray jsonArray = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String id_ins = object.getString("id_ins");
                String nama_ins = object.getString("nama_ins");
                String email_ins = object.getString("email_ins");
                String hp_ins = object.getString("hp_ins");

                HashMap<String, String> insturktur = new HashMap<>();
                insturktur.put("id_ins", id_ins);
                insturktur.put("nama_ins", nama_ins);
                insturktur.put("email_ins", email_ins);
                insturktur.put("hp_ins", hp_ins);

                list.add(insturktur);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // adapter untuk meletakkan array list kedalam list view
        ListAdapter adapter = new SimpleAdapter(
                view.getContext(), list, R.layout.activity_list_item,
                new String[]{"id_ins", "nama_ins"},
                new int[]{R.id.txt_id, R.id.txt_name}
        );
        instrukturBinding.listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(view.getContext(), TambahInstruktur.class));
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
        Log.d("test", "clicked");
        Intent myIntent = new Intent(getActivity(), PesertaDetail.class);
        HashMap<String, String> map = (HashMap) adapterView.getItemAtPosition(i);
        String pgwId = map.get(Konfigurasi.TAG_JSON_ID).toString();
        myIntent.putExtra(Konfigurasi.PGW_ID, pgwId);
        startActivity(myIntent);
    }
}