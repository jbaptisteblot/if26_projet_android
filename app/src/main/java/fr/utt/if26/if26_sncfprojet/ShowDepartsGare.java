package fr.utt.if26.if26_sncfprojet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowDepartsGare extends AppCompatActivity {
    List<DepartGareClass> departs = new ArrayList<>();
    Context context;
    String apikey;
    GareClasse garePref;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_departs_gare);

        context = getApplicationContext();
        db = new DatabaseHelper(context);
        Intent intent = getIntent();
        garePref = intent.getParcelableExtra("garepref");

        TextView nomGare = findViewById(R.id.showDepartsGare_NomGare);
        nomGare.setText(garePref.getName());
        apikey = APIKeyClasse.getKey(context);

        searchInternetDeparture();
    }

    IResult volleyCallback() {
        return new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                System.out.println(response);
                List<DepartGareClass> newDeparts = ParseJSON.getDepartureGare(response, garePref);
                for (DepartGareClass newDepart: newDeparts) {
                    //db.createDepart(newDepart);
                }
                departs.addAll(newDeparts);

                //adapter.notifyDataSetChanged();
                System.out.println(departs);
                //Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
            }
            @Override
            public void notifyError(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }
        };
    }
    void searchInternetDeparture() {
        Toast.makeText(context, "Recherche...", Toast.LENGTH_SHORT).show();
        String url = "https://api.sncf.com/v1/coverage/sncf/stop_areas/"
                + garePref.getId()
                + "?data_freshness=realtime";
        IResult callback = volleyCallback();
        VolleyService volleyService = new VolleyService(callback, context);
        volleyService.getData(url, apikey);
    }
}
