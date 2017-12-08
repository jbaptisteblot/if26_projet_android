package fr.utt.if26.if26_sncfprojet;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public class SearchPlace extends AppCompatActivity {
    String apikey;
    EditText searchPlaceInput;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_place);

        context = getApplicationContext();
        Button searchPlaceButton = findViewById(R.id.searchplace_button);
        searchPlaceInput = findViewById(R.id.searchplace_input);
        searchPlaceButton.setOnClickListener(onClickListener);
        apikey = APIKeyClass.getKey(context);


    }
    IResult volleyCallback() {
        return new IResult() {
            @Override
            public void notifySuccess(JSONObject response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
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
            String url = "https://api.sncf.com/v1/coverage/sncf/places?q=" + searchPlaceInput.getText().toString();
            IResult callback = volleyCallback();
            VolleyService volleyService = new VolleyService(callback, context);
            volleyService.getData(url, apikey);
        }
    };
}
