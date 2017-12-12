package fr.utt.if26.if26_sncfprojet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowNextDeparture extends AppCompatActivity {
    String apikey;
    Context context;
    TrajetClasse trajet;
    List<NextDepartureClass> nextDepartures = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_next_departure);
        context = getApplicationContext();
        Intent intent = getIntent();
        trajet = intent.getParcelableExtra("trajet");
        apikey = APIKeyClasse.getKey(context);
        System.out.println(trajet);
        searchInternetDeparture();

    }
    IResult volleyCallback() {
        return new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                nextDepartures.clear();
                nextDepartures.addAll(ParseJSON.getNextDeparture(response, trajet));
                System.out.println(nextDepartures);
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
        String url = "https://api.sncf.com/v1/coverage/sncf/journeys?to="
                + trajet.getGareArrive().getId() +
                "&from=" + trajet.getGareDepart().getId()
                + "&min_nb_journeys=5";
        IResult callback = volleyCallback();
        VolleyService volleyService = new VolleyService(callback, context);
        volleyService.getData(url, apikey);
    }
}
