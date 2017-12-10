package fr.utt.if26.if26_sncfprojet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addDestination extends AppCompatActivity {
    TrajetClasse trajet = new TrajetClasse(null, null);
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_destination);
        context = getApplicationContext();
        Intent intent = getIntent();
        if (intent.hasExtra("trajet")) {
            trajet = intent.getParcelableExtra("trajet");
        }

        EditText editText_Depart = findViewById(R.id.addDestination_textInput_depart);
        EditText editText_Arrive = findViewById(R.id.addDestination_textInput_Arrive);
        Button button_save = findViewById(R.id.addDestination_saveButton);

        if (trajet.getGareDepart() != null) {
            editText_Depart.setText(trajet.getGareDepart().getName());
        }
        if (trajet.getGareArrive() != null) {
            editText_Arrive.setText(trajet.getGareArrive().getName());
        }
        editText_Depart.setOnClickListener(onClickListener);
        editText_Arrive.setOnClickListener(onClickListener);
        button_save.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()) {
                case R.id.addDestination_textInput_depart:
                    intent = new Intent(context, SearchPlace.class);
                    intent.putExtra("type", "depart");
                    intent.putExtra("trajet", trajet);

                    startActivity(intent);
                    break;
                case R.id.addDestination_textInput_Arrive :
                    intent = new Intent(context, SearchPlace.class);
                    intent.putExtra("type", "arrive");
                    intent.putExtra("trajet", trajet);
                    startActivity(intent);
                    break;
                case R.id.addDestination_saveButton :
                    Toast.makeText(context, "Vous avez essayé d'enregistrer le trajet.", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


}
