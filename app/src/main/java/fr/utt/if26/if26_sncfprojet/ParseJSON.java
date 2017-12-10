package fr.utt.if26.if26_sncfprojet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
}
