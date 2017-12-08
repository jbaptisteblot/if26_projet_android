package fr.utt.if26.if26_sncfprojet;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.ArrayList;

public class SearchPlace extends AppCompatActivity {
    String apikey;
    EditText searchPlaceInput;
    ListView searchPlaceListView;
    Context context;
    ArrayList<String> gares;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_place);

        context = getApplicationContext();
        ImageButton searchPlaceButton = findViewById(R.id.searchplace_button);
        searchPlaceInput = findViewById(R.id.searchplace_input);
        searchPlaceListView = findViewById(R.id.searchplace_listView);
        searchPlaceButton.setOnClickListener(onClickListener);
        apikey = APIKeyClass.getKey(context);

        gares = new ArrayList<>();
        //TODO : Implémenter un ArrayAdapter qui soit compatible avec une ArrayList de type GareClasse. Et changer de String à Gare Classe pour les autres ensuite
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, gares);
        searchPlaceListView.setAdapter(arrayAdapter);



    }
    IResult volleyCallback() {
        return new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                gares.clear();
                gares.addAll(ParseJSON.getGares(response));
                System.out.println(gares);
                ((BaseAdapter) searchPlaceListView.getAdapter()).notifyDataSetChanged();
                //Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
            }
            @Override
            public void notifyError(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }
        };
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String url = "https://api.sncf.com/v1/coverage/sncf/places?type[]=stop_area&q=" + searchPlaceInput.getText().toString();
            IResult callback = volleyCallback();
            VolleyService volleyService = new VolleyService(callback, context);
            volleyService.getData(url, apikey);

        }
    };
}
