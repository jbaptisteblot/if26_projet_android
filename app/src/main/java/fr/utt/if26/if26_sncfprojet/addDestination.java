package fr.utt.if26.if26_sncfprojet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class addDestination extends AppCompatActivity {
    TrajetClass trajet = new TrajetClass(null, null);
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_destination);
        context = getApplicationContext();

        EditText depart = findViewById(R.id.addDestination_textInput_depart);
        EditText arrive = findViewById(R.id.addDestination_textInput_Arrive);

        depart.setOnClickListener(onClickListener);
        arrive.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()) {
                case R.id.addDestination_textInput_depart:
                    intent = new Intent(context, SearchPlace.class);
                    intent.putExtra("type", "depart");
                    //intent.putExtra("trajet", trajet);

                    startActivity(intent);
                    break;
                case R.id.addDestination_textInput_Arrive :
                    intent = new Intent(context, SearchPlace.class);
                    intent.putExtra("type", "arrive");

                    startActivity(intent);
                    break;
            }
        }
    };


}
