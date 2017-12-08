package fr.utt.if26.if26_sncfprojet;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SearchPlace extends AppCompatActivity {
    String apikey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_place);

        Context context = getApplicationContext();
        Button searchPlaceButton = findViewById(R.id.seachplace_button);
        EditText searchPlaceInput = findViewById(R.id.seachplace_input);

        apikey = APIKeyClass.getKey(context);

    }
}
