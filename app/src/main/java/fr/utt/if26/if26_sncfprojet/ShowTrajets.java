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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShowTrajets extends AppCompatActivity implements ShowTrajets_RecycleView_Adapter.OnItemClicked{
    Context context;
    DatabaseHelper db;
    List<TrajetClasse> trajets = new ArrayList<>();
    ShowTrajets_RecycleView_Adapter adapter;

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

        db = new DatabaseHelper(getApplicationContext());
        trajets = db.getAllTrajet();
        adapter = new ShowTrajets_RecycleView_Adapter(trajets);
        rv.setAdapter(adapter);
        adapter.setOnClick(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddTrajet.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClick(int position, View view) {
        switch (view.getId()) {
            case R.id.showTrajets_cardview_EditButton:
                Toast.makeText(context, "Edit " + position, Toast.LENGTH_SHORT).show();
                break;
            case R.id.showTrajets_cardview_DeleteButton:
                db.deleteTrajet(trajets.get(position).getId_trajet());
                trajets.remove(position);
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
