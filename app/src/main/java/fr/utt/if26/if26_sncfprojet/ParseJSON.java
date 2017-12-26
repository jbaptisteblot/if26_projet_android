package fr.utt.if26.if26_sncfprojet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Méthode permettant de gérer des JSON
 * Created by Jeanba on 08/12/2017.
 */

class ParseJSON {
    private static SimpleDateFormat parser = new SimpleDateFormat("yyyyMMdd'T'kkmmss", Locale.getDefault());

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
                Date dateDepart = parser.parse(oneObject.getString("departure_date_time"));
                Date dateArrive = parser.parse(oneObject.getString("arrival_date_time"));
                int duration = oneObject.getInt("duration");
                int correspondance = oneObject.getInt("nb_transfers");
                listNexDepartures.add(new NextDepartureClass(trajet, dateDepart, dateArrive, duration, correspondance));
            }
        } catch (JSONException e) {
            System.out.println(e.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listNexDepartures;
    }
    static List<DepartGareClass> getDepartureGare(JSONObject json, GareClasse gare) {
        List<DepartGareClass> listNexDepartures = new ArrayList<>();
        try {
            JSONArray jsonArray = json.getJSONArray("departures");
            for(int i =0; i < jsonArray.length(); i++) {
                JSONObject oneObject = jsonArray.getJSONObject(i);
                GareClasse gareArrive = new GareClasse(oneObject.getJSONObject("route").getJSONObject("direction").getString("name"), oneObject.getJSONObject("route").getJSONObject("direction").getString("id"));
                Date dateDepart = parser.parse(oneObject.getJSONObject("stop_date_time").getString("departure_date_time"));
                Date dateArrive = parser.parse(oneObject.getJSONObject("stop_date_time").getString("arrival_date_time"));
                listNexDepartures.add(new DepartGareClass(gare, gareArrive, dateDepart, dateArrive));
            }
        } catch (JSONException e) {
            System.out.println(e.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listNexDepartures;
    }
}
