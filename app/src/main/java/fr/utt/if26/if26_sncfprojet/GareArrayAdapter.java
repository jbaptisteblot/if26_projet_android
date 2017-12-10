package fr.utt.if26.if26_sncfprojet;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Classe arrayAdapter
 * Created by Jeanba on 10/12/2017.
 */

public class GareArrayAdapter extends ArrayAdapter<GareClasse> {
    private Context context;
    private ArrayList<GareClasse> gareList = new ArrayList<>();
    GareArrayAdapter(@NonNull Context context, ArrayList<GareClasse> arrayList) {
        super(context, 0, arrayList);
        this.context = context;
        gareList = arrayList;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
                listItem = LayoutInflater.from(context).inflate(R.layout.searchplace_listitem, parent, false);
        GareClasse gare = gareList.get(position);

        TextView nomGare = listItem.findViewById(R.id.searchplace_listItem_nomGare);
        TextView idGare = listItem.findViewById(R.id.searchplace_listItem_idGare);
        nomGare.setText(gare.getName());
        idGare.setText(gare.getId());

        return listItem;
    }
    public GareClasse getItem(int position){
        return gareList.get(position);
    }
}
