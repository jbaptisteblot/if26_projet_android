package fr.utt.if26.if26_sncfprojet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Méthode permettant de gérer des JSON
 * Created by Jeanba on 08/12/2017.
 */

class ParseJSON {
    static ArrayList<GareClasse> getGares(JSONObject json) {
        ArrayList<GareClasse> gareClasseArrayList = new ArrayList<>();
        try {
            JSONArray jsonArray = json.getJSONArray("places");
            for(int i=0; i < jsonArray.length(); i++) {
                try {
                    JSONObject oneObject = jsonArray.getJSONObject(i);
                    gareClasseArrayList.add(new GareClasse(oneObject.getJSONObject("stop_area").getString("name"), oneObject.getJSONObject("stop_area").getString("id")));
                } catch (JSONException e) {
                    System.out.println(e.toString());
                }
            }
        } catch (JSONException e) {
            System.out.println(e.toString());
        }
    return gareClasseArrayList;
    }
    static List<NextDepartureClass> getNextDeparture(JSONObject json, TrajetClasse trajet) {
        List<NextDepartureClass> listNexDepartures = new ArrayList<>();
        try {
            JSONArray jsonArray = json.getJSONArray("journeys");
            for(int i =0; i < jsonArray.length(); i++) {
                JSONObject oneObject = jsonArray.getJSONObject(i);
                SimpleDateFormat parser = new SimpleDateFormat("yyyyMMdd'T'kkmmss", Locale.getDefault());
                Date dateDepart = parser.parse(oneObject.getString("departure_date_time"));
                Date dateArrive = parser.parse(oneObject.getString("arrival_date_time"));
                int duration = oneObject.getInt("duration");
                listNexDepartures.add(new NextDepartureClass(trajet, dateDepart, dateArrive, duration));
            }
        } catch (JSONException e) {
            System.out.println(e.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listNexDepartures;
    }
}
