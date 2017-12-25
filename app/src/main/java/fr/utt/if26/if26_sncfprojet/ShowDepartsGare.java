package fr.utt.if26.if26_sncfprojet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowDepartsGare extends AppCompatActivity {
    Context context;
    String apikey;
    GareClasse garePref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_departs_gare);

        context = getApplicationContext();
        Intent intent = getIntent();
        garePref = intent.getParcelableExtra("garepref");

        TextView nomGare = findViewById(R.id.showDepartsGare_NomGare);
        nomGare.setText(garePref.getName());
        apikey = APIKeyClasse.getKey(context);
    }
}
