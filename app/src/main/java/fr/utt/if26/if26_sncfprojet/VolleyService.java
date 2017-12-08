package fr.utt.if26.if26_sncfprojet;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe permettant de gérer les requêtes WEB
 * Created by Jeanba on 08/12/2017.
 */

public class VolleyService {
    IResult callback;
    Context context;
    VolleyService(IResult callback, Context context) {
        this.callback = callback;
        this.context = context;
    }
    public void getData(final String url,final String auth) {
        try {
            RequestQueue queue = Volley.newRequestQueue(context);

            JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (callback != null) {
                        callback.notifySuccess(response);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (callback != null) {
                        callback.notifyError(error);
                    }
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Authorization", auth);
                    return headers;
                }
            };

            queue.add(jsonObj);
        }catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
