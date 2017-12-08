package fr.utt.if26.if26_sncfprojet;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public class SearchPlace extends AppCompatActivity {
    String apikey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_place);

        Context context = getApplicationContext();
        Button searchPlaceButton = findViewById(R.id.seachplace_button);
        EditText searchPlaceInput = findViewById(R.id.seachplace_input);

        String apikey = APIKeyClass.getKey(context);
        IResult callback = volleyCallback(context);
        VolleyService volleyService = new VolleyService(callback, context);
        volleyService.getData("https://api.sncf.com/v1/coverage/sncf/places?q=%22Bordeaux%22", apikey);

    }
    IResult volleyCallback(final Context context) {
        IResult callback = new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
            }
            @Override
            public void notifyError(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }
        };
        return callback;
    }
}
