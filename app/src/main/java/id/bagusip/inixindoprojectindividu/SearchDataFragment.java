package id.bagusip.inixindoprojectindividu;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchDataFragment extends Fragment implements MainActivity.OnBackPressedListener, View.OnClickListener, AdapterView.OnItemClickListener{
    private EditText edit_search;
    private Button button_search;
    private View view;
    private ListView listView;
    private String JSON_STRING;
    private ProgressDialog loading;


    public SearchDataFragment() {
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
        View view = inflater.inflate(R.layout.fragment_search_data, container, false);

        edit_search = view.findViewById(R.id.edit_search);

        listView = view.findViewById(R.id.listView);

//        listView.setVisibility(View.GONE);


        button_search = view.findViewById(R.id.button_search);
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String val = edit_search.getText().toString().trim();

                getData(val);
            }
        });


        return view;
    }

    private void getData(String val) {
        class GetJsonData extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getContext(), "Ambil Data ", "Harap menunggu...", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.SEARCH_URL_COUNT_PESERTA,val);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);

                loading.dismiss();
                JSON_STRING = message;
                Log.d("DATA_JSON: ", JSON_STRING);
                displaySearchResult(JSON_STRING);
//                displaySearchResult();
            }
        }
        GetJsonData getJsonData = new GetJsonData();
        getJsonData.execute();
    }

    private void displaySearchResult(String json) {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        Log.d("json",json);

        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray jsonArray = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String id_kls = object.getString("k.id_kls");
                String tgl_mulai_kls = object.getString("k.tgl_mulai_kls");
                String tgl_akhir_kls = object.getString("k.tgl_akhir_kls");
                String count_pst = object.getString("count_id_pst");

                HashMap<String, String> res = new HashMap<>();
                res.put("id_kls", id_kls);
                res.put("tgl_mulai_kls", tgl_mulai_kls);
                res.put("tgl_akhir_kls", tgl_akhir_kls);
                res.put("count_pst", count_pst);


                list.add(res);
                Log.d("RES", String.valueOf(res));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // adapter untuk meletakkan array list kedalam list view
        ListAdapter adapter = new SimpleAdapter(
                getContext(), list, R.layout.activity_list_item_result_search,
                new String[]{"id_kls", "tgl_mulai_kls", "tgl_akhir_kls", "count_pst"},
                new int[]{R.id.txt_id_kls, R.id.txt_tgl_mulai_kls, R.id.txt_tgl_akhir_kls, R.id.txt_count_pst}

        );
        listView.setAdapter(adapter);
//        listView.setVisibility(View.VISIBLE);

    }


    private void search_data(String val) {
        Toast.makeText(getContext(), val, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void doBack() {

    }
}