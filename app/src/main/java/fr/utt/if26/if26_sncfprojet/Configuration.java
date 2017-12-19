package fr.utt.if26.if26_sncfprojet;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Configuration extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_configuration, container, false);
        final EditText configurationAPIKEYInput = view.findViewById(R.id.configuration_apikey_input);
        final Button saveButton = view.findViewById(R.id.configuration_save_button);
        final Context context = view.getContext();

        String apikey = APIKeyClasse.getKey(context);
        if (apikey != null) {
            configurationAPIKEYInput.setText(APIKeyClasse.getKey(context));
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIKeyClasse.setKey(context, configurationAPIKEYInput.getText().toString());
                Toast.makeText(context, R.string.apikey_save_successfull, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
