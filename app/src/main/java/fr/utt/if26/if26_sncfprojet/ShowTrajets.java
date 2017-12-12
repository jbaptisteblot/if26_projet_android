package fr.utt.if26.if26_sncfprojet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

public class ShowTrajets extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_trajets);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = getApplicationContext();
        FloatingActionButton addButton = findViewById(R.id.ShowTrajets_AddButton);

        RecyclerView rv = findViewById(R.id.showTrajets_recyclerview);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(context);
        rv.setLayoutManager(llm);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(),
                llm.getOrientation());
        rv.addItemDecoration(dividerItemDecoration);

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        List<TrajetClasse> trajets = db.getAllTrajet();
        ShowTrajets_RecycleView_Adapter adapter = new ShowTrajets_RecycleView_Adapter(trajets, context);
        rv.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddTrajet.class);
                startActivity(intent);
            }
        });
    }

}
