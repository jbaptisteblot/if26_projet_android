package fr.utt.if26.if26_sncfprojet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivityTemp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_temp);
        Button tempConfigurationButton = findViewById(R.id.temp_configuration_button);
        Button tempSearchPlaceButton = findViewById(R.id.temp_searchPlace_button);
        tempConfigurationButton.setOnClickListener(onClickListener);
        tempSearchPlaceButton.setOnClickListener(onClickListener);

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.temp_configuration_button:
                    intent = new Intent(MainActivityTemp.this, Configuration.class);
                    startActivity(intent);
                    break;
                case R.id.temp_searchPlace_button:
                    intent = new Intent(MainActivityTemp.this, SearchPlace.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
