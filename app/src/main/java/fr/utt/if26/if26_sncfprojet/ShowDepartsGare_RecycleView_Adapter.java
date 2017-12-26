package fr.utt.if26.if26_sncfprojet;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * RecycleViewAdapter pour Show Next Departure
 * Created by Jeanba on 12/12/2017.
 */

public class ShowDepartsGare_RecycleView_Adapter extends RecyclerView.Adapter<ShowDepartsGare_RecycleView_Adapter.NextDepartViewHolder>{
    private List<DepartGareClass> departs;

    static class NextDepartViewHolder extends RecyclerView.ViewHolder {
        TextView gare_arrive;
        TextView heure_depart;
        CardView cardView;


        NextDepartViewHolder(View itemView) {
            super(itemView);
            heure_depart = itemView.findViewById(R.id.showDepartsGare_cardview_heuredepart);
            gare_arrive = itemView.findViewById(R.id.showDepartsGare_cardview_garearrive);
            cardView = itemView.findViewById(R.id.showDepartsGare_cardview);
        }
    }
    ShowDepartsGare_RecycleView_Adapter(List<DepartGareClass> departs) {
        this.departs = departs;
    }

    public NextDepartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.showdepartsgare_cardview, parent, false);
        return new NextDepartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NextDepartViewHolder holder, int position) {
        SimpleDateFormat formatter = new SimpleDateFormat("kk:mm", Locale.getDefault());
        holder.heure_depart.setText(formatter.format(departs.get(position).getHeure_depart()));
        holder.gare_arrive.setText(departs.get(position).getGare_arrive().getName());
    }

    @Override
    public int getItemCount() {
        return departs.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
