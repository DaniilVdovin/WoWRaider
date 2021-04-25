package com.daniilvdovin.wowraider.mainscreen;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daniilvdovin.wowraider.R;
import com.daniilvdovin.wowraider.model2.RaidRankingGuild;

public class AdapterRaidRanking extends RecyclerView.Adapter<AdapterRaidRanking.ViewHolder>{
    private RaidRankingGuild[] Item = new RaidRankingGuild[]{};

    public View.OnClickListener OnClickListener;
    public Context context;
    public com.daniilvdovin.wowraider.MainActivity MainActivity;
    public int ShowOnly=0;
    public AdapterRaidRanking(Context context, RaidRankingGuild[] Item, int ShowOnly)
    {
        this.Item = Item;
        this.context = context;
        this.ShowOnly = ShowOnly;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name,realm,rank,progress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.t_price);
            rank = itemView.findViewById(R.id.t_region);
            realm = itemView.findViewById(R.id.t_last_change);
            progress = itemView.findViewById(R.id.t_progres);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.raidrankguildsitem,
                        parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final RaidRankingGuild data = Item[position];
        holder.name.setText(""+data.getGuild().getName());
        holder.rank.setText(""+(position+1));
        holder.realm.setText(""+data.getGuild().getRealm().getName());
        holder.progress.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        if(Item.length>ShowOnly)
            return ShowOnly;
        else
            return Item.length;
    }
}

