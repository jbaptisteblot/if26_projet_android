package fr.utt.if26.if26_sncfprojet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class ShowNextDeparture extends AppCompatActivity {
    String apikey;
    Context context;
    TrajetClasse trajet;
    Date lastDate = new Date();
    ShowNextDeparture_RecycleView_Adapter adapter;
    List<NextDepartureClass> nextDepartures = new ArrayList<>();
    DatabaseHelper db;

    //Formatter date
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd'T'kkmmss", Locale.getDefault());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_next_departure);
        context = getApplicationContext();
        Intent intent = getIntent();
        trajet = intent.getParcelableExtra("trajet");
        apikey = APIKeyClasse.getKey(context);

        //Premier appel à la base de données
        db = new DatabaseHelper(context);
        try {
            nextDepartures = db.getAllDepart(trajet.getId_trajet());
            Iterator<NextDepartureClass> iter = nextDepartures.iterator();
            while (iter.hasNext()) {
                NextDepartureClass nd = iter.next();
                if(nd.getDeparture_date_time().before(new Date())) {
                    db.deleteDepart(nd.getTrajet().getId_trajet());
                    iter.remove();
                }
            }
            if (nextDepartures.size() > 0) {
                NextDepartureClass dernierTrajet = Collections.max(nextDepartures, new Comparator<NextDepartureClass>() {
                    @Override
                    public int compare(NextDepartureClass nextDepartureClass, NextDepartureClass t1) {
                        if (nextDepartureClass.getDeparture_date_time().before(t1.getDeparture_date_time())) {
                            return -1;
                        } else if (nextDepartureClass.getDeparture_date_time().equals(t1.getDeparture_date_time())) {
                            return 0;
                        } else {
                            return 1;
                        }
                    }
                });
                lastDate = dernierTrajet.getDeparture_date_time();
            }
            if (nextDepartures.size() < 5) {

                searchInternetDeparture();

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Recycler view
        RecyclerView rv = findViewById(R.id.showNextDeparture_RecyclerView);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(context);
        rv.setLayoutManager(llm);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(),
                llm.getOrientation());
        rv.addItemDecoration(dividerItemDecoration);
        adapter = new ShowNextDeparture_RecycleView_Adapter(nextDepartures);
        rv.setAdapter(adapter);


    }
    IResult volleyCallback() {
        return new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                List<NextDepartureClass> newDeparts = ParseJSON.getNextDeparture(response, trajet);
                for (NextDepartureClass newDepart: newDeparts) {
                    db.createDepart(newDepart);
                }
                nextDepartures.addAll(newDeparts);

                adapter.notifyDataSetChanged();
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
                + "&min_nb_journeys=5&datetime=" + formatter.format(lastDate);
        IResult callback = volleyCallback();
        VolleyService volleyService = new VolleyService(callback, context);
        volleyService.getData(url, apikey);
    }
}
