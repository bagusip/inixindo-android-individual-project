package id.bagusip.inixindoprojectindividu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchDataFragment extends Fragment{
    EditText edit_search;
    Button button_search;


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

        button_search = view.findViewById(R.id.button_search);
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String val = edit_search.getText().toString().trim();
                search_data(val);
            }
        });

        return view;
    }


    private void search_data(String val) {
        Toast.makeText(getContext(), val, Toast.LENGTH_SHORT).show();
    }
}