package fr.utt.if26.if26_sncfprojet;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * RecycleView for ShowTrajets
 * Created by Jeanba on 12/12/2017.
 */

public class ShowTrajets_RecycleView_Adapter extends RecyclerView.Adapter<ShowTrajets_RecycleView_Adapter.TrajetViewHolder> {
    private List<TrajetClasse> trajets;

    static class TrajetViewHolder extends RecyclerView.ViewHolder {

        TextView gare_depart;
        TextView gare_arrive;

        TrajetViewHolder(View itemView) {
            super(itemView);
            gare_depart = itemView.findViewById(R.id.showTrajets_cardview_nomGareDepart);
            gare_arrive = itemView.findViewById(R.id.showTrajets_cardview_nomGareArrive);
        }

    }
    ShowTrajets_RecycleView_Adapter(List<TrajetClasse> trajets) {
        this.trajets = trajets;
    }
    @Override
    public TrajetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.showtrajets_cardview, parent, false);
        return new TrajetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TrajetViewHolder holder, int position) {
        holder.gare_depart.setText(trajets.get(position).getGareDepart().getName());
        holder.gare_arrive.setText(trajets.get(position).getGareArrive().getName());
    }

    @Override
    public int getItemCount() {
        return trajets.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
