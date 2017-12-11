package fr.utt.if26.if26_sncfprojet;

import android.app.Activity;
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
    EditText editText_Depart;
    EditText editText_Arrive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_destination);
        context = getApplicationContext();

        editText_Depart = findViewById(R.id.addDestination_textInput_depart);
        editText_Arrive = findViewById(R.id.addDestination_textInput_Arrive);
        Button button_save = findViewById(R.id.addDestination_saveButton);


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

                    startActivityForResult(intent, 1);
                    break;
                case R.id.addDestination_textInput_Arrive :
                    intent = new Intent(context, SearchPlace.class);
                    intent.putExtra("type", "arrive");
                    intent.putExtra("trajet", trajet);
                    startActivityForResult(intent, 1);
                    break;
                case R.id.addDestination_saveButton :
                    Toast.makeText(context, "Vous avez essayé d'enregistrer le trajet.", Toast.LENGTH_SHORT).show();
                    DatabaseHelper db = new DatabaseHelper(context);
                    trajet.SyncToDB(db);
                    break;
            }
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                trajet = data.getParcelableExtra("trajet");

                if (trajet.getGareDepart() != null) {
                    editText_Depart.setText(trajet.getGareDepart().getName());
                }
                if (trajet.getGareArrive() != null) {
                    editText_Arrive.setText(trajet.getGareArrive().getName());
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                System.out.println("Aucun retour");
            }
        }
    }//onActivityResult


}
