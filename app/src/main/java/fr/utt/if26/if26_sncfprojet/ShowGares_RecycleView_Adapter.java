package fr.utt.if26.if26_sncfprojet;

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

public class ShowGares_RecycleView_Adapter extends RecyclerView.Adapter<ShowGares_RecycleView_Adapter.GarePrefViewHolder> {
    private List<GareClasse> gare;
    private OnItemClicked onClick;

    static class GarePrefViewHolder extends RecyclerView.ViewHolder {
        TextView gare_depart;
        CardView cardView;
        LinearLayout extra_button;
        Button deleteButton;

        GarePrefViewHolder(View itemView) {
            super(itemView);
            gare_depart = itemView.findViewById(R.id.showGares_cardview_nomGareDepart);
            cardView = itemView.findViewById(R.id.showGares_cardview);
            extra_button = itemView.findViewById(R.id.showGares_cardview_extraButton);
            deleteButton = itemView.findViewById(R.id.showGares_cardview_DeleteButton);
        }
    }
    ShowGares_RecycleView_Adapter(List<GareClasse> gare) {
        this.gare = gare;
    }

    @Override
    public GarePrefViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.showgares_cardview, parent, false);
        return new GarePrefViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final GarePrefViewHolder holder,int position) {
        holder.gare_depart.setText(gare.get(position).getName());
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
        return gare.size();
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
