package fr.utt.if26.if26_sncfprojet;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Interface implémentant les résultats d'une requête WEB
 * Created by Jeanba on 08/12/2017.
 */

public interface IResult {
    public void notifySuccess(JSONObject response);
    public void notifyError(VolleyError error);
}
