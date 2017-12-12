package fr.utt.if26.if26_sncfprojet;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * RecycleView for ShowTrajets
 * Created by Jeanba on 12/12/2017.
 */

public class ShowTrajets_RecycleView_Adapter extends RecyclerView.Adapter<ShowTrajets_RecycleView_Adapter.TrajetViewHolder> {
    private List<TrajetClasse> trajets;
    private OnItemClicked onClick;

    static class TrajetViewHolder extends RecyclerView.ViewHolder {
        TextView gare_depart;
        CardView cardView;
        TextView gare_arrive;
        LinearLayout extra_button;
        Button editButton;
        Button deleteButton;

        TrajetViewHolder(View itemView) {
            super(itemView);
            gare_depart = itemView.findViewById(R.id.showTrajets_cardview_nomGareDepart);
            gare_arrive = itemView.findViewById(R.id.showTrajets_cardview_nomGareArrive);
            cardView = itemView.findViewById(R.id.showTrajets_cardview);
            extra_button = itemView.findViewById(R.id.showTrajets_cardview_extraButton);
            editButton = itemView.findViewById(R.id.showTrajets_cardview_EditButton);
            deleteButton = itemView.findViewById(R.id.showTrajets_cardview_DeleteButton);
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
    public void onBindViewHolder(final TrajetViewHolder holder,int position) {
        holder.gare_depart.setText(trajets.get(position).getGareDepart().getName());
        holder.gare_arrive.setText(trajets.get(position).getGareArrive().getName());
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(holder.extra_button.getVisibility() == View.VISIBLE) {
                    holder.extra_button.setVisibility(View.GONE);
                }else {
                    holder.extra_button.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onItemClick(holder.getAdapterPosition(), view);
            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onItemClick(holder.getAdapterPosition(), view);
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onItemClick(holder.getAdapterPosition(), view);
            }
        });
    }
    @Override
    public int getItemCount() {
        return trajets.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public interface OnItemClicked {
        void onItemClick(int position, View view);
    }
    void setOnClick(OnItemClicked onClick) {
        this.onClick = onClick;
    }
}
