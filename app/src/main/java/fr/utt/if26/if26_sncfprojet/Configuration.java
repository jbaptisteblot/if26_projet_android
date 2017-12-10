package fr.utt.if26.if26_sncfprojet;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Configuration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        final EditText configurationAPIKEYInput = findViewById(R.id.configuration_apikey_input);
        final Button saveButton = findViewById(R.id.configuration_save_button);
        final Context context = getApplicationContext();

        String apikey = APIKeyClasse.getKey(context);
        if (apikey != null) {
            configurationAPIKEYInput.setText(APIKeyClasse.getKey(this));
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIKeyClasse.setKey(context, configurationAPIKEYInput.getText().toString());
                Toast.makeText(context, R.string.apikey_save_successfull, Toast.LENGTH_LONG).show();
            }
        });
    }
}
