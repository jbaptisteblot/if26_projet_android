package fr.utt.if26.if26_sncfprojet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ShowNextDeparture extends AppCompatActivity {
    TrajetClasse trajet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_next_departure);
        Intent intent = getIntent();
        trajet = intent.getParcelableExtra("trajet");
        System.out.println(trajet);
    }
}
