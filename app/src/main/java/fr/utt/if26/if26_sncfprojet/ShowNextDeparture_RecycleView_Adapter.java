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

public class ShowNextDeparture_RecycleView_Adapter extends RecyclerView.Adapter<ShowNextDeparture_RecycleView_Adapter.NextDepartViewHolder>{
    private List<NextDepartureClass> nextDepartures;

    static class NextDepartViewHolder extends RecyclerView.ViewHolder {
        TextView heure_depart;
        CardView cardView;
        TextView heure_arrive;

        NextDepartViewHolder(View itemView) {
            super(itemView);
            heure_depart = itemView.findViewById(R.id.showNextDeparture_cardview_heuredepart);
            heure_arrive = itemView.findViewById(R.id.showNextDeparture_cardview_heurearrive);
            cardView = itemView.findViewById(R.id.showNextDeparture_cardview);
        }
    }
    ShowNextDeparture_RecycleView_Adapter(List<NextDepartureClass> nextDepartures) {
        this.nextDepartures = nextDepartures;
    }

    public NextDepartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.shownextdeparture_cardview, parent, false);
        return new NextDepartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NextDepartViewHolder holder, int position) {
        SimpleDateFormat formatter = new SimpleDateFormat("kk:mm", Locale.getDefault());
        holder.heure_depart.setText(formatter.format(nextDepartures.get(position).getDeparture_date_time()));
        holder.heure_arrive.setText(formatter.format(nextDepartures.get(position).getArrival_date_time()));

    }

    @Override
    public int getItemCount() {
        return nextDepartures.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
