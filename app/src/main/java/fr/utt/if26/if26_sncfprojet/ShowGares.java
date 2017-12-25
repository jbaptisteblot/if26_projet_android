package fr.utt.if26.if26_sncfprojet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class ShowGares extends Fragment implements ShowGares_RecycleView_Adapter.OnItemClicked{
    Context context;
    DatabaseHelper db;
    List<GareClasse> gares = new ArrayList<>();
    ShowGares_RecycleView_Adapter adapter;

    public ShowGares() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_gares, container, false);
        context = view.getContext();
        db = new DatabaseHelper(context);
        gares = db.getAllGaresPref();

        FloatingActionButton addButton = view.findViewById(R.id.ShowGares_AddButton);

        RecyclerView rv = view.findViewById(R.id.showGares_RecyclerView);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(context);
        rv.setLayoutManager(llm);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(),
                llm.getOrientation());
        rv.addItemDecoration(dividerItemDecoration);

        adapter = new ShowGares_RecycleView_Adapter(gares);
        rv.setAdapter(adapter);
        adapter.setOnClick(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SearchPlace.class);
                intent.putExtra("type", "pref");

                startActivityForResult(intent, 1);
            }
        });


        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                GareClasse gare = data.getParcelableExtra("gare");
                db.findOrCreateGarePref(gare);
                gares.clear();
                gares.addAll(db.getAllGaresPref());
                adapter.notifyDataSetChanged();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                System.out.println("Aucun retour");
            }
        }
    }


    @Override
    public void onItemClick(int position, View view) {
        switch (view.getId()) {
            case R.id.showGares_cardview:
                Intent intent = new Intent(context, ShowDepartsGare.class);
                intent.putExtra("garepref", gares.get(position));
                startActivity(intent);
                break;
            case R.id.showGares_cardview_DeleteButton:
                db.deleteGarePref(gares.get(position).getId());
                gares.remove(position);
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
